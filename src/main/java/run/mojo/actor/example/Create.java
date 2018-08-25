package run.mojo.actor.example;

import run.mojo.actor.Ask;
import run.mojo.actor.AskHandler;
import run.mojo.example.Person;

/**
 *
 */
public interface Create {
  Ask<Response> create(Request request);

  class Request {
    Person person;
  }

  class Response {

  }
}
