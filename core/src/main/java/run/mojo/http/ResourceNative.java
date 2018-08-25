package run.mojo.http;

/**
 *
 */
public class ResourceNative {

  public native static long route(long resourceHandle);

  public native static long get(long resourceHandle);

  public native static long post(long resourceHandle);

  public native static long put(long resourceHandle);

  public native static long delete(long resourceHandle);

  public native static long head(long resourceHandle);

}
