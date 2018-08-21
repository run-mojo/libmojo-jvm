package run.mojo.http;


import java.util.function.Consumer;
import run.mojo.future.Oneshot.Sender;

/**
 * Handle a HttpRequest responding with a HttpResponse.
 * Handler types will have one instance per Slice and
 * point to the Slice State.
 */
public interface Handler<S> {

  /**
   *
   * @param request HttpRequest
   * @return A response or a channel of a response.
   */
  Result handle(HttpRequest<S> request);

  static Result async(Consumer<Sender<HttpResponse>> consumer) {
    return Result.async(consumer);
  }
}
