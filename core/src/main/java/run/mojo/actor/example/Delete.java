package run.mojo.actor.example;

import io.protostuff.Tag;
import run.mojo.message.Result;

/**
 *
 */
public interface Delete {

  Result<Response> delete(Request request);

  class Request {

    @Tag(value = 1, alias = "name")
    String name;
  }

  class Response {

  }
}
