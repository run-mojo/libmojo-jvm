package run.mojo.actor;

/**
 *
 */
public class ResponseChannel<R> {
  public boolean isCanceled() {
    return false;
  }

  public void send(R result) {

  }
}
