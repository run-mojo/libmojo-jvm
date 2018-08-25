package run.mojo.actor;

import java.time.Duration;
import java.util.function.BiConsumer;

/**
 * Actix Actor context. This is the system companion to the
 * user-defined Pojo Actor subclass.
 */
public class ActorContext<A extends MetalActor> {

  long handle;

  protected ActorContext(long handle) {
    this.handle = handle;
  }

  public long handle() {
    return handle;
  }

//  public void stop() {
//    Native.stop(handle);
//  }
//
//  public void terminate() {
//    Native.terminate(handle);
//  }

  public ActorState state() {
//    return ActorState.from(Native.state(handle));
    return ActorState.STARTED;
  }

  public long spawn(ActorFuture fut) {
    return 0L;
  }

  /**
   *
   * @param fut
   */
  public void wait(ActorFuture fut) {

  }

  public boolean waiting() {
    return false;
  }

  public boolean cancelFuture(long handle) {
    return true;
  }


  public long runLater(
      Duration dur,
      BiConsumer<A, ActorContext<A>> fn
  ) {
    return 0L;
  }

  public long runInterval(
      Duration dur,
      BiConsumer<A, ActorContext<A>> fn
  ) {
    return 0L;
  }

  public long inboxCapacity() {
    return 0L;
  }

  public ActorContext<A> inboxCapacity(long cap) {
    return this;
  }
}
