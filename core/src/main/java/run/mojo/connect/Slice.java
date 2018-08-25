package run.mojo.connect;

import java.util.ArrayList;

/**
 * Effectively a shard of a Redis cluster or the entire thing if using
 * standalone Redis or sentinel.
 */
public class Slice {
  private RespActor writer;
  private final ArrayList<RespActor> nodes =
      new ArrayList<>(8);

  /**
   *
   */
  public class Bus {

  }
}
