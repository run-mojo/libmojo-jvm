package run.mojo.actor;

import java.time.Duration;
import java.util.function.BiConsumer;

/**
 * Actix Actor context. This is the system companion to the
 * user-defined Pojo Actor subclass.
 */
public class ActorContext<A extends MetalActor> {

  private long handle;

  protected ActorContext(long handle) {
    this.handle = handle;
  }

  public long handle() {
    return handle;
  }

  public void stop() {
    Native.stop(handle);
  }

  public void terminate() {
    Native.terminate(handle);
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


  public void forceSend(Message msg) {

  }

  public void trySend(Message msg) {

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

  /**
   * Handle of the running future.
   *
   * @return pointer to SpawnHandle
   */
  public long currentHandle() {
    return 0L;
  }

  public long capacity() {
    return 0L;
  }

  public ActorContext<A> capacity(long cap) {
    return this;
  }
}
