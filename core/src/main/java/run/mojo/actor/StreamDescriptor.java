package run.mojo.actor;

import java.lang.reflect.Method;
import run.mojo.MojoError;
import run.mojo.MojoError.Code;
import run.mojo.message.MessageCodec;

/**
 *
 */
public class StreamDescriptor {

  MessageCodec messageCodec;
  Method method;
  ActorDescriptor actor;
  int id;
  int messageId;
  ActorRuntime runtime;
  ActorService[] services;

  private StreamDescriptor(MessageCodec messageCodec, Method method) {
    this.messageCodec = messageCodec;
    this.method = method;
  }

  public static StreamDescriptor create(MessageCodec messageCodec, Class actorClass,
      String method) {
    try {
      Method m = actorClass.getDeclaredMethod(method, messageCodec.messageClass());
      return new StreamDescriptor(messageCodec, m);
    } catch (Throwable e) {
      throw new MojoError(e, Code.BAD_INPUT);
    }
  }

  boolean isMatch(StreamDescriptor other) {
    return this.messageCodec.messageClass().equals(other.messageCodec.messageClass())
        && this.method.equals(other.method);
  }

  boolean copyFrom(StreamDescriptor first) {
    if (!isMatch(first)) {
      return false;
    }
    id = first.id;
    messageId = first.messageId;
    runtime = first.runtime;
    services = first.services;
    return true;
  }
}
