package run.mojo.http;

import run.mojo.actor.MetalActor;

/**
 *
 */
public class HttpMetalActor<
    // <A, S>
    // A = HttpMetalActor
    A extends HttpMetalActor<A, S>,
    // S = ?AppState
    S>
    // extends MetalActor
    extends MetalActor<A, HttpContext<A, S>> {

  @Override
  protected HttpContext<A, S> createContext(long handle) {
    return new HttpContext<>(handle);
  }
}
