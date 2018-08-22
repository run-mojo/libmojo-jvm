package run.mojo.http;

import run.mojo.actor.ActorContext;

/**
 *
 */
public class HttpContext<A extends HttpActor, S> extends ActorContext<A> {
  protected HttpContext(long handle) {
    super(handle);
  }

  public HttpRequest<S> request() {
    return null;
  }
}
