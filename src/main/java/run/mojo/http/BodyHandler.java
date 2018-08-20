package run.mojo.http;

import run.mojo.bytes.Bytes;

/**
 * Extracts a HttpRequest's body into a Bytes buffer.
 */
public abstract class BodyHandler<S> implements Handler<S> {

  @Override
  public Responder handle(HttpRequest<S> request) {
    // Start extractor.
    return Responder.future();
  }

  protected abstract Responder handle(HttpRequest<S> request, Bytes body);
}
