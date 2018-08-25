package run.mojo.message;

/**
 *
 */
public interface MessageFactory<M> {

  MessageFactory<Message> DEFAULT = Message::from;

  M create(long typeId, int encoding, long pointer);
}
