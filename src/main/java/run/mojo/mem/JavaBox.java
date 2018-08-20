package run.mojo.mem;

/**
 * Represents a global Java "boxed" heap allocation.
 * Mojo grabs a GlobalRef from the JNI API.
 */
public class JavaBox {
  private long parent;
  private long handle;
}
