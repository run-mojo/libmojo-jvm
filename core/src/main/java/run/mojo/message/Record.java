package run.mojo.message;

/**
 *
 */
public class Record {
  public static final byte ID = 1;
  public static final byte ID_2 = 2;
  public static final byte DEADLINE = 3;
  public static final byte TYPE_ID = 4;
  public static final byte TYPE_NAME = 5;
  public static final byte AUTH = 6;

  public static final byte EXTRA_HEADERS = 10;

  public static final byte MESSAGE_NIL = 20;
  public static final byte MESSAGE_STR = 21;
  public static final byte MESSAGE_INT = 22;
  public static final byte MESSAGE_FLOAT = 23;

  public static final byte MESSAGE_PROTOBUF = 30;
  public static final byte MESSAGE_JSON = 31;
  public static final byte MESSAGE_MSGPACK = 32;
  public static final byte MESSAGE_YAML = 33;
  public static final byte MESSAGE_XML = 34;
  public static final byte MESSAGE_FLATBUFFER = 35;

  public static final byte MESSAGE_JOBJECT = 50;


  /**
   * Size of allocation in bytes.
   */
  public int size() {
    return 0;
  }

  /**
   *
   * @return
   */
  public int count() {
    return 0;
  }
}
