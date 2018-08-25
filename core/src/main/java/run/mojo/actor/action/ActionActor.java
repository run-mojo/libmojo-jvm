package run.mojo.actor.action;

/**
 *
 */
public abstract class ActionActor<A extends ActionActor<A, M, R>, M, R> {

  protected abstract ActionFuture<R> run(M message);

  protected ActionFuture<R> result(R message) {
    return new ActionFuture<>();
  }

  protected ActionFuture<R> future() {
    return new ActionFuture<>();
  }
}
