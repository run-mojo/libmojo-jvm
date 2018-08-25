package run.mojo.message;

/**
 *
 */
public class Result<R> {
  // Result to ask.
  public final R result;
  // Pointer to Metal Future.
  public final long future;

  private Result(R result, long future) {
    this.result = result;
    this.future = future;
  }

  public static <R> Result<R> result(R result) {
    return new Result<>(result, 0L);
  }

  public static <R> Result<R> future() {
    return new Result<>(null, 0L);
  }
}
