package run.mojo.actor;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import run.mojo.MojoError;
import run.mojo.MojoError.Code;
import run.mojo.message.MessageCodec;

/**
 *
 */
@SuppressWarnings("all")
public class Runtime<S extends SliceState> {

  private static final AtomicInteger ID = new AtomicInteger(0);
  public final int id = ID.getAndIncrement();
  public final SliceReactor<S>[] slices;

  private final ActorRuntime[] runtimes;

  public Runtime(
      SliceReactor<S>[] slices,
      ActorRuntime[] runtimes) {
    this.slices = slices;
    this.runtimes = runtimes;
  }

  @SuppressWarnings("unchecked")
  public static <S extends SliceState> Runtime create(S[] sliceStates) {
    assert sliceStates != null;
    assert sliceStates.length > 0;

    final SliceReactor<S>[] reactors = new SliceReactor[sliceStates.length];

    for (int reactorIndex = 0; reactorIndex < sliceStates.length; reactorIndex++) {
      // Get user State impl.
      final S prototype = sliceStates[reactorIndex];
      if (prototype == null) {
        throw new MojoError("null slice", Code.BAD_INPUT);
      }

      // Create Slice reactor.
      final SliceReactor<S> reactor = new SliceReactor<>(reactorIndex, prototype);
      reactors[reactorIndex] = reactor;

      final Set<ActorService> prototypeServices = prototype.registry();
      if (prototypeServices == null) {
        throw new MojoError("null registry", Code.BAD_INPUT);
      }

      reactor.runtimes = new ActorRuntime[prototypeServices.size()];
      reactor.services = new ActorService[prototypeServices.size()];

      final TreeMap<String, ActorService> sortedServices = new TreeMap<>();
      for (ActorService actorService : prototypeServices) {
        sortedServices.put(actorService.actorClass.getName(), actorService);
      }

      // Build the registry.
      final ArrayList<ActorRuntime> actors = new ArrayList<>(sortedServices.size());
      final ArrayList<MessageCodec> codecs = new ArrayList<>();
      final ArrayList<AskDescriptor> asks = new ArrayList<>();
      final ArrayList<TellDescriptor> tells = new ArrayList<>();
      final ArrayList<StreamDescriptor> streams = new ArrayList<>();

      if (reactorIndex > 0) {
        if (reactors[0].services.length != sortedServices.size()) {
          throw new MojoError("bad descriptor", Code.BAD_INPUT);
        }
      }

      int serviceIndex = -1;
      for (ActorService service : sortedServices.values()) {
        serviceIndex++;
        service.id = actors.size();
        service.reactor = reactor;

        final ActorRuntime runtime;
        if (reactorIndex == 0) {
          runtime = new ActorRuntime(
              service.descriptor,
              new ActorService[sliceStates.length]
          );
        } else {
          runtime = reactors[0].runtimes[serviceIndex];
        }

        service.runtime = runtime;
        reactor.runtimes[serviceIndex] = runtime;
        runtime.services[reactorIndex] = service;

        if (reactorIndex == 0) {
          // Init asks.
          for (AskDescriptor ask : service.descriptor.asks) {
            ask.id = asks.size();
            ask.runtime = runtime;
            ask.services = new ActorService[sliceStates.length];
            ask.services[serviceIndex] = service;
            asks.add(ask);

            if (ask.messageCodec != null) {
              ask.messageId = codecs.size();
              codecs.add(ask.messageCodec);
            }
            if (ask.responseCodec != null) {
              ask.responseId = codecs.size();
              codecs.add(ask.responseCodec);
            }
          }
          // Init tells.
          for (TellDescriptor tell : service.descriptor.tells) {
            tell.id = tells.size();
            tell.runtime = runtime;
            tell.services = new ActorService[sliceStates.length];
            tell.services[0] = service;
            tells.add(tell);
            if (tell.messageCodec != null) {
              tell.messageId = codecs.size();
              codecs.add(tell.messageCodec);
            }
          }
          // Init streams.
          for (StreamDescriptor stream : service.descriptor.streams) {
            stream.id = streams.size();
            stream.runtime = runtime;
            stream.services = new ActorService[sliceStates.length];
            stream.services[0] = service;
            streams.add(stream);

            if (stream.messageCodec != null) {
              stream.messageId = codecs.size();
              codecs.add(stream.messageCodec);
            }
          }
        } else {
          final SliceReactor<S> first = reactors[0];
          final ActorDescriptor firstDesc = first.services[serviceIndex].descriptor;
          if (first.services[serviceIndex].descriptor.asks.length
              != service.descriptor.asks.length) {
            throw new MojoError("bad descriptor", Code.BAD_INPUT);
          }
          if (first.services[serviceIndex].descriptor.tells.length
              != service.descriptor.tells.length) {
            throw new MojoError("bad descriptor", Code.BAD_INPUT);
          }
          if (first.services[serviceIndex].descriptor.streams.length
              != service.descriptor.streams.length) {
            throw new MojoError("bad descriptor", Code.BAD_INPUT);
          }

          // Init asks.
          for (int p = 0; p < service.descriptor.asks.length; p++) {
            final AskDescriptor firstAsk = firstDesc.asks[p];
            final AskDescriptor ask = service.descriptor.asks[p];
            if (!ask.copyFrom(firstAsk)) {
              throw new MojoError("bad descriptor", Code.BAD_INPUT);
            }
            assert ask.copyFrom(firstAsk);
            ask.services[reactorIndex] = service;
            asks.add(ask);
          }

          for (int p = 0; p < service.descriptor.tells.length; p++) {
            final TellDescriptor firstTell = firstDesc.tells[p];
            final TellDescriptor tell = service.descriptor.tells[p];
            if (!tell.copyFrom(firstTell)) {
              throw new MojoError("bad descriptor", Code.BAD_INPUT);
            }
            tell.services[reactorIndex] = service;
            tells.add(tell);
          }

          for (int p = 0; p < service.descriptor.streams.length; p++) {
            final StreamDescriptor firstStream = firstDesc.streams[p];
            final StreamDescriptor stream = service.descriptor.streams[p];
            if (!stream.copyFrom(firstStream)) {
              throw new MojoError("bad descriptor", Code.BAD_INPUT);
            }
            stream.services[reactorIndex] = service;
            streams.add(stream);
          }
        }

        reactor.services[actors.size()] = service;
        actors.add(runtime);
      }

      final Registry registry = new Registry(
          codecs.toArray(new MessageCodec[0]),
          actors.toArray(new ActorRuntime[0]),
          asks.toArray(new AskDescriptor[0]),
          tells.toArray(new TellDescriptor[0]),
          streams.toArray(new StreamDescriptor[0])
      );

      reactor.registry = registry;
    }

    return new Runtime<S>(reactors, reactors[0].runtimes);
  }
}
