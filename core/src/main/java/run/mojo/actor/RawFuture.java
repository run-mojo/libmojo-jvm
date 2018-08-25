package run.mojo.actor;

import java.util.concurrent.CompletableFuture;

/**
 * Wraps a metal oneshot channel. It extends "CompleteableFuture" and can take full advantage of the
 * standard API. On "complete" oneshot.Sender.send() is invoked atomically a single time.
 *
 * There are two sides to this future. Underneath is a "oneshot" channel. The "receiver" is managed
 * as a "Metal Future" that is scheduled in the Reactor waiting for the "sender" to send it's "one"
 * message.
 */
@SuppressWarnings("all")
public class RawFuture<R> extends CompletableFuture<R> {

  /**
   * Creates a new ActorFuture.
   */
  public native static long alloc(long kind, long handle);

  /**
   *
   * @param handle
   * @return
   */
  public native static long drop(long handle);

  /**
   *
   * @param handle
   * @param message
   */
  public native static void send(long handle, long message);

  /**
   * libmojo Callback
   */
  public static void canceled(long kind, long handle) {
    System.out.println("RawPointer.canceled");
  }

//  static final Schema<Foo> schema = RuntimeSchema.getSchema(Foo.class);
//  static final LinkedBuffer buffer = LinkedBuffer.allocate(512);
//  static byte[] protostuff;

  /**
   * libmojo Callback
   */
  public static void finish(long kind, long handle, long message) {
//    final LinkedBuffer buffer = LinkedBuffer.allocate(512);
//    Foo foo = new Foo("foo", 1);
//    byte[] protostuff;
//    try {
//      protostuff = ProtostuffIOUtil.toByteArray(foo, schema, buffer);
//    } finally {
//      buffer.clear();
//    }
//
//    Foo fooParsed = schema.newMessage();
//    ProtostuffIOUtil.mergeFrom(protostuff, fooParsed, schema);
//    System.out.println("RawPointer.finish");
  }

}
