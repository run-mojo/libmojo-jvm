package run.mojo.stream;

import run.mojo.Promise;

/**
 *
 */
public interface QueueProducer<IN, REPLY> {
  Promise<Receipt> tell(IN request);

  /**
   *
   * @param job
   * @return
   */
  Promise<ACKReply<REPLY>> tellJob(Job job);

  /**
   *
   * @param request
   * @return
   */
  Promise<ACKReply<REPLY>> ask(IN request);

  /**
   *
   * @param job
   * @return
   */
  Promise<ACKReply<REPLY>> askJob(Job job);
}
