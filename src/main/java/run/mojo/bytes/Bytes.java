package run.mojo.bytes;

import java.nio.ByteBuffer;

/**
 * Metal Bytes immutable byte buffer.
 */
public class Bytes {

  private long handle;
  private volatile int state;

  protected long handle() {
    return handle;
  }

  /**
   *
   * @return
   */
  public long len() {
    return 0L;
  }

  /**
   *
   * @return
   */
  public long cap() {
    return 0L;
  }

  /**
   *
   * @return
   */
  public long data() {
    return 0L;
  }

  /**
   *
   * @param start
   * @param end
   * @return
   */
  public Bytes slice(long start, long end) {
    return this;
  }

  /**
   *
   * @return
   */
  public ByteBuffer toByteBuffer() {
    return ByteBuffer.allocate((int) cap());
  }

  /**
   *
   * @return
   */
  public byte[] toByteArray() {
    return new byte[(int) len()];
  }

  /**
   *
   * @param slice
   * @return
   */
  public int memcmp(Bytes slice) {
    return 0;
  }

  /**
   *
   * @param to
   * @return
   */
  public int memcmp(String to) {
    return 0;
  }

  /**
   *
   * @param to
   * @return
   */
  public int memcmp(int to) {
    return 0;
  }

  /**
   *
   * @param slice
   * @return
   */
  public boolean eq(Bytes slice) {
    return memcmp(slice) == 0;
  }

  /**
   *
   * @param to
   * @return
   */
  public boolean eq(String to) {
    return memcmp(to) == 0;
  }

  /**
   *
   * @param to
   * @return
   */
  public boolean eq(int to) {
    return memcmp(to) == 0;
  }
}
