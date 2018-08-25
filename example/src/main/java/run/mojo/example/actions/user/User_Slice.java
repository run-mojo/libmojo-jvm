package run.mojo.example.actions.user;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import javax.inject.Provider;
import run.mojo.actor.ActorService;

/**
 *
 */
@Module
public class User_Slice {

  @Provides
  @IntoSet
  static ActorService provideUserService(Provider<UserService> userService) {
    return userService.get();
  }
}
