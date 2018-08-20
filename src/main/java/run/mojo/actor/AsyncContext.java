package run.mojo.actor;

import java.time.Duration;
import java.util.function.BiConsumer;

/**
 *
 */
public interface AsyncContext<A extends Actor> {
  Addr<A> address();

  long spawn(ActorFuture fut);

  void wait(ActorFuture fut);

  boolean waiting();

  boolean cancelFuture(long handle);

  void notify0(Message msg);

  void notifyLater(Message msg, Duration dur);

  long runLater(
      Duration dur,
      BiConsumer<A, AsyncContext<A>> fn
  );

  default long runInterval(
      Duration dur,
      BiConsumer<A, AsyncContext<A>> fn
  ) {
    return 0L;
  }
}
