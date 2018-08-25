package run.mojo.example;

import dagger.Component;
import dagger.Module;
import java.util.Set;
import run.mojo.SliceScope;
import run.mojo.actor.ActorService;
import run.mojo.actor.SliceState;
import run.mojo.example.SliceComponent.M;
import run.mojo.example.actions.user.User_Slice;

/**
 *
 */
@Component(
    dependencies = {
        AppComponent.class
    },
    modules = {
        M.class
    }
)
@SliceScope
public interface SliceComponent extends SliceState {

  Services services();

  Set<ActorService> registry();

  @Module(includes = {User_Slice.class})
  class M {

  }
}
