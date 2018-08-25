package run.mojo.stream;

/**
 *
 */
public interface Value {
  class Int implements Value {
    public long value;
  }

  class Str implements Value {
    private long handle;
    private int length;
  }
}
