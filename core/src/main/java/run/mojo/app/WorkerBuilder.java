package run.mojo.app;

/**
 *
 */
public abstract class WorkerBuilder {

  protected long handle;

  protected Object init(long handle) {
    System.out.println("native handle: " + handle);
    this.handle = handle;
    return state();
  }

  protected abstract Object state();

  protected abstract void build();

  public WorkerBuilder prefix(String value) {
    return this;
  }
}
