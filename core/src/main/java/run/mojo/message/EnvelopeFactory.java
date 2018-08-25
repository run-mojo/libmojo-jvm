package run.mojo.message;

/**
 *
 */
public interface EnvelopeFactory<H, M> {
  Envelope<H, M> create(Message message);
}
