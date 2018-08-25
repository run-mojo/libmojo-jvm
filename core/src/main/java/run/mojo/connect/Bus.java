package run.mojo.connect;

/**
 * A bus is used for inter-node communication. Internally it's just
 * a Stream used as a reliable Pub/Sub where the "Read Index" is
 * maintained on the node. This allows for stateless Redis access.
 *
 * When a node goes down slice/d will orphan the Stream which can
 * be deleted at that point. The side effect of this model is a
 * complete history of the messages sent and received.
 *
 * Each node must maintain a Bus with each Slice. When using a stream
 * as a Queue the "Caller ID" will point to a Node Bus on the local
 * Redis node.
 */
public class Bus {
}
