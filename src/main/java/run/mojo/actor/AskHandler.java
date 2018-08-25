package run.mojo.actor;

/**
 *
 */
public interface AskHandler<M, R> {
  Ask<R> onAsk( M message);
}
