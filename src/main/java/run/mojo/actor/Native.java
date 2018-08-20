package run.mojo.actor;

/**
 *
 */
public class Native {
  native static void actorContextStop(long handle);
  native static void actorContextTerminate(long handle);
  native static int actorContextState(long handle);
}
