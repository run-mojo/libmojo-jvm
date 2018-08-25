package run.mojo.resp;

import run.mojo.mem.Box;

/**
 * Wrapper around the Metal RespValue type. It represents a Redis value.
 * Should never hold onto RespValue instances as it points to native memory.
 * Instead it should be utilized as an intermediate format from the raw
 * network stream to a java specific representation. This is a framework
 * level API in general, so app developers will likely never deal with it.
 */
public class RespValue {
  public static final RespValue NIL_VALUE = new RespValue();

  public static final int NIL = 0;
  public static final int ARRAY = 1;
  public static final int BULK = 2;
  public static final int ERROR = 3;
  public static final int INT = 4;
  public static final int SIMPLE = 5;

  public int type() {
    return NIL;
  }

  public boolean isNil() {
    return false;
  }

  public int length() {
    return 0;
  }

  public RespValue valueAt(int index) {
    return NIL_VALUE;
  }

  public long toInt() {
    return 0L;
  }

  public long toInt(long or) {
    return or;
  }

  public String toString() {
    return "";
  }

  public enum Type {
    NIL,
    ARRAY,
    BULK,
    ERROR,
    INT,
    SIMPLE,
  }

  public static RespValue of(String value) {
    return NIL_VALUE;
  }

  public static RespValue of(boolean value) {
    return NIL_VALUE;
  }

  public static RespValue of(byte value) {
    return NIL_VALUE;
  }

  public static RespValue of(char value) {
    return NIL_VALUE;
  }

  public static RespValue of(short value) {
    return NIL_VALUE;
  }

  public static RespValue of(int value) {
    return NIL_VALUE;
  }

  public static RespValue of(long value) {
    return NIL_VALUE;
  }

  public static RespValue of(float value) {
    return NIL_VALUE;
  }

  public static RespValue of(double value) {
    return NIL_VALUE;
  }

  public static RespValue of(String[] value) {
    return NIL_VALUE;
  }
}
