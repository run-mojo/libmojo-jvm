package run.mojo.http;

import java.util.function.Function;
import run.mojo.http.HttpResponse.Builder;

/**
 * Function to invoke to create a HttpResponse from a type.
 */
public abstract class IntoResponse<T> implements Function<T, HttpResponse> {
  public static HttpResponse.Builder builder() {
    return new Builder();
  }
}
