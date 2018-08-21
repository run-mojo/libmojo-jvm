package run.mojo.actor;

/**
 * Manages the supervision and lifecycle of SliceActor variants for
 * a single ActorType. The Kernel supervisors and schedules / spawns
 * SliceActor instances across all the slices.
 *
 * The Kernel can provide interception based services around the
 * Slice Actors such as throttling, circuit-breaking, etc.
 *
 * "Pool Pattern" is the idea of having a pool of Slice Actors spread
 * across all "Slices" / event-loops .
 *
 * "Singleton Pattern"
 *
 * "Named Pattern" describes a ActorType registry of "named" Slice Actors.
 * A name is any unique string of bytes which uniquely identifies a Slice
 * MetalActor.
 *
 * At the "Metal" are some ActorType kernel "primitives" that can be used
 * to construct more lanaguage "native" interfaces. We want to leverage
 * the native language features and programming model as much as it makes
 * sense to do so without sacrificing the ability to minimize GC pressure
 * by offloading as much bookkeeping to the Metal.
 *
 * Mojo Streaming uses ActorType kernels to control concurrency, parallelism,
 * rate, etc of Stream / Consumer Group messages.
 */
public class ActorTypeKernel {

}
