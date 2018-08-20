package run.mojo.actor;

import java.time.Duration;
import run.mojo.mem.Box;

/**
 *
 */
public class Request extends Box {
  public Request timeout(Duration duration) {
    return this;
  }
}
