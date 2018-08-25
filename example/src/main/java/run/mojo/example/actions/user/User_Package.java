package run.mojo.example.actions.user;

import javax.inject.Inject;

/**
 *
 */
public class User_Package {

  public final UserService user;

  @Inject
  User_Package(UserService user) {
    this.user = user;
  }
}
