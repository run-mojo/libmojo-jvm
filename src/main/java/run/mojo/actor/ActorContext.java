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


  public void forceSend(MessageType msg) {

  }

  public void trySend(MessageType msg) {

  }

  public void send(MessageType msg) {

  }

  public void sendLater(MessageType msg, Duration dur) {

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
