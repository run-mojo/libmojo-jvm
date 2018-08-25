package run.mojo.mem;

/**
 *
 *
 * Here's the skinny. We track mem allocations from the 2 worlds and
 * keep a running "score".
 *
 * First Tenant: OWNERSHIP
 *
 * Long held memory should exist in the runtime / Drivers, MetalActor or a Stream Processor.
 * Anything outside of that becomes rogue and the developer should fix in
 * the channel.
 */
public class Heap {

}
