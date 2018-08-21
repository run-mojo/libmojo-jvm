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
}
