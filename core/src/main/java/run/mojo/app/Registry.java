package run.mojo.app;

import java.lang.reflect.Method;
import java.util.Set;
import org.reflections.Reflections;
import run.mojo.actor.ActorDescriptor;
import run.mojo.message.Result;
import run.mojo.actor.MetalActor;

/**
 *
 */
public class Registry {
  private final Set<ActorDescriptor> descriptors;

  public Registry(Set<ActorDescriptor> descriptors) {
    this.descriptors = descriptors;
  }

  public static class ActorEntry {

    public final ActorDescriptor descriptor;

    public ActorEntry(ActorDescriptor descriptor) {
      this.descriptor = descriptor;
    }
  }

  public static void main(String[] args) {
    final Reflections reflections = new Reflections("run.mojo");
    final Set<Class<? extends MetalActor>> actorTypes = reflections.getSubTypesOf(MetalActor.class);

    for (Class<? extends MetalActor> actorType : actorTypes) {
      System.out.println(actorType.getName());

      for (Method method : actorType.getMethods()) {

        if (method.getReturnType().isAssignableFrom(Result.class)) {
          System.out.println("  AskResult -> " + method.getName() + "(" + method.getParameterTypes()[0].getName() + ")");
        }
      }
    }
  }
}
