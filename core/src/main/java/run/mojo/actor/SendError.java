package run.mojo.actor;

/**
 *
 */
public enum SendError {
  FULL(0),
  CLOSED(1),;

  public final int code;

  SendError(int code) {
    this.code = code;
  }
}
