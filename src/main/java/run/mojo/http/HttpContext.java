package run.mojo.http;

import run.mojo.actor.ActorContext;

/**
 *
 */
public class HttpContext<A extends HttpMetalActor, S> extends ActorContext<A> {
  private long handle;

  HttpContext(long handle) {
    this.handle = handle;
  }

  public HttpRequest<S> request() {
    return null;
  }
}
