package run.mojo.actor;

import run.mojo.mem.Box;

/**
 * Addr make communication with Actors possible by interacting
 * with an MetalActor's mailbox.
 */
public class Addr<A extends MetalActor> extends Box {
  public boolean connected() {
    return true;
  }

  public <M extends MessageType> void doSend(M msg) {

  }

  public <M extends MessageType> void send(M msg) {

  }

  public void ask() {

  }

  public void tell() {

  }
}
