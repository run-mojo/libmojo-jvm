package run.mojo.actor;

/**
 *
 */
public class Native {
  native static void stop(long handle);

  native static void terminate(long handle);

  native static int state(long handle);
}
