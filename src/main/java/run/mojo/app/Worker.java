package run.mojo.app;

import run.mojo.actor.Actor;
import run.mojo.actor.Addr;

/**
 * Slice is where async work is processed.
 */
public class Worker {

  private Addr<?> arbiter;
  private Thread thread;

  /**
   *
   * @param actor
   */
  public void spawn(Actor actor) {

  }
}
