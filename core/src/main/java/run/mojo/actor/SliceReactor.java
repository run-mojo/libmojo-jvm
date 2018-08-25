package run.mojo.actor;

/**
 *
 */
public class SliceReactor<S extends SliceState> {

  public final int id;
  public final S state;

  ActorRuntime[] runtimes;
  ActorService[] services;
  Registry registry;

  SliceReactor(int id, S state) {
    this.id = id;
    this.state = state;
  }
}
