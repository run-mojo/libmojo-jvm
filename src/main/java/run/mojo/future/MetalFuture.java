package run.mojo.future;

/**
 * Java interface to Rust's futures.
 */
public abstract class MetalFuture<T> {

  /**
   *
   * @return
   */
  public abstract Poll<T> poll();

  public static <T> Poll<T> ready(T result) {
    return Poll.ready(result);
  }

  public static <T> Poll<T> notReady() {
    return Poll.notReady();
  }

  public static <T> Poll<T> err(Throwable e) {
    return Poll.err(e);
  }
}
