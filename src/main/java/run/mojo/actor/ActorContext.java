package run.mojo.actor;

import run.mojo.mem.IsMalloc;

/**
 *
 */
public interface ActorContext extends IsMalloc {
  void stop();

  void terminate();

  ActorState state();
}
