package run.mojo.future;

import java.util.concurrent.CompletableFuture;

/**
 *
 */
public class Oneshot<T> {

//  private static final jdk.internal.misc.Unsafe U = jdk.internal.misc.Unsafe.getUnsafe();

  public final long receiver;
  public final long sender;

  private Oneshot(long receiver, long sender) {
    this.receiver = receiver;
    this.sender = sender;
  }

  public static <T> Oneshot<T> create() {
    return new Oneshot<>(0L, 0L);
  }

  public final Receiver<T> receiver() {
    return new Receiver<>(receiver);
  }

  public final Sender<T> sender() {
    return new Sender<>(sender);
  }

  /**
   *
   * @param <T>
   */
  public static class Receiver<T> extends CompletableFuture<T> {

    private long handle;

    Receiver(long handle) {
      this.handle = handle;
    }

    public static <T> Receiver<T> from(long handle) {
      return new Receiver<>(handle);
    }

    public void dispose() {
      // TODO: Drop strong Arc reference
    }
  }

  /**
   * Represents the completion half of a oneshot through which the result of a computation is
   * signaled.
   *
   * This is automatically disposed of after the send operation.
   */
  public static class Sender<T> {

//    private static final long HANDLE = U.objectFieldOffset(Sender.class, "handle");
    private volatile long handle;

    Sender(long handle) {
      this.handle = handle;
    }

    public static <T> Sender<T> from(long handle) {
      return new Sender<>(handle);
    }

    /**
     * Send a message to a raw pointer. Please know what you are doing before using any unsafe
     * method.
     */
    public static <T> boolean unsafeSend(long handle, T message) {
      return Native.oneshotSend(handle, message) == 0;
    }

    /**
     *
     * @param message
     * @return
     */
    public boolean send(T message) {
      // Safely determine if the handle is valid. This operation effectively
      // drops the native value so we clear the handle to 0 to not use it again.
      final long existing = this.handle;
//      if (existing > 0L && U.compareAndSetLong(this, HANDLE, existing, 0L)) {
//        return Native.oneshotSend(handle, message) == 0;
//      } else {
        return false;
//      }
    }

    public void dispose() {
      // Safely drop by guaranteeing against double-free and use-after free.
      // This handle has an implicit weak Arc reference.
      final long existing = this.handle;
//      if (existing > 0L && U.compareAndSetLong(this, HANDLE, existing, 0L)) {
        // TODO: Drop strong Arc reference
//      }
    }
  }
}
