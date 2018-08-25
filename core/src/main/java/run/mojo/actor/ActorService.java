package run.mojo.actor;

import javax.inject.Provider;

/**
 *
 */
public abstract class ActorService<S extends ActorService<S, A, C, ADDR>, A extends MetalActor<A, C>, C extends ActorContext<A>, ADDR extends Addr<A, C>> {
  public final Class<S> serviceClass;
  public final Class<A> actorClass;
  public final Class<? extends ActorContext> contextClass;
  public final Class<ADDR> addrClass;
  public final Provider<A> actorProvider;
  public final Provider<ADDR> addrProvider;

  public final ActorDescriptor descriptor;

  int id;
  private long pointer;
  // Parent runtime.
  ActorRuntime runtime;
  SliceReactor reactor;

  // Pool config.
  private int poolMin;
  private int poolMax;

  // Max active.
  private int max;

  private long counter;

  protected ActorService(
      Class<S> serviceClass,
      Class<A> actorClass,
      Class<? extends ActorContext> contextClass,
      Class<ADDR> addrClass,
      Provider<A> actorProvider,
      Provider<ADDR> addrProvider,
      AskDescriptor[] asks,
      TellDescriptor[] tells,
      StreamDescriptor[] streams) {
    this.serviceClass = serviceClass;
    this.actorClass = actorClass;
    this.contextClass = contextClass;
    this.addrClass = addrClass;
    this.actorProvider = actorProvider;
    this.addrProvider = addrProvider;

    this.descriptor = ActorDescriptor.create(
        actorClass,
        contextClass,
        addrClass,
        serviceClass,
        asks,
        tells
    );
  }

  void init() {}

  public long active() {
    return 0L;
  }

  public long activeRequest() {
    return 0L;
  }

  /**
   * Spawns a new actor and returns the Producer handle for the newly spawned actor.
   * The actor will be closed automatically after a response is received.
   *
   * @return
   */
  public ADDR request() {
    return null;
  }

  /**
   * Spawns a new Actor and lives until it is manually told to do so.
   *
   * @return
   */
  public ADDR spawn() {
    return null;
  }

  /**
   * Selects the next actor as part of a balancing
   * @return
   */
  public ADDR pooled() {
    return null;
  }

  /**
   * Finds a suitable Addr that is owned by the slice determined by the hash of the
   * supplied slice key.
   *
   * @param sliceKey key to determine slice from it's hash
   * @return
   */
  public ADDR pooled(String sliceKey) {
    return null;
  }

  public ADDR named(String name) {
    return null;
  }

  public interface Delegate {

  }
}
