package run.mojo.actor;

import run.mojo.mem.Box;

/**
 *
 */
public class Actor<A extends Actor> extends Box {

  /**
   * Actor's Context.
   *
   * @return ActorContext
   */
  public AsyncContext<A> context() {
    return null;
  }

  /**
   * Method is called when actor get polled first time.
   *
   * @param ctx ActorContext
   */
  public void started(AsyncContext<A> ctx) {
  }

  /**
   * Method is called after an actor is in `Actor::Stopping` state. There could be several reasons
   * for stopping. `Context::stop` get called by the actor itself. All addresses to current actor
   * get dropped and no more evented objects left in the context.
   *
   * Actor could restore from stopping state by returning `Running.CONTINUE` value.
   *
   * @param ctx ActorContext
   * @return Running enum code
   */
  public int stopping(AsyncContext<A> ctx) {
    return Running.STOP.code;
  }

  /**
   * Method is called after an actor is stopped, it can be used to perform any needed cleanup work
   * or spawning more actors. This is final state, after this call actor get dropped.
   *
   * @param ctx ActorContext
   */
  public void stopped(AsyncContext<A> ctx) {
  }

  /**
   * Message handler.
   *
   * @param message
   */
  public void message(Message message) {
  }
}
