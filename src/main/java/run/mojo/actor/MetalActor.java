package run.mojo.actor;

import run.mojo.MojoError;
import run.mojo.MojoError.Code;
import run.mojo.bytes.Bytes;
import run.mojo.bytes.BytesMut;

/**
 * Represents an MetalActor in the MetalActor system. At the root exists 2 different objects.
 *
 * Each MetalActor has a mailbox managed in the Metal layer which as a default max size of 16 which
 * can be increased or lowered. An MetalActor is bound to a single Arbiter / Slice which is bound to
 * a single thread. So an MetalActor is single-threaded and there is no need to use concurrent
 * collections and primitives within the same MetalActor instance.
 *
 * See "ActorTypeKernel" about how to get fancy with saturating all available processing cores.
 *
 * 1. MetalActor = User-defined POJO 2. ActorContext = Sys-defined object for interacting with
 * the runtime.
 *
 * Channels may be used for communicating with other Actors or even from code that is written for
 * other platforms and frameworks. Channels are thread-safe and can send messages from any thread.
 */
public abstract class MetalActor<
    A extends MetalActor<A, C>,
    C extends ActorContext<A>> {

  public static final int BYTES = 1;
  public static final int BYTES_MUT = 2;
  public static final MojoError NOT_HANDLED = new MojoError(
      "not handled",
      Code.NOT_HANDLED
  );

  private C context = createContext(0);
  private volatile boolean started;

  protected C ctx() {
    return context;
  }

  protected abstract C createContext(long handle);

  /**
   *
   * @return
   */
  public Addr<A> start() {
    return null;
  }

  /**
   * Invoked from JNI as the first step in the MetalActor's lifecycle.
   */
  protected void started0(long handle, long context) {
    this.context.handle = context;
    started();
  }

  /**
   * Method is called when actor get polled first time.
   */
  public void started() {
  }

  protected void restarting0(long context) {
    this.context.handle = context;
    restarting();
  }

  protected void restarting() {
  }

  protected int stopping0(long context) {
    this.context.handle = context;
    final Running result = stopping();
    if (result == null) {
      return Running.STOP.code;
    } else {
      return result.code;
    }
  }

  /**
   * Method is called after an actor is in `MetalActor::Stopping` state. There could be several
   * reasons for stopping. `ActorContext::stop` get called by the actor itself. All addresses to
   * current actor get dropped and no more evented objects left in the context.
   *
   * MetalActor could restore from stopping state by returning `Running.CONTINUE` value.
   *
   * @return Running enum code
   */
  public Running stopping() {
    return Running.STOP;
  }

  protected void stopped0(long context) {
    this.context.handle = context;
    stopped();
  }

  /**
   * Method is called after an actor is stopped, it can be used to perform any needed cleanup work
   * or spawning more actors. This is final state, after this call the actor gets dropped.
   */
  protected void stopped() {
  }

  /**
   *
   * @param context
   * @param future If not 0 then this is a handle to the future for a reply.
   * @param type A hint to what the raw pointer points to.
   * @param message If not 0 then it's a handle (raw pointer) to a native object.
   * @param messageObject If it's a Java Type then this will be set, null if not.
   */
  protected void receive(
      long context,
      long future,
      int type,
      long message,
      Object messageObject
  ) {
    this.context.handle = context;

    if (messageObject != null) {
      message(future, messageObject);
      return;
    }

    switch (type) {
      case BYTES:
        message(future, Bytes.from(message));
        break;
      case BYTES_MUT:
        message(future, BytesMut.from(message));
        break;

      default:
        unknownMessage(future, type, message);
        break;
    }
  }

  protected void unknownMessage(long future, int type, long message) {

  }

  /**
   * Java Object message handler. Raw java objects are valid to pass. The object is pinned in the GC
   * while in-flight and released after it calls this method.
   */
  protected void message(long future, Object message) {

  }

  /**
   *
   */
  public static class Native {
    native static void stop(long handle);

    native static void terminate(long handle);

    native static int state(long handle);
  }
}
