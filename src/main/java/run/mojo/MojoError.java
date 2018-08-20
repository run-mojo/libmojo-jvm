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
    BAD_INPUT(300),
    NOMEM(200),;

    public final int code;

    Code(int code) {
      this.code = code;
    }
  }
}
