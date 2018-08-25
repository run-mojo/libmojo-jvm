package run.mojo.actor;

/**
 *
 */
public class Addr<A extends MetalActor<A, C>, C extends ActorContext<A>> {
  //
  long pointer;
  volatile boolean closed;

}
