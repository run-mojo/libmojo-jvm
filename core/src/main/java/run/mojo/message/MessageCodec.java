package run.mojo.message;

import io.protostuff.JsonIOUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.MsgpackIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 */
public interface MessageCodec<T> {

  static <T> MessageCodec<T> create(Class<T> clazz) {
    return new SchemaCodec<>(clazz);
  }

  Class<T> messageClass();

  T create();

  T read(Encoding encoding, InputStream in);

  void write(Encoding encoding, OutputStream out, T message);

  enum Encoding {
    PROTOBUF(0),
    JSON(1),
    MSGPACK(2),
    ;

    public final int code;

    Encoding(int code) {
      this.code = code;
    }
  }

  class SchemaCodec<T> implements MessageCodec<T> {

    static final ThreadLocal<LinkedBuffer> BUFFERS = new ThreadLocal<>();

    private final Class<T> clazz;
    private final Schema<T> schema;

    private SchemaCodec(Class<T> clazz) {
      this.clazz = clazz;
      this.schema = RuntimeSchema.getSchema(clazz);
    }

    @Override
    public Class<T> messageClass() {
      return clazz;
    }

    @Override
    public T create() {
      return schema.newMessage();
    }

    @Override
    public T read(Encoding encoding, InputStream in) {
      switch (encoding) {
        case PROTOBUF:
          return read(in);
        case JSON:
          return readJson(in);
        case MSGPACK:
          return readMsgpack(in);
      }

      return null;
    }

    @Override
    public void write(Encoding encoding, OutputStream out, T message) {
      switch (encoding) {
        case PROTOBUF:
          write(out, message);
        case JSON:
          writeJson(out, message);
        case MSGPACK:
          writeMsgpack(out, message);
      }
    }

    public T read(InputStream inputStream) {
      LinkedBuffer buffer = BUFFERS.get();
      if (buffer == null) {
        buffer = LinkedBuffer.allocate(8192);
      } else {
        buffer.clear();
      }
      final T message = schema.newMessage();
      try {
        ProtostuffIOUtil.mergeFrom(inputStream, message, schema, buffer);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return message;
    }

    public void write(OutputStream out, T message) {
      LinkedBuffer buffer = BUFFERS.get();
      if (buffer == null) {
        buffer = LinkedBuffer.allocate(8192);
      } else {
        buffer.clear();
      }
      try {
        ProtostuffIOUtil.writeTo(out, message, schema, buffer);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    public T readJson(InputStream inputStream) {
      final T message = schema.newMessage();
      try {
        JsonIOUtil.mergeFrom(inputStream, message, schema, false);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return message;
    }

    public void writeJson(OutputStream out, T obj) {
      try {
        JsonIOUtil.writeTo((OutputStream) null, obj, schema, false);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    public T readMsgpack(InputStream inputStream) {
      final T message = schema.newMessage();
      try {
        MsgpackIOUtil.mergeFrom(inputStream, message, schema, false);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return message;
    }

    public void writeMsgpack(OutputStream out, T obj) {
      try {
        MsgpackIOUtil.writeTo((OutputStream) null, obj, schema, false);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
