package run.mojo.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;

/**
 *
 */
public class OneshotCompletable<T> extends CompletableFuture<T> {

  private long handle;

  OneshotCompletable(long handle) {
    this.handle = handle;

  }

  @Override
  public <U> CompletableFuture<U> thenApply(Function<? super T, ? extends U> fn) {
    return super.thenApply(fn);
  }

  @Override
  public <U> CompletableFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn) {
    return super.thenApplyAsync(fn);
  }

  @Override
  public <U> CompletableFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn,
      Executor executor) {
    return super.thenApplyAsync(fn, executor);
  }
}
