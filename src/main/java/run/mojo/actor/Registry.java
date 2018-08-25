package run.mojo.actor;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Registry {

  public Map<Class, Object> actorTypes = new HashMap<>();

  public ActorEntry create(Class actorClass) {

    return null;
  }

  public static class ActorEntry {

    public final Class actorClass;
    public final HashMap<Integer, MessageType> messages = new HashMap<>();

    public ActorEntry(Class actorClass) {
      this.actorClass = actorClass;
    }
  }
}
