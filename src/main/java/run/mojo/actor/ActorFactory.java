package run.mojo.actor;

/**
 *
 */
public interface ActorFactory<A extends Actor<? extends A>> {
  A provide();
}
