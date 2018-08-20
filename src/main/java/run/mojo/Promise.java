package run.mojo;

import java.util.concurrent.CompletableFuture;

/**
 *
 */
public class Promise<T> extends CompletableFuture<T> {
  private long handle;

  public Promise() {
  }

  public long handle() {
    return handle;
  }

  public boolean isDeallocated() {
    return handle == 0L;
  }
}
