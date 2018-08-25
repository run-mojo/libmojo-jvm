package run.mojo.example;

import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import java.util.Set;
import run.mojo.actor.ActorService;
import run.mojo.actor.Runtime;
import run.mojo.example.actions.user.UserService;

/**
 *
 */
public class Main {

  public static void main(String[] args) {
    Schema<Person> personSchema = RuntimeSchema.getSchema(Person.class);

    personSchema.newMessage();

    final AppComponent appComponent = DaggerAppComponent.create();

    SliceComponent[] slices = new SliceComponent[2];
    for (int i = 0; i < slices.length; i++) {
      final SliceComponent sliceComponent = DaggerSliceComponent.builder()
          .appComponent(appComponent)
          .build();

      slices[i] = sliceComponent;

      final Set<ActorService> services = sliceComponent.registry();
      final UserService service = sliceComponent.services().actions.user;

      System.out.println(service);
    }

    final Runtime runtime = Runtime.create(slices);
    System.out.println();
  }
}
