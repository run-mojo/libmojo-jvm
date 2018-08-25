package run.mojo.message;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import java.io.InputStream;

/**
 * Messages are used for passing between
 */
public class Message<T> {
  public final long id;
  public final int flags;
  public final Encoding encoding;
  public final long pointer;

  private Message(long id, int flags, Encoding encoding, long pointer) {
    this.id = id;
    this.flags = flags;
    this.encoding = encoding;
    this.pointer = pointer;
  }

  public static Message from(long typeId, int encoding, long pointer) {

    if (typeId == -1) {

    }

    return null;
  }

  public enum Encoding {

    /**
     *
     */
    NIL(0),
    STR(1),
    INT(2),
    BYTES(3),
    PROTOBUF(4),
    JSON(5),
    MSGPACK(6),
    YAML(7),
    XML(8),
    LISTPACK(9),

    /**
     * Global Ref to a Java object (POJO).
     */
    JOBJECT(20),
    /**
     * Java callback.
     */
    JCALLBACK(21),
    /**
     * Envelopes have 2 parts, a header and a message. Headers are a compact map of keys and
     * values encoded as a listpack.
     */
    ENVELOPE(30),
    ;

    public final int code;

    Encoding(int code) {
      this.code = code;
    }

    /**
     *
     * @return
     */
    public boolean isDependent() {
      return false;
    }

    /**
     *
     * @return
     */
    public boolean isIndependent() {
      return false;
    }

    public Encoding from(int code) {
      switch (code) {
        case 0:
          return Encoding.NIL;
        case 1:
          return Encoding.STR;
        case 2:
          return Encoding.INT;
      }

      return Encoding.NIL;
    }

    public long intOr(Message message, long defaultValue) {
      if (this == INT) {
        return message.pointer;
      } else {
        return defaultValue;
      }
    }
  }

//  private long raw;
//  private T pojo;
//  private Encoding encoding;
//
//  public static void main(String[] args) {
//    roundTrip();
//  }
//
//  static void roundTrip() {
//    Foo foo = new Foo("foo", 1);
//    Person person = Person.newBuilder().setEmail("test@test.com").setName("Bob").setId(0).build();
//    final Schema<Person> personSchema = Person.getSchema();
//
//
//    // this is lazily created and cached by RuntimeSchema
//    // so its safe to call RuntimeSchema.getSchema(Foo.class) over and over
//    // The getSchema method is also thread-safe
//    Schema<Foo> schema = RuntimeSchema.getSchema(Foo.class);
//
//    // Re-use (manage) this buffer to avoid allocating on every serialization
//    LinkedBuffer buffer = LinkedBuffer.allocate(512);
//
//    // ser
//    final byte[] protostuff;
//    try {
//      protostuff = ProtostuffIOUtil.toByteArray(foo, schema, buffer);
//    } finally {
//      buffer.clear();
//    }
//
//    for (int i = 0; i < 30; i++) {
//      long start = System.currentTimeMillis();
//      for (int x = 0; x < 1000000; x++) {
//        // deser
//        Foo fooParsed = schema.newMessage();
//        ProtostuffIOUtil.mergeFrom(protostuff, fooParsed, schema);
//      }
//      long elapsed = System.currentTimeMillis() - start;
//      System.out.println(elapsed);
//    }
//  }
//
//  public enum Encoding {
//
//  }
//
//  public static final class Foo {
//
//    String name;
//    int id;
//
//    public Foo(String name, int id) {
//      this.name = name;
//      this.id = id;
//    }
//  }
}
