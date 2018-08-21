package run.mojo.http;

/**
 *
 */
public interface Body {

  default boolean isStreaming() {
    return false;
  }

  class Empty implements Body {

  }

  class Binary implements Body {

  }

  class Streaming implements Body {

  }
}
