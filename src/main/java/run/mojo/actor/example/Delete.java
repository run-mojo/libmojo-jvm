package run.mojo.actor.example;

import io.protostuff.Tag;
import run.mojo.actor.Ask;

/**
 *
 */
public interface Delete {

  Ask<Response> delete(Request request);

  class Request {

    @Tag(value = 1, alias = "name")
    String name;
  }

  class Response {

  }
}
