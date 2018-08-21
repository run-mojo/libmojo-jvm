package run.mojo.http;

/**
 *
 */
public class HttpResponse {
  public static HttpResponse PANIC = new HttpResponse();
  private long handle;
  private Body body;

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
