package run.mojo.http;

/**
 * Represents supported types of content encodings.
 */
public enum ContentEncoding {
  /**
   * Automatically select encoding based on encoding negotiation.
   */
  AUTO(0),

  /**
   * A format using the Brotli algorithm.
   */
  BR(1),

  /**
   * A format using the zlib structure with deflate algorithm.
   */
  DEFLATE(2),

  /**
   * Gzip algorithm.
   */
  GZIP(3),

  /**
   * Indicates the identity function (i.e. no compression, nor modification).
   */
  IDENTITY(4),;

  public final int code;

  ContentEncoding(int code) {
    this.code = code;
  }

  public int code() {
    return code;
  }
}
