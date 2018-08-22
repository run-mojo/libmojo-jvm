package run.mojo.actor;

/**
 *
 */
public class WithState<S> {
  public final S state;

  public WithState(S state) {
    this.state = state;
  }
}
