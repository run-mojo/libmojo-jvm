package run.mojo.actor;

import sun.jvm.hotspot.memory.ConcurrentMarkSweepGeneration;

/**
 *
 */
public class ActorPool {
  private long pointer;
  private System system;
  private Slice[] slices;
  private int min;
  private int max;

  public class System {

  }

  public class Slice {
    private long pointer;
    private int min;
    private int max;
  }
}
