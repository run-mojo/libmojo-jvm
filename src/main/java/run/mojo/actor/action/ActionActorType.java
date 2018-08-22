package run.mojo.actor.action;

import java.util.function.Supplier;

/**
 *
 */
public class ActionActorType<A extends ActionActor<A, M, R>, M, R> {
  private Supplier<A> provider;
}
