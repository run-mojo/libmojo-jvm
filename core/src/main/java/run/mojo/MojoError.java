package run.mojo;

/**
 *
 */
public class MojoError extends RuntimeException {
  public Code code;

  public MojoError() {
    code = Code.UNKNOWN;
  }

  public MojoError(Code code) {
    this.code = code;
  }

  public MojoError(String message, Code code) {
    super(message);
    this.code = code;
  }

  public MojoError(String message, Throwable cause, Code code) {
    super(message, cause);
    this.code = code;
  }

  public MojoError(Throwable cause, Code code) {
    super(cause);
    this.code = code;
  }

  public MojoError(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace, Code code) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.code = code;
  }

  public enum Code {
    UNKNOWN(99),
    BIND(100),
    APP(101),
    CANCELED(120),
    TIMEOUT(121),
    BAD_INPUT(300),
    NOT_HANDLED(301),
    NOMEM(200),
    ILLEGAL_RACE(500),
    NOT_FOUND(404);

    public final int code;

    Code(int code) {
      this.code = code;
    }
  }
}
