package run.mojo.http;


import java.util.function.Consumer;
import java.util.function.Function;
import run.mojo.Catch;
import run.mojo.Catch.Location;
import run.mojo.MojoError;
import run.mojo.MojoError.Code;

/**
 * Handle a HttpRequest responding with a HttpResponse. HttpHandler types will have one instance per
 * Slice and point to the Slice State.
 */
public interface HttpHandler<S> {

  /**
   * @param request HttpRequest
   * @return A response or a channel of a response.
   */
  Result handle(HttpRequest<S> request);

  default <T> AsyncResult<T> async(
      Consumer<AsyncResult<T>> fut,
      Function<T, HttpResponse> into) {

    final AsyncResult<T> result = AsyncResult.create(into);
    try {
      fut.accept(result);
    } catch (Throwable e) {
      result.dispose();
      Catch.caught(Location.HTTP_ASYNC_RESULT_ALLOC_CALLBACK, e);
      throw new MojoError(e, Code.BAD_INPUT);
    }
    return result;
  }
}
