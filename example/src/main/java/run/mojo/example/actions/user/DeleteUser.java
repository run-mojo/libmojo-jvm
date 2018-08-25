package run.mojo.example.actions.user;

import io.protostuff.Tag;
import run.mojo.message.Result;

/**
 *
 */
public interface DeleteUser {

  Result<Response> delete(Request request);

  class Request {

    @Tag(value = 1, alias = "name")
    String name;
  }

  class Response {

  }
}
