package run.mojo.actor;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 */
public class ActorType<A extends MetalActor> {

  private static final AtomicLong COUNTER = new AtomicLong(0L);

  public final long id;
  public final Class<A> actorClass;

  ActorType(long id, Class<A> actorClass) {
    this.id = id;
    this.actorClass = actorClass;
  }

  public static <A extends MetalActor> ActorType<A> of(Class<A> actorClass) {
    assert actorClass != null;
    return new ActorType<>(COUNTER.incrementAndGet(), actorClass);
  }
}
