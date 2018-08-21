package run.mojo.actor;

/**
 *
 */
public class Actor<A extends Actor<A>> extends MetalActor<A, ActorContext<A>> {

  @Override
  protected ActorContext<A> createContext(long handle) {
    return new ActorContext<>(handle);
  }
}
