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
@Target(ElementType.TYPE)
public @interface Actor {

  /**
   * Determines how the
   * @return
   */
  SpawnStrategy[] strategy() default {
      SpawnStrategy.AUTO,
      SpawnStrategy.NAMED,
      SpawnStrategy.REQUEST
  };

  /**
   *
   * @return
   */
//  Class<? extends Supervisor> supervisor() default Supervisor.class;

  /**
   *
   */
  enum SpawnStrategy {
    /**
     * Let Mojo figure out how to best utilize the resources available.
     */
    AUTO,

    /**
     *
     */
    NAMED,

    /**
     * Each request
     */
    REQUEST,
  }
}
