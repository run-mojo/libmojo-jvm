package run.mojo.actor;

/**
 *
 */
public enum Running {
  STOP(0),
  CONTINUE(1),;

  public final int code;

  Running(int code) {
    this.code = code;
  }

  public static Running from(int code) {
    switch (code) {
      case 0: return STOP;
      case 1: return CONTINUE;
    }
    return STOP;
  }
}
