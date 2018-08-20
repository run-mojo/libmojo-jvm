package run.mojo.mem;

import java.io.IOException;

/**
 * Represents a "boxed" object on the native heap.
 */
public class Box implements IsMalloc {
  protected long handle;

  public Box() {}

  protected Box(long handle) {
    this.handle = handle;
  }

  @Override
  public long handle() {
    return handle;
  }

  @Override
  public void close() throws IOException {
    // Drop object and free up memory.
  }
}
