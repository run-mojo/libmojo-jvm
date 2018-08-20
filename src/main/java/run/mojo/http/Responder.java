package run.mojo.http;

/**
 * In charge of sending a HttpResponse for a HttpRequest.
 * "Either" pattern is used here allowing for a synchronous
 * response or a future response. A future is only allocated
 * if used, otherwise there is no performance impact.
 */
public class Responder {
  public HttpResponse response;
  public ResponseFuture future;

  private Responder(HttpResponse response) {
    this.response = response;
  }

  private Responder(ResponseFuture future) {
    this.future = future;
  }

  public static Responder result(HttpResponse response) {
    return new Responder(response);
  }

  public static Responder future() {
    final ResponseFuture future = new ResponseFuture();
    return new Responder(future);
  }
}
