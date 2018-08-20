package run.mojo.mem;

/**
 * JEMalloc is linked statically into libmojo_jvm.
 * All internal mem allocations happen through JEMalloc.
 */
public class JEMalloc {
  public static native void epoch();

  public static native long allocated();

  public static native long active();

  public static native long metadata();

  public static native long resident();

  public static native long mapped();

  public static native long retained();

  public static native long alloc(int size);

  public static native long realloc(long ptr, int size);

  public static native long dealloc(long ptr);
}
