package run.mojo.stream;

import run.mojo.mem.Box;

/**
 *
 */
public class Receipt extends Box {
  public long ms() {
    return 0L;
  }

  public long seq() {
    return 0L;
  }

  public RecordID id() {
    return null;
  }

  public long deadline() {
    return 0L;
  }
}
