package run.mojo.bytes;

/**
 *
 */
public class Frame {
  public long type;
  public Bytes data;

  public Frame(long type, Bytes data) {
    this.type = type;
    this.data = data;
  }
}
