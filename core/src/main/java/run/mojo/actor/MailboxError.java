package run.mojo.actor;

/**
 *
 */
public enum MailboxError {
  CLOSED(0),
  TIMEOUT(1),;

  public final int code;

  MailboxError(int code) {
    this.code = code;
  }
}
