package run.mojo.actor.action;

import run.mojo.actor.ActorFuture;
import run.mojo.actor.action.MyAction.Request;
import run.mojo.actor.action.MyAction.Response;

/**
 *
 */
public class MyAction extends ActionActor<MyAction, Request, Response> {

  @Override
  protected ActionFuture<Response> run(Request message) {

    return result(new Response());
  }

  public static class Request {

  }

  public static class Response {

  }
}
