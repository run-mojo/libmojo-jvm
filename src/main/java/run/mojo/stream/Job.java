package run.mojo.stream;

/**
 *
 */
public class Job {
  private long handle;
  private long deadline;
  private char slot = Character.MAX_VALUE;
  private Record record;

  public long deadline() {
    return 0L;
  }

  public char slot() {
    return 0;
  }

  public long ms() {
    return 0L;
  }

  public long seq() {
    return 0L;
  }

  public RecordID id() {
    return new RecordID(ms(), seq());
  }

  public Record record() {
    return null;
  }

  public ACK ack() {
    return new ACK();
  }

  public static Job request(Record record) {
    return new Job();
  }

  public static Job request(Record record, long deadline) {
    return new Job();
  }

  public static Job request(Record record, long deadline, char slot) {
    final Job job = new Job();
    job.deadline = deadline;
    job.slot = slot;
    return job;
  }
}
