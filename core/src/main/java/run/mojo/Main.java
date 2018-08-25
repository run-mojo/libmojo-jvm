package run.mojo;

import java.util.concurrent.Executors;
import run.mojo.app.WorkerBuilder;
import run.mojo.app.Native;

/**
 *
 */
public class Main {
  static {
    System.loadLibrary("mojo");
  }

  public static void main(String[] args) throws Throwable {
//    AsyncResult.init();

//    var server = new ServerBuilder()
//        .bind("localhost:8000")
//        .slices(1)
//        .shutdownTimeout(30)
//        .start();
//
//    server.join();

    long addr_pointer = Native.start(
        "localhost:8000",
        "mojo",
        2,
        0,
        null,
        1,
        0,
        30,
        0,
        new WorkerBuilder() {
          public Object hi(long handle) {
            return new Object();
          }

          @Override
          protected Object state() {
            return new Object();
          }

          @Override
          protected void build() {
            prefix("hi");
          }
        }
    );

    Native.stop(addr_pointer, 0, 1);
    System.in.read();

//    var v = await(calculateAsync());
//    var v2 = await(calculateAsync());
//
//    Sys.out.println(v);
//    Sys.exit(0);
  }

//  public static Promise<String> calculateAsync() {
//    final Promise<String> promise = new Promise<>();
//
//    Executors.newCachedThreadPool().submit(() -> {
//      Thread.sleep(500);
//      promise.complete("Hello");
//      return null;
//    });
//
//    return promise;
//  }
}
