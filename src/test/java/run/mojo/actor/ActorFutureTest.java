package run.mojo.actor;

import java.nio.charset.Charset;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

/**
 *
 */
public class ActorFutureTest {


  @Fork(value = 1, warmups = 1)
  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  public void benchActorFuture() {
    final ActorFuture<String> future = ActorFuture.create();

    // Wire it up.
    future.thenAccept(result -> {
    });

    // Complete.
    future.complete("woohoo!");
  }
}
