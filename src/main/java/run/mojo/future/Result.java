package run.mojo.future;

/**
 *
 */
public class Result<T, E> {
  public final T ok;
  public final E err;

  Result(T ok, E err) {
    this.ok = ok;
    this.err = err;
  }

  public boolean isOk() {
    return ok != null;
  }

  public boolean isErr() {
    return err != null;
  }

  public static <T, E> Result<T, E> ok(T value) {
    return new Result<>(value, null);
  }

  public static <T, E> Result<T, E> err(E err) {
    return new Result<>(null, err);
  }
}
