package run.mojo.future;

import java.util.concurrent.CompletableFuture;

/**
 *
 */
public class Oneshot<T> {

  private static final jdk.internal.misc.Unsafe U = jdk.internal.misc.Unsafe.getUnsafe();

  public final Receiver<T> receiver;
  public final Sender<T> sender;

  private Oneshot(Receiver<T> receiver, Sender<T> sender) {
    this.receiver = receiver;
    this.sender = sender;
  }

  public static <T> Oneshot<T> create() {
    return new Oneshot<>(null, null);
  }

  /**
   *
   * @param <T>
   */
  public static class Receiver<T> extends CompletableFuture<T> {

    private long handle;
  }

  /**
   * Represents the completion half of a oneshot through which the result of a computation is
   * signaled.
   */
  public static class Sender<T> {

    private static final long HANDLE = U.objectFieldOffset(Sender.class, "handle");
    private volatile long handle;

    Sender(long handle) {
      this.handle = handle;
    }

    public static <T> boolean unsafeSend(long handle, T message) {
      return Native.oneshotSend(handle, message) == 0;
    }

    public boolean send(T message) {
      // Safely determine if the handle is valid. This operation effectively
      // drops the native value so we clear the handle to 0 to not use it again.
      final long existing = this.handle;
      if (existing > 0L && U.compareAndSetLong(this, HANDLE, existing, 0L)) {
        return Native.oneshotSend(handle, message) == 0;
      } else {
        return false;
      }
    }
  }
}
