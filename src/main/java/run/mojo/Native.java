package run.mojo;

/**
 *
 */
public class Native {

  /**
   *
   * @param len
   * @return
   */
  public static native long alloc(long len);

  /**
   *
   * @param ptr
   */
  public static native void dealloc(long ptr);
}
