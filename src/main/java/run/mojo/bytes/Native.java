package run.mojo.bytes;

/**
 *
 */
class Native {
  static native long with_capacity(long capacity);

  static native long create(long capacity);

  static native long len(long ptr);

  static native long capacity(long ptr);

  static native long freeze(long ptr);

  static native long splitOff(long ptr, long at);

  static native long take(long ptr);

  static native long splitTo(long ptr, long at);

  static native void truncate(long ptr, long len);

  static native void advance(long ptr, long len);

  static native void clear(long ptr);

  static native void resize(long ptr, long newLen, byte value);

  static native void setLen(long ptr, long len);
}
