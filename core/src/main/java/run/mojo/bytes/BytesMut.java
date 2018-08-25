package run.mojo.bytes;

/**
 * Metal Mutable Bytes.
 */
public class BytesMut extends Bytes {
  protected BytesMut(long handle) {
    super(handle);
  }

  public static BytesMut from(long handle) {
    return new BytesMut(handle);
  }
}
