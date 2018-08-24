package run.mojo;

/**
 *
 */
public class Native {
  static {
    System.loadLibrary("mojo");
  }

  native public static void init();
}
