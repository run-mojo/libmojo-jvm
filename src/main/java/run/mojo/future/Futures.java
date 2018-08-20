package run.mojo.future;

/**
 *
 */
public class Futures {
  public static <T> Mpsc<T> mpsc() {
    return new Mpsc<>();
  }

  public static <T> Oneshot<T> oneshot() {
    return Oneshot.create();
  }
}
