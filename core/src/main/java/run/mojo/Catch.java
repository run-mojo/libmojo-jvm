package run.mojo;

/**
 *
 */
public class Catch {
  public static void caught(Location location, Throwable e) {

  }

  public enum Location {
    HTTP_ASYNC_RESULT_ALLOC,
    HTTP_ASYNC_RESULT_ALLOC_CALLBACK,
    HTTP_ASYNC_RESULT_COMPLETED,
    HTTP_ASYNC_RESULT_COMPLETED_EX,
    ACTOR_FUTURE_FINISH,
    ACTOR_FUTURE_CANCELED,
  }
}
