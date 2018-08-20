package run.mojo.http.ws;

import run.mojo.http.Handler;
import run.mojo.http.HttpRequest;
import run.mojo.http.Responder;

/**
 *
 */
public class ExampleHandler implements Handler<Object> {

  @Override
  public Responder handle(HttpRequest<Object> request) {

    return Responder.future();
  }
}
