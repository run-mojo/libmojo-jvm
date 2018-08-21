package run.mojo.app;

import run.mojo.app.Svr.WorkerStopping;

/**
 *
 */
public class Example {

  public static void main(String[] args) {
    // Create an injector.

    Svr.builder(
        // State factory. Called for each worker.
        State::new,
        // Number of slices to start.
        1,
        // Slice "App" factory.
        w -> w
            .prefix("")
    ).start();
  }

  static class State implements WorkerStopping<State> {
    public String name;

    public State() {
      this.name = "my_app";
    }

    @Override
    public void stopping(Svr.Slice<State> slice) {

    }

    @Override
    public void stopped(Svr.Slice<State> slice) {

    }
  }
}
