package run.mojo.app;

import run.mojo.actor.MetalActor;
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
  public void spawn(MetalActor actor) {

  }
}
