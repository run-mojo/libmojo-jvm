package run.mojo.example.actions;

import javax.inject.Inject;
import run.mojo.example.actions.user.UserService;

/**
 *
 */
public class Actions_Package {

  public final UserService user;

  @Inject
  Actions_Package(UserService user) {
    this.user = user;
  }

  public final UserService user() {
    return user;
  }
}
