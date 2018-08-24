package run.mojo.actor;

/**
 *
 */
public class Arbiter {
  private Sys system;
  private Addr<?> arbiter;
  private Addr<?> mojo;

  /**
   * Start an Actor instance.
   *
   * @param actor
   * @param <A>
   * @param <C>
   * @return
   */
  public <A extends MetalActor<A, C>, C extends ActorContext<A>> Addr<A> start(A actor) {
    return null;
  }

  /**
   * Run a closure on the Arbiter's reactor thread.
   *
   * @param runnable
   */
  public void run(Runnable runnable) {

  }

  native static void sendExecute();
}
