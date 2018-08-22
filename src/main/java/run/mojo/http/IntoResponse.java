package run.mojo.http;

import java.util.function.Function;

/**
 * Function to invoke to create a HttpResponse from a type.
 */
public interface IntoResponse<T> extends Function<T, HttpResponse> {

}
