package run.mojo.actor;

import java.util.function.Consumer;

/**
 *
 */
public class Sys {
  private long handle;
  private String name;
  private Consumer<Sys> consumer;

  public Sys(String name, Consumer<Sys> consumer) {
    this.name = name;
    this.consumer = consumer;
  }

  public static Sys create(String name, Consumer<Sys> runner) {
    final Sys sys = new Sys(name, runner);
    sys.start(runner);
    return sys;
  }

  private void start(Consumer<Sys> runner) {
    start(name, this);
  }

  private void onStart(long handle) {
    System.out.println(handle);
    if (this.consumer != null) {
      this.consumer.accept(this);
    }
  }

  native static long start(String name, Sys sys);
}
