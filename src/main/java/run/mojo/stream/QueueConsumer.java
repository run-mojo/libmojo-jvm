package run.mojo.stream;

import run.mojo.Promise;

/**
 *
 */
public interface QueueConsumer {
  void process(Job job, Promise<ACK> promise);
}
