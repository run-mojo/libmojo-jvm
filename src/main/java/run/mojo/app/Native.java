package run.mojo.app;

import run.mojo.http.Route;

/**
 *
 */
public class Native {

  /**
   * Constructs a new server with "slices" number of threads. Each thread runs an instance of
   * "Slice" that is created by the supplied "WorkerProvider".
   *
   * @return Addr<HttpServer> handle
   */
  public native static long start(
      String bind,
      String systemName,
      int workers,
      int backlog,
      String hostname,
      int systemExit,
      int disableSignals,
      int shutdownTimeout,
      int noHttp2,
      WorkerBuilder builder
  );

  /**
   * Pauses the server linked to the Addr handle passed in.
   */
  public native static long pause(long handle);

  /**
   *
   * @param handle
   * @return
   */
  public native static long resume(long handle);

  /**
   *
   * @param handle
   * @param graceful
   * @param wait
   * @return
   */
  public native static long stop(long handle, int graceful, int wait);

  /**
   *
   * @param value
   * @return
   */
  native static void prefix(String value);

  native static void filter(String value);

  native static void route(String path, String method, Route route);
}
