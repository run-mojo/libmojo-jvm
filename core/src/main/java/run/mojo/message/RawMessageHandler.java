package run.mojo.message;

/**
 * Handles a Raw Mojo message.
 */
public interface RawMessageHandler {
  void handle(long id, int encoding, long pointer, Object jobject);
}
