package run.mojo.actor.action;

import java.util.concurrent.CompletableFuture;

/**
 *
 */
public class ActionFuture<R> extends CompletableFuture<R> {
  private R response;
}
