package run.mojo.actor.example;

import run.mojo.actor.Actor;
import run.mojo.actor.Ask;

/**
 *
 */
public class MyActor extends Actor<MyActor> implements Create, Delete {

  @Override
  public Ask<Create.Response> create(Create.Request request) {
    return null;
  }

  @Override
  public Ask<Delete.Response> delete(Delete.Request request) {
    return null;
  }
}
