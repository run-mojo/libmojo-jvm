package run.mojo.actor;

/**
 * MetalActor that belongs to a single slice / reactor "event-loop" thread with the associated user
 * defined Slice's state object.
 */
public class SliceActor<A extends SliceActor<A, S>, S> extends MetalActor<A, SliceContext<A, S>> {

  @Override
  protected SliceContext<A, S> createContext(long handle) {
    return new SliceContext<>(handle);
  }
}
