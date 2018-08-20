package run.mojo.actor;

/**
 *
 */
public interface RunCallback<A, C> {
  void apply(A actor, C context);
}
