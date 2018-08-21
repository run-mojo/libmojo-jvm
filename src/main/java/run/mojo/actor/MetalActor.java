package run.mojo.actor;

import run.mojo.mem.Box;

/**
 * Represents an MetalActor in the MetalActor system. At the root exists 2
 * different objects.
 *
 * Each MetalActor has a mailbox managed in the Metal layer which as
 * a default max size of 16 which can be increased or lowered.
 * An MetalActor is bound to a single Arbiter / Slice which is bound to
 * a single thread. So an MetalActor is single-threaded and there
 * is no need to use concurrent collections and primitives within
 * the same MetalActor instance.
 *
 * See "ActorTypeKernel" about how to get fancy with saturating
 * all available processing cores.
 *
 * 1. MetalActor = User-defined POJO
 * 2. ActorContext = System-defined object for interacting with the runtime.
 *
 * Channels may be used for communicating with other Actors or even
 * from code that is written for other platforms and frameworks.
 * Channels are thread-safe and can send messages from any thread.
 */
public abstract class MetalActor<A extends MetalActor<A, C>, C extends ActorContext<A>> extends Box {
  private long handle;
  // Keep a cached instance of the Context wrapper object for the life of the actor.
  // This is created on start by invoking the "createContext()" factory method.
  private C context;

  /**
   * MetalActor's ActorContext.
   *
   * @return ActorContext
   */
  public C context() {
    //
    return null;
  }

  /**
   * Invoked from JNI as the first step in the MetalActor's lifecycle.
   *
   * @param handle
   * @param contextHandler
   */
  protected void started0(long handle, long contextHandler) {
    this.handle = handle;
    context = createContext(contextHandler);
  }

  protected abstract C createContext(long handle);

  /**
   * Method is called when actor get polled first time.
   *
   * @param ctx ActorContext
   */
  public void started(C ctx) {
  }

  int stopping0() {
    return stopping(context);
  }
  /**
   * Method is called after an actor is in `MetalActor::Stopping` state. There could be several reasons
   * for stopping. `ActorContext::stop` get called by the actor itself. All addresses to current actor
   * get dropped and no more evented objects left in the context.
   *
   * MetalActor could restore from stopping state by returning `Running.CONTINUE` value.
   *
   * @param ctx ActorContext
   * @return Running enum code
   */
  public int stopping(C ctx) {
    return Running.STOP.code;
  }

  /**
   * Method is called after an actor is stopped, it can be used to perform any needed cleanup work
   * or spawning more actors. This is final state, after this call the actor gets dropped.
   *
   * @param ctx ActorContext
   */
  public void stopped(C ctx) {
  }

  /**
   * Message handler.
   *
   * @param message
   */
  public void message(Message message) {
  }
}
