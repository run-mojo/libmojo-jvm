package run.mojo.example.actions.user;

import javax.inject.Inject;
import run.mojo.Actor;
import run.mojo.Ask;
import run.mojo.Ask.Http;
import run.mojo.Ask.Http.WebSocket;
import run.mojo.Tell;
import run.mojo.actor.AbstractActor;
import run.mojo.example.Person;
import run.mojo.example.actions.user.CreateUser.Response;
import run.mojo.message.Result;

/**
 *
 */
@Actor
public class UserActor extends AbstractActor<UserActor> {

  private final UserService userService;

  @Inject
  UserActor(UserService userService) {
    this.userService = userService;
  }

  @Ask(
      http = @Http(
          ws = @WebSocket()
      )
  )
  Result<Response> create(CreateUser.Request request) {
    return Result.future();
  }

  Result<Response> create(Person request) {
    return Result.future();
  }

  @Ask
  Result<Response> update(CreateUser.Request request) {
    return null;
  }

  @Ask
  Result<DeleteUser.Response> delete(DeleteUser.Request request) {
    return null;
  }

  @Tell
  void on(UserEvent message) {

  }
}
