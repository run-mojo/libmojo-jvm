package run.mojo.mem;

import java.io.Closeable;

/**
 *
 */
public interface IsMalloc extends Closeable {
  long handle();
}
