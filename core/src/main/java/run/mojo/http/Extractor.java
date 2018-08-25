package run.mojo.http;

/**
 *
 */
public interface Extractor<M> {
  Result<M> extract(HttpRequest request);

  class Result<M> {
    public M message;
    public long future;
  }
}
