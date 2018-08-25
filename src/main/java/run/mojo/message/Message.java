package run.mojo.message;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import java.io.InputStream;
import run.mojo.example.Person;

/**
 * Messages are used for passing between
 */
public class Message<T> {

  private long raw;
  private T pojo;
  private Encoding encoding;

  public static void main(String[] args) {
    roundTrip();
  }

  static void roundTrip() {
    Foo foo = new Foo("foo", 1);
    Person person = Person.newBuilder().setEmail("test@test.com").setName("Bob").setId(0).build();
    final Schema<Person> personSchema = Person.getSchema();


    // this is lazily created and cached by RuntimeSchema
    // so its safe to call RuntimeSchema.getSchema(Foo.class) over and over
    // The getSchema method is also thread-safe
    Schema<Foo> schema = RuntimeSchema.getSchema(Foo.class);

    // Re-use (manage) this buffer to avoid allocating on every serialization
    LinkedBuffer buffer = LinkedBuffer.allocate(512);

    // ser
    final byte[] protostuff;
    try {
      protostuff = ProtostuffIOUtil.toByteArray(foo, schema, buffer);
    } finally {
      buffer.clear();
    }

    for (int i = 0; i < 30; i++) {
      long start = System.currentTimeMillis();
      for (int x = 0; x < 1000000; x++) {
        // deser
        Foo fooParsed = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(protostuff, fooParsed, schema);
      }
      long elapsed = System.currentTimeMillis() - start;
      System.out.println(elapsed);
    }
  }

  public enum Encoding {

  }

  public static final class Foo {

    String name;
    int id;

    public Foo(String name, int id) {
      this.name = name;
      this.id = id;
    }
  }
}
