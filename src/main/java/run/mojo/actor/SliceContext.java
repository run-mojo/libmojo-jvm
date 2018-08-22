package run.mojo.actor;

/**
 *
 */
public class SliceContext<A extends SliceActor<A, S>, S> extends ActorContext<A> {

  SliceContext(long handle) {
    super(handle);
  }
}
