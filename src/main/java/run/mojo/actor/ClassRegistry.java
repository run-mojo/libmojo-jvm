package run.mojo.actor;

/**
 *
 */
public class ClassRegistry {
  public Class<?>[] list = new Class<?>[128];
  private int next = -1;

  public int add(Class cls) {
    synchronized (this) {
      final int next = this.next++;
      list[next] = cls;
      return next;
    }
  }


}
