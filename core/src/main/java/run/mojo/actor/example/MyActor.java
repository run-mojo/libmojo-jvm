package run.mojo.actor.example;

import run.mojo.message.Result;

/**
 *
 */
public class MyActor implements Create, Delete {

  @Override
  public Result<Create.Response> create(Create.Request request) {
    return null;
  }

  @Override
  public Result<Create.Response> update(Create.Request request) {
    return null;
  }

  @Override
  public Result<Delete.Response> delete(Delete.Request request) {
    return null;
  }
}
