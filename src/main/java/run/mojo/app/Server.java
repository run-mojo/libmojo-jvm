package run.mojo.app;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import run.mojo.actor.ActorSystem;
import run.mojo.actor.Addr;

/**
 *
 */
public class Server implements Closeable {

  private final AtomicReference running = new AtomicReference<>(Status.RUNNING);
  private final CountDownLatch join = new CountDownLatch(1);

  /**
   *
   */
  private Addr<?> addr;

  /**
   * Workers
   */
  private Worker[] workers;
  /**
   * SyncArbiter Addr
   */
  private Addr<?> sync;

  private ActorSystem system;

  Server(long handle) {

  }

  public void pause() {
    if (!running.compareAndSet(Status.RUNNING, Status.PAUSED)) {
      return;
    }
    Native.pause(addr.handle());
  }

  public void resume() {
    if (!running.compareAndSet(Status.PAUSED, Status.RESUMING)) {
      return;
    }
    try {
      Native.pause(addr.handle());
    } finally {
      running.compareAndSet(Status.RESUMING, Status.RUNNING);
    }
  }

  /**
   * Stops the server and waits until complete.
   *
   * @param graceful flag to determine if we should play nice
   */
  public Server stop(boolean graceful) {
    if (!running.compareAndSet(Status.RUNNING, Status.STOPPING)) {
      if (!running.compareAndSet(Status.PAUSED, Status.STOPPING)) {
        return this;
      }
    }
    try {
      Native.stop(addr.handle(), graceful ? 1 : 0, 1);
    } finally {
      running.set(Status.STOPPED);
      join.countDown();
    }
    return this;
  }

  public void join() throws InterruptedException {
    join.await();
  }

  @Override
  public synchronized void close() throws IOException {
    this.stop(false);
  }

  protected void dispose() {

  }

  public enum Status {
    RUNNING,
    PAUSING,
    PAUSED,
    RESUMING,
    STOPPING,
    STOPPED,;
  }
}
