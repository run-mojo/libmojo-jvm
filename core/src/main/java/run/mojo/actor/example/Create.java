package run.mojo.actor.example;

import run.mojo.message.Result;

/**
 *
 */
public interface Create {
  Result<Response> create(Request request);

  Result<Response> update(Request request);

  class Request {

  }

  class Response {

  }
}
