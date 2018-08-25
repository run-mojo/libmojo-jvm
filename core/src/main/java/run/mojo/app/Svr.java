package run.mojo.app;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;
import run.mojo.MojoError;
import run.mojo.MojoError.Code;
import run.mojo.actor.Sys;
import run.mojo.app.SvrBuilder.Reactor;

/**
 * Server s
 */
@SuppressWarnings("unchecked")
public class Svr<T> {

  public final ArrayList<Slice<T>> slices;
  private final AtomicReference status =
      new AtomicReference<>(Server.Status.RUNNING);
  // Latch used to join on server stop.
  private final CountDownLatch join = new CountDownLatch(1);
  // Builder used to start Svr.
  private SvrBuilder<T> builder;
  // SystemAribiter
  // MetalActor Sys
  private Sys system;

  Svr(SvrBuilder<T> builder) {
    this.builder = builder;
    this.slices = new ArrayList<>(builder.reactors.size());
    for (int i = 0; i < builder.reactors.size(); i++) {
      final Slice<T> slice = new Slice<>(this, builder.reactors.get(i));
      this.slices.add(slice);
    }
  }

  public static <T> SvrBuilder<T> builder(
      Supplier<T> provider,
      int slices,
      Consumer<Reactor<T>> factory) {
    if (factory == null) {
      throw new MojoError("builder factory must be supplied", Code.BAD_INPUT);
    }

    final SvrBuilder<T> builder = new SvrBuilder<>(new ArrayList<>(slices));

    for (int i = 0; i < slices; i++) {
      final T state = provider.get();
      final Reactor<T> app = new Reactor<>(builder, i, state);
      builder.reactors.add(app);

      factory.accept(app);
    }

    return builder;
  }

  void start() {
  }

  public void pause() {
    if (!status.compareAndSet(Server.Status.RUNNING, Server.Status.PAUSING)) {
      return;
    }
    try {
      pausing();
    } catch (Throwable e) {
      // Ignore.
    }
    try {
//      Native.pause(arbiter.handle());
    } finally {
      status.compareAndSet(Server.Status.PAUSING, Server.Status.PAUSED);
    }
    try {
      paused();
    } catch (Throwable e) {
      // Ignore.
    }
  }

  public void resume() {
    if (!status.compareAndSet(Server.Status.PAUSED, Server.Status.RESUMING)) {
      return;
    }
    try {
      resuming();
    } catch (Throwable e) {
      // Ignore.
    }
    try {
//      Native.pause(arbiter.handle());
    } finally {
      status.compareAndSet(Server.Status.RESUMING, Server.Status.RUNNING);
    }
    try {
      resumed();
    } catch (Throwable e) {
      // Ignore.
    }
  }

  /**
   * Stops the server and waits until complete.
   *
   * @param graceful flag to determine if we should play nice
   */
  public Svr<T> stop(boolean graceful) {
    if (!status.compareAndSet(Server.Status.RUNNING, Server.Status.STOPPING)) {
      if (!status.compareAndSet(Server.Status.PAUSED, Server.Status.STOPPING)) {
        return this;
      }
    }
    try {
      stopping();
    } catch (Throwable e) {
      // Ignore.
    }
    try {
//      Native.stop(arbiter.handle(), graceful ? 1 : 0, 1);
    } finally {
      status.set(Server.Status.STOPPED);
      join.countDown();
    }
    try {
      stopped();
    } catch (Throwable e) {
      // Ignore.
    }
    return this;
  }

  /**
   *
   * @throws InterruptedException
   */
  public void join() throws InterruptedException {
    join.await();
  }

  private void starting() {
    for (Slice<T> slice : slices) {
      try {
        if (slice.state instanceof WorkerStarted) {
          ((WorkerStarted<T>) slice.state).starting(slice);
        }
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
  }

  private void started() {
    for (Slice<T> slice : slices) {
      if (slice.state instanceof WorkerStarted) {
        ((WorkerStarted<T>) slice.state).started(slice);
      }
    }
  }

  private void crashed() {
    for (Slice<T> slice : slices) {
      if (slice.state instanceof WorkerCrashed) {
        ((WorkerCrashed<T>) slice.state).crashed(slice);
      }
    }
  }

  private void pausing() {
    for (Slice<T> slice : slices) {
      if (slice.state instanceof WorkerPaused) {
        ((WorkerPaused<T>) slice.state).pausing(slice);
      }
    }
  }

  private void paused() {
    for (Slice<T> slice : slices) {
      if (slice.state instanceof WorkerPaused) {
        ((WorkerPaused<T>) slice.state).paused(slice);
      }
    }
  }

  private void resuming() {
    for (Slice<T> slice : slices) {
      if (slice.state instanceof WorkerResumed) {
        ((WorkerResumed<T>) slice.state).resuming(slice);
      }
    }
  }

  private void resumed() {
    for (Slice<T> slice : slices) {
      if (slice.state instanceof WorkerResumed) {
        ((WorkerResumed<T>) slice.state).resumed(slice);
      }
    }
  }

  private void stopping() {
    for (Slice<T> slice : slices) {
      if (slice.state instanceof WorkerStopping) {
        ((WorkerStopping<T>) slice.state).stopping(slice);
      }
    }
  }

  private void stopped() {
    for (Slice<T> slice : slices) {
      if (slice.state instanceof WorkerStopping) {
        ((WorkerStopping<T>) slice.state).stopped(slice);
      }
    }
  }

  public enum Status {
    RUNNING,
    PAUSED,
    RESUMING,
    STOPPING,
    STOPPED,;
  }

  interface WorkerStarted<T> {

    void starting(Slice<T> slice);

    void started(Slice<T> slice);
  }

  interface WorkerCrashed<T> {

    void crashed(Slice<T> slice);
  }

  interface WorkerPaused<T> {

    void pausing(Slice<T> slice);

    void paused(Slice<T> slice);
  }

  interface WorkerResumed<T> {

    void resuming(Slice<T> slice);

    void resumed(Slice<T> slice);
  }

  interface WorkerStopping<T> {

    void stopping(Slice<T> slice);

    void stopped(Slice<T> slice);
  }

  public static class Slice<T> {

    public final Svr<T> svr;
    public final Reactor<T> builder;
    public final int index;
    public final T state;

    private final AtomicInteger status = new AtomicInteger(0);


    private Sys system;

    public Slice(Svr<T> svr, Reactor<T> builder) {
      this.svr = svr;
      this.builder = builder;
      this.index = builder.index;
      this.state = builder.state;
    }
  }
}
