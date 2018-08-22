package run.mojo.http.ws;

import java.time.Duration;
import run.mojo.http.HttpActor;
import run.mojo.http.HttpContext;

/**
 *
 */
public class MyHttpActor extends HttpActor<MyHttpActor, Object> {

  private long timerID;

  @Override
  public void started(HttpContext<MyHttpActor, Object> ctx) {
    super.started(ctx);

    timerID = ctx.runInterval(Duration.ZERO, (act, ctx0) -> {

    });
  }

  @Override
  public void stopped(HttpContext<MyHttpActor, Object> ctx) {
    super.stopped(ctx);
  }
}
