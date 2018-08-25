package run.mojo.actor;

/**
 *
 */
public class MessageType<T> {

  private int id;
  private Class<T> cls;

  public void toMetal() {

  }

  enum Kind {
    POJO,
  }
}
