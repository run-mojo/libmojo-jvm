package run.mojo.actor.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import run.mojo.actor.ActorFuture;
import run.mojo.actor.RawFuture;
import run.mojo.actor.Sys;

/**
 *
 */
public class FutureExample {

  public static void main(String[] args) {
    run.mojo.Native.init();

    System.out.println("loaded successfully");

//    System.out.println("Running Java Executor Futures");
//    final ExecutorService executor = Executors.newSingleThreadExecutor();
//    for (int i = 0; i < 2; i++) {
//      long start = System.currentTimeMillis();
//      for (int x = 0; x < 1000000; x++) {
//        final CompletableFuture<Long> future = new CompletableFuture<>();
//        executor.submit(() -> {
//          executor.submit(() -> {
//            future.complete(0L);
//          });
//        });
////          future.complete("done!");
//      }
//      long elapsed = System.currentTimeMillis() - start;
//      System.out.println(elapsed);
//    }

    Sys.create("mojo", sys -> {
      System.out.println("hi");

      System.out.println("Running Mojo Raw Futures");
      for (int i = 0; i < 30; i++) {
        long start = System.currentTimeMillis();
        for (int x = 0; x < 1000000; x++) {
          long rawSender = RawFuture.alloc(1L, 100L);
//          RawFuture.send(rawSender, 200L);
          RawFuture.send(rawSender, 100L);
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println(elapsed);
      }
//
//      {
//        long rawSender = RawFuture.alloc(1L, 100L);
//        System.out.println("RawPointer.sender = " + rawSender);
//        RawFuture.send(rawSender, 200L);
//        System.out.println("RawPointer.sent");
//      }

//      for (int i = 0; i < 30; i++) {
//        long start = System.currentTimeMillis();
//        for (int x = 0; x < 1000000; x++) {
//          final ActorFuture<String> future = ActorFuture.create();
////          future.cancel(true);
//          future.complete("done!");
////          future.cancel(true);
//        }
//        long elapsed = System.currentTimeMillis() - start;
//        System.out.println(elapsed);
//      }


      final ActorFuture<String> future = ActorFuture.create();

      // Wire it up.
      future.thenAccept(result -> {
        System.out.println("Future.then");
        System.out.println(result);
      });

      // Complete.
      future.complete("woohoo!");
    });
  }
}
