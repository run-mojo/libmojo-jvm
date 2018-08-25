package run.mojo.example.actions.user;

import javax.inject.Inject;
import run.mojo.actor.ActorContext;
import run.mojo.actor.Addr;
import run.mojo.example.actions.user.CreateUser.Response;
import run.mojo.message.Result;

/**
 *
 */
public class UserActorAddr extends Addr<UserActor, ActorContext<UserActor>> {

  @Inject
  UserActorAddr() {
  }
  ////////////////////////////////////////////////////////////////////////////////
  // Instance
  ////////////////////////////////////////////////////////////////////////////////

  public Result<Response> create(
      CreateUser.Request request) {
    return null;
  }

  ////////////////////////////////////////////////////////////////////////////////
  // Statics
  ////////////////////////////////////////////////////////////////////////////////

  public static Result<Response> unsafeCreate(
      long addr,
      CreateUser.Request request) {
    return null;
  }

//
//  public static AskResult<CreateUser.Response> update(
//      Addr<UserActor> addr,
//      CreateUser.Request request) {
//    return null;
//  }
//
//  public static AskResult<DeleteUser.Response> delete(
//      Addr<UserActor> addr,
//      DeleteUser.Request request) {
//    return null;
//  }
}
