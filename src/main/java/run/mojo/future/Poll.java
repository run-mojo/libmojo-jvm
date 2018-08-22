package run.mojo.future;

import run.mojo.MojoError;
import run.mojo.Result;

/**
 *
 */
public class Poll<T> extends Result<T, Throwable> {
  private Poll(T ok, MojoError err) {
    super(ok, err);
  }

  private Poll(Throwable err) {
    super(null, err);
  }

  public boolean isReady() {
    return ok != null;
  }

  public static <T> Poll<T> ready(T result) {
    return new Poll<>(result, null);
  }

  public static <T> Poll<T> notReady() {
    return null;
  }

  public static <T> Poll<T> err(Throwable e) {
    return new Poll<>(e);
  }
}
