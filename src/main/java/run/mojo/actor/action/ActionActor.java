package run.mojo.actor.action;

import run.mojo.actor.Actor;

/**
 *
 */
public abstract class ActionActor<A extends ActionActor<A, M, R>, M, R> extends Actor<A> {

  protected abstract ActionFuture<R> run(M message);

  @Override
  public ActionAddr<A, M, R> start() {
    return null;
  }

  protected ActionFuture<R> result(R message) {
    return new ActionFuture<>();
  }

  protected ActionFuture<R> future() {
    return new ActionFuture<>();
  }
}
