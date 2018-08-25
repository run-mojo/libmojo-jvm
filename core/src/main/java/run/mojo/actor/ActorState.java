package run.mojo.actor;

/**
 * Actor execution state.
 */
public enum ActorState {
  STARTED(0),
  RUNNING(1),
  STOPPING(2),
  STOPPED(3),;

  public final int code;

  ActorState(int code) {
    this.code = code;
  }

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
