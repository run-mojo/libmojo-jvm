package run.mojo.actor.example;

import run.mojo.http.HttpMetalActor;
import run.mojo.http.HttpContext;

/**
 *
 */
public class MyHttpActor extends HttpMetalActor<
    MyHttpActor,
    HttpContext<MyHttpActor, String>,
    String> {

  @Override
  public void started(HttpContext<MyHttpActor, String> ctx) {
    super.started(ctx);
    ctx.terminate();
  }
}
