package run.mojo.actor.example;

import run.mojo.http.HttpActor;
import run.mojo.http.HttpContext;

/**
 *
 */
public class MyHttpActor extends HttpActor<MyHttpActor, String> {

  @Override
  public void started(HttpContext<MyHttpActor, String> ctx) {
    super.started(ctx);

  }
}
