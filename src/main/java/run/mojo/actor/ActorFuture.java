package run.mojo.actor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import run.mojo.Catch;
import run.mojo.Catch.Location;
import run.mojo.MojoError;
import run.mojo.MojoError.Code;
import run.mojo.Utils;
import sun.misc.Unsafe;

/**
 * Wraps a metal oneshot channel. It extends "CompleteableFuture" and can take full advantage of the
 * standard API. On "complete" oneshot.Sender.send() is invoked atomically a single time.
 *
 * There are two sides to this future. Underneath is a "oneshot" channel. The "receiver" is
 * managed as a "Metal Future" that is scheduled in the Reactor waiting for the "sender" to
 * send it's "one" message.
 */
@SuppressWarnings("all")
public class ActorFuture<R> extends CompletableFuture<R> {

  private static final Unsafe U = Utils.UNSAFE;
  private static final long HANDLE = Utils.objectFieldOffset(ActorFuture.class, "handle");

  private volatile long handle;

  private ActorFuture() {
  }

  private ActorFuture(long handle) {
    this.handle = handle;
  }

  /**
   *
   * @param <R>
   * @return
   */
  public static <R> ActorFuture<R> create() {
    final ActorFuture<R> future = new ActorFuture<>();
    final long handle = alloc(future);
    if (handle == 0L) {
      throw new MojoError("out of memory", Code.NOMEM);
    }
    future.handle = handle;
    return future;
//    long handle = alloc(new ActorFuture());
//    return null;
  }

  /**
   *
   * @param <R>
   * @return
   */
  public static <R> ActorFuture<R> from(long handle) {
    return new ActorFuture<>(handle);
  }

  /**
   *
   * @param <R>
   * @return
   */
  public static <R> ActorFuture<R> fromWithTimeout(long handle, long millis) {
    return new ActorFuture<>(handle);
  }

  /**
   *
   * @param <R>
   * @return
   */
  public static <R> ActorFuture<R> fromWithTimeout(long handle, long value, TimeUnit unit) {
    return fromWithTimeout(handle, unit.toMillis(value));
  }

  /**
   * Creates a new ActorFuture.
   */
  native static long alloc(ActorFuture future);

  /**
   *
   * @param handle
   * @return
   */
  native static long drop(long handle);

  /**
   *
   * @param handle
   * @param message
   */
  native static void send(long handle, Object message);

  public long handle() {
    return handle;
  }

  @Override
  public boolean complete(R value) {
    final long handle = this.handle;
    if (handle == 0L) {
      return false;
    }
    if (!U.compareAndSwapLong(this, HANDLE, handle, 0L)) {
      return false;
    }
    try {
      send(handle, value);
    } catch (Throwable e) {
    }
    return true;
  }

  @Override
  public boolean completeExceptionally(Throwable ex) {
    final long handle = this.handle;
    if (handle == 0L) {
      return false;
    }
    // Did we lose the race?
    if (!U.compareAndSwapLong(this, HANDLE, handle, 0L)) {
      return false;
    }
    try {
      send(handle, ex);
    } catch (Throwable e) {
    }
    return true;
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    final long handle = this.handle;
    if (handle == 0L) {
      return false;
    }
    // Did we lose the race?
    if (!U.compareAndSwapLong(this, HANDLE, handle, 0L)) {
      return false;
    }
    drop(handle);
    return super.cancel(mayInterruptIfRunning);
  }

  /**
   *
   * @return
   */
//  public R await() {
//    return com.ea.async.Async.await(this);
//  }

  /**
   * libmojo Callback
   */
  void finish(Object message) {
    final long handle = this.handle;
    if (handle != 0L) {
      if (!U.compareAndSwapLong(this, HANDLE, handle, 0L)) {
        // This is an illegal race. Punishment must be delivered.
        Catch.caught(
            Location.ACTOR_FUTURE_FINISH,
            new MojoError("clear handle race", Code.ILLEGAL_RACE)
        );
      }
    }
    if (message != null && message instanceof Throwable) {
      super.completeExceptionally((Throwable) message);
      return;
    }

    super.complete((R) message);
  }

  /**
   * libmojo Callback
   */
  void canceled() {
    final long handle = this.handle;
    if (handle != 0L && U.compareAndSwapLong(this, HANDLE, handle, 0L)) {
      // Manually free Sender handle.
      drop(handle);
    }
    super.completeExceptionally(
        new MojoError("canceled", Code.CANCELED)
    );
  }
}
