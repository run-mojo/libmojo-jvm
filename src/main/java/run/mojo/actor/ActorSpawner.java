package run.mojo.actor;

import java.util.function.Supplier;

/**
 * Responsible for spawning new Actors.
 */
public class ActorSpawner<A extends Actor<A>> {

  private boolean spread;
  private Supplier<A> provider;
  private int min;
  private int max;


}
