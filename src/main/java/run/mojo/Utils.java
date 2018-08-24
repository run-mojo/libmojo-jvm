package run.mojo;

import java.lang.reflect.Field;
import run.mojo.actor.ActorFuture;
import sun.misc.Unsafe;

/**
 *
 */
public class Utils {
  public static final Unsafe UNSAFE;

  static {
    try {
      Field f = Unsafe.class.getDeclaredField("theUnsafe");
      f.setAccessible(true);
      UNSAFE = (Unsafe) f.get(null);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  public static long objectFieldOffset(Class cls, String fieldName) {
    Field field = null;
    try {
      field = ActorFuture.class.getDeclaredField("handle");
      field.setAccessible(true);
      return UNSAFE.objectFieldOffset(field);
    } catch (NoSuchFieldException e) {
      throw new RuntimeException(e);
    }
  }
}
