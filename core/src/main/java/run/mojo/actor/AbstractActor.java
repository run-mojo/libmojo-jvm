package run.mojo.actor;

/**
 *
 */
public class AbstractActor<A extends AbstractActor<A>> extends MetalActor<A, ActorContext<A>> {

  @Override
  protected ActorContext<A> createContext(long handle) {
    return new ActorContext<>(handle);
  }
}
