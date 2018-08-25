package run.mojo.http;

/**
 *
 */
public class Native {
  native public static long asyncAlloc();

  native public static void asyncDealloc(long handle);

  native public static void asyncResult(long handle);
}
