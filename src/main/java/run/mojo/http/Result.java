package run.mojo.http;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import run.mojo.future.Oneshot;
import run.mojo.future.Oneshot.Sender;

/**
 * In charge of sending a HttpResponse for a HttpRequest.
 * "Either" pattern is used here allowing for a synchronous
 * response or a channel response. A channel is only allocated
 * if used, otherwise there is no performance impact.
 */
public class Result<T> {

  /**
   * The response to immediately use. A Body for a response
   * may be Streaming which is fully non-blocking.
   */
  public final HttpResponse response;

  /**
   * The oneshot channel to use for the Response building.
   * "IntoResponse" is used so HttpResponses are always
   * constructed on the Handler's arbiter using the Arbiter's
   * response pool.
   *
   * Another async pattern is to return with a HttpResponse
   * immediately and use a "Streaming" body. That has the
   * benefit of responding quickly even if the body will
   * take some time to be fully sent.
   *
   */
  public final Oneshot<IntoResponse<T>> channel;

  private Result(HttpResponse response) {
    this.response = response;
    this.channel = null;
  }

  private Result(Oneshot<IntoResponse<T>> channel) {
    this.response = null;
    this.channel = channel;
  }

  public CompletableFuture<IntoResponse<T>> asFuture() {
    if (response != null) {
      // Return a completed channel.
      return CompletableFuture.completedFuture(new IntoResponse<T>() {
        @Override
        public HttpResponse apply(T t) {
          return response;
        }
      });
    } else {
      assert this.channel != null;
      final Sender<IntoResponse<T>> sender = this.channel.sender;
      final CompletableFuture<IntoResponse<T>> future = new CompletableFuture<>();
      future.thenApply(sender::send);
      return future;
    }
  }

  public static Result result(HttpResponse response) {
    return new Result(response);
  }

  public static Result async(Consumer<Sender<HttpResponse>> consumer) {
    final Oneshot<HttpResponse> oneshot = Oneshot.create();
    return new Result(oneshot);
  }

  public static Result async2(Consumer<Sender<HttpResponse>> consumer) {
    final Oneshot<HttpResponse> oneshot = Oneshot.create();
    return new Result(oneshot);
  }
}
