package run.mojo.actor;

import java.time.Duration;
import java.util.function.BiConsumer;

/**
 * Actix MetalActor context. The user-defined MetalActor is just a POJO.
 */
public class ActorContext<A extends MetalActor> {

  private long handle;

  ActorContext(long handle) {
    this.handle = handle;
  }

  public void stop() {
    Native.stop(handle);
  }

  public void terminate() {
    Native.stop(handle);
  }

  public ActorState state() {
    return ActorState.from(Native.state(handle));
  }

  public Addr<A> address() {
    return null;
  }

  public long spawn(ActorFuture fut) {
    return 0L;
  }

  public void wait(ActorFuture fut) {

  }

  public boolean waiting() {
    return false;
  }

  public boolean cancelFuture(long handle) {
    return true;
  }

  public void send(Message msg) {

  }

  public void sendLater(Message msg, Duration dur) {

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
}
