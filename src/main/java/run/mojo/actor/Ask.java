package run.mojo.actor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class Ask<R> extends CompletableFuture<R> {

  private long handle;

  private Ask(long handle) {
    this.handle = handle;
  }

  public boolean isRequest() {
    return handle != 0L;
  }

  /**
   *
   * @param <R>
   * @return
   */
  public static <R> Ask<R> create(long handle) {
    return new Ask<>(handle);
  }

  /**
   *
   * @param <R>
   * @return
   */
  public static <R> Ask<R> withTimeout(long handle, long millis) {
    return new Ask<>(handle);
  }

  /**
   *
   * @param <R>
   * @return
   */
  public static <R> Ask<R> withTimeout(long handle, long value, TimeUnit unit) {
    return withTimeout(handle, unit.toMillis(value));
  }

  public long handle() {
    return handle;
  }

  @Override
  public boolean complete(R value) {
    return super.complete(value);
  }

  @Override
  public boolean completeExceptionally(Throwable ex) {
    return super.completeExceptionally(ex);
  }


  /**
   *
   * @param handle
   * @param message
   */
  public static void send(long handle, Object message) {

  }
}
