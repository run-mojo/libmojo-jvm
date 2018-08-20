package run.mojo.stream;

import java.util.Objects;

/**
 *
 */
public class RecordID {
  public final long ms;
  public final long seq;

  public RecordID(long ms, long seq) {
    this.ms = ms;
    this.seq = seq;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecordID recordID = (RecordID) o;
    return ms == recordID.ms &&
        seq == recordID.seq;
  }

  @Override
  public int hashCode() {

    return Objects.hash(ms, seq);
  }
}
