package run.mojo.actor;

/**
 *
 */
public class Ask<R> {
  public final R result;
  public final long future;

  private Ask(R result, long future) {
    this.result = result;
    this.future = future;
  }

  public static <R> Ask<R> result(R result) {
    return new Ask<>(result, 0L);
  }

  public static <R> Ask<R> future() {
    return new Ask<>(null, 0L);
  }
}
