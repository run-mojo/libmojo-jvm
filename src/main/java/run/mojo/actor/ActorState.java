package run.mojo.actor;

/**
 *
 */
public enum ActorState {
  STARTED,
  RUNNING,
  STOPPING,
  STOPPED,;

  public boolean isAlive() {
    return this == STARTED || this == RUNNING;
  }

  public boolean isStopping() {
    return this == STOPPING || this == STOPPED;
  }

  public static ActorState from(int code) {
    switch (code) {
      case 0: return STARTED;
      case 1: return RUNNING;
      case 2: return STOPPING;
      case 3: return STOPPED;
    }
    return STARTED;
  }
}
