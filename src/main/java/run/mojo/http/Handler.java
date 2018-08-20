package run.mojo.http;


/**
 * Handle a HttpRequest responding with a HttpResponse.
 * Handler types will have one instance per Slice and
 * point to the Slice State.
 */
public interface Handler<S> {

  /**
   *
   * @param request HttpRequest
   * @return A response or a future of a response.
   */
  Responder handle(HttpRequest<S> request);
}
