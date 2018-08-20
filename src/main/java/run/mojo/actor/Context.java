package run.mojo.actor;

import run.mojo.mem.Box;

/**
 *
 */
public class Context extends Box implements ActorContext {
  @Override
  public void stop() {
    Native.actorContextStop(this.handle());
  }

  @Override
  public void terminate() {
    Native.actorContextStop(this.handle());
  }

  @Override
  public ActorState state() {
    return ActorState.from(Native.actorContextState(this.handle()));
  }
}
