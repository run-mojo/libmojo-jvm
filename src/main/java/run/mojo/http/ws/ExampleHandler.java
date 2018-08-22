package run.mojo.http.ws;

import run.mojo.http.HttpHandler;
import run.mojo.http.HttpRequest;
import run.mojo.http.Result;

/**
 *
 */
public class ExampleHandler implements HttpHandler<Object> {

  @Override
  public Result handle(HttpRequest<Object> request) {
    return async(
        future -> {
          future.complete(new MyActionResult());
        },
        (MyActionResult o) -> {
          o.name = "";
          return null;
        }
    );
  }

  class MyActionResult {
    public String name;
  }
}
