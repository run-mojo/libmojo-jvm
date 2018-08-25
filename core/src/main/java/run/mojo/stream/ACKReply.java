package run.mojo.stream;

/**
 * Helper for ACK with a typed Reply.
 */
public class ACKReply<T> {
  public ACK ack;
  public T reply;

  public ACKReply(ACK ack, T reply) {
    this.ack = ack;
    this.reply = reply;
  }
}
