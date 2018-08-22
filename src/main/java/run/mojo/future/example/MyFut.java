package run.mojo.future.example;

import run.mojo.future.MetalFuture;
import run.mojo.future.Poll;

/**
 *
 */
public class MyFut extends MetalFuture<String> {

  @Override
  public Poll<String> poll() {
    return notReady();
  }
}
