package run.mojo.actor;

import run.mojo.mem.Box;

/**
 *
 */
public class Addr<A extends Actor> extends Box {
  public boolean connected() {
    return true;
  }

  public <M extends Message> void doSend(M msg) {

  }

  public <M extends Message> void send(M msg) {

  }
}
