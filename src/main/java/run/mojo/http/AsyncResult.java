package run.mojo.http;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import jdk.internal.misc.Unsafe;
import run.mojo.Catch;
import run.mojo.Catch.Location;
import run.mojo.MojoError;
import run.mojo.MojoError.Code;
import run.mojo.future.Oneshot.Sender;

/**
 *
 */
public class AsyncResult<T> extends CompletableFuture<T> implements Result {

  private static final Unsafe U = Unsafe.getUnsafe();
  private static final long HANDLE = U.objectFieldOffset(Sender.class, "handle");
  public final Function<T, HttpResponse> map;
  private volatile long handle;

  AsyncResult(long handle, Function<T, HttpResponse> map) {
    assert map != null;
    this.handle = handle;
    this.map = map;
  }

  public static <T> AsyncResult<T> create(Function<T, HttpResponse> into) {
    return new AsyncResult<>(Native.asyncAlloc(), into);
  }

  void dispose() {
    final long handle = U.getAndSetLong(this, HANDLE, 0L);
    if (handle != 0L) {
      Native.asyncDealloc(handle);
    }
  }

  void canceled() {
    // Complete future with CANCELED exception.
    completeExceptionally(new MojoError("canceled", Code.CANCELED));
  }

  void timeout() {
    // Complete future with TIMEOUT exception.
    completeExceptionally(new MojoError("timeout", Code.TIMEOUT));
  }

  long into(T result) {
    try {
      final HttpResponse response = map.apply(result);
      if (response != null) {
        return response.handle;
      } else {
        return 0L;
      }
    } catch (Throwable e) {
      // Ignore.
      return 0L;
    }
  }

  @Override
  public boolean complete(T value) {
    try {
      if (super.complete(value)) {
        dispose();
        return true;
      }
      return false;
    } catch (Throwable e) {
      // Metal dealloc.
      dispose();
      Catch.caught(Location.HTTP_ASYNC_RESULT_COMPLETED, e);
      return false;
    }
  }

  @Override
  public boolean completeExceptionally(Throwable ex) {
    try {
      return super.completeExceptionally(ex);
    } catch (Throwable e) {
      Catch.caught(Location.HTTP_ASYNC_RESULT_COMPLETED_EX, e);
      return false;
    } finally {
      // Metal dealloc.
      dispose();
    }
  }
}
