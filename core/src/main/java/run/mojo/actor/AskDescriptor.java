package run.mojo.actor;

import java.lang.reflect.Method;
import run.mojo.MojoError;
import run.mojo.MojoError.Code;
import run.mojo.message.MessageCodec;

/**
 *
 */
public class AskDescriptor {

  public final MessageCodec messageCodec;
  public final MessageCodec responseCodec;
  public final Method method;
  public final run.mojo.Ask ask;
  ActorDescriptor actor;
  int id;
  int messageId;
  int responseId;
  ActorRuntime runtime;
  ActorService[] services;

  private AskDescriptor(
      MessageCodec messageCodec,
      MessageCodec responseCodec,
      Method method) {
    this.messageCodec = messageCodec;
    this.responseCodec = responseCodec;
    this.method = method;
    this.ask = method.getDeclaredAnnotation(run.mojo.Ask.class);
  }

  public static AskDescriptor create(
      MessageCodec messageCodec,
      MessageCodec responseCodec,
      Class<? extends MetalActor> actorClass,
      String methodName) {
    try {
      Method m = actorClass.getDeclaredMethod(methodName, messageCodec.messageClass());
      return new AskDescriptor(messageCodec, responseCodec, m);
    } catch (Throwable e) {
      throw new MojoError(e, Code.BAD_INPUT);
    }
  }

  boolean isMatch(AskDescriptor other) {
    return this.messageCodec.messageClass().equals(other.messageCodec.messageClass())
        && this.responseCodec.messageClass().equals(other.responseCodec.messageClass())
        && this.method.equals(other.method);
  }

  boolean copyFrom(AskDescriptor first) {
    if (!isMatch(first)) {
      return false;
    }
    id = first.id;
    messageId = first.messageId;
    responseId = first.responseId;
    runtime = first.runtime;
    services = first.services;
    return true;
  }
}
