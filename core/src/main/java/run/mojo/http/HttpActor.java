package run.mojo.http;

import run.mojo.actor.MetalActor;

/**
 *
 */
public class HttpActor<
    // <A, S>
    // A = HttpActor
    A extends HttpActor<A, S>,
    // S = ?AppState
    S>
    // extends MetalActor
    extends MetalActor<A, HttpContext<A, S>> {

  @Override
  protected HttpContext<A, S> createContext(long handle) {
    return new HttpContext<>(handle);
  }
}
