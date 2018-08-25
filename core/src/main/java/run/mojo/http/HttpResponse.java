package run.mojo.http;

/**
 *
 */
public class HttpResponse implements Result {
  public static HttpResponse PANIC = new HttpResponse(0L);
  public final long handle;
  private Body body;

  public HttpResponse(long handle) {
    this.handle = handle;
  }

  public Body body() {
    return body;
  }

  public HttpResponse body(Body body) {
    return this;
  }

  public ContentEncoding contentEncoding() {
    return null;
  }

  public HttpResponse contentEncoding(ContentEncoding encoding) {
    return this;
  }

  public static class Builder {

  }
}
