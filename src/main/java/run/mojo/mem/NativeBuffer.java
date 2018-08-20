package run.mojo.mem;

import run.mojo.mem.IsMalloc;

/**
 *
 */
public interface NativeBuffer extends IsMalloc {
  long len();

  long data();

  void update(byte[] buffer);
}
