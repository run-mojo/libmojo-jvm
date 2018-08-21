package run.mojo.actor;

/**
 * An MetalActor Type arbiter.
 */
public class Supervisor {

  public static final CrashStrategy RESTART = new CrashStrategy();
  public static final CrashStrategy NO_RESTART = new CrashStrategy();

  public static final SpawnManager SINGLETON = new SpawnManager();
  public static final SpawnManager POOL = new SpawnManager();
  public static final SpawnManager ONE_PER_WORKER = new SpawnManager();
  public static final SpawnManager ONE_PER_MESSAGE = new SpawnManager();

  public static class CrashStrategy {

  }

  /**
   * Controls the behavior
   */
  public static class SpawnManager {

  }
}
