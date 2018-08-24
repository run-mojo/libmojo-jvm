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

  /**
   *
   * @param result
   * @param <T>
   * @return
   */
  public static <T> Poll<T> ready(T result) {
    return Poll.ready(result);
  }

  /**
   *
   * @param <T>
   * @return
   */
  public static <T> Poll<T> notReady() {
    return Poll.notReady();
  }

  /**
   *
   * @param e
   * @param <T>
   * @return
   */
  public static <T> Poll<T> err(Throwable e) {
    return Poll.err(e);
  }
}
