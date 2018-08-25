package run.mojo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Ask {

  /**
   * Name / path of the action. This is the default if HttpMethod is specified.
   *
   * @return
   */
  String name() default "";

  /**
   * Provide a hint that this message should have a limit on the time it takes
   * to produce a response. Note this is a soft requirement as Mojo runtime
   * services provides much more advanced ways of determining how long something
   * should be allowed to run before determining it a "dead" message.
   *
   * @return timeout in milliseconds.
   */
  long timeout() default 0;

  /**
   * Associated Http method for Mojo to auto-wire. This turns an Ask into something
   * that can be hit from Http.
   *
   * @return
   */
  Http http() default @Http(enabled = false);

  /**
   *
   */
  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.METHOD)
  @interface Http {

    /**
     *
     * @return
     */
    String path() default "";

    /**
     *
     * @return
     */
    boolean enabled() default true;

    /**
     *
     * @return
     */
    Verb[] verb() default {Verb.ANY};

    /**
     * Enable Action over Mojo websocket protocol.
     *
     * @return
     */
    WebSocket ws() default @WebSocket();

    /**
     *
     */
    enum Verb {
      NONE,
      ANY,
      GET,
      POST,
      DELETE,
      PUT,
      TRACE,
      OPTIONS,
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface WebSocket {
      boolean enabled() default true;
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Filter {

    }
  }
}
