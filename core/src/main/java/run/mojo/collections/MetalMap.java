package run.mojo.collections;

import run.mojo.mem.Box;

/**
 * Alternative to HashMap, LinkedHashMap, SortedMap all in one.
 * It is implemented as a Radix Tree (rax) with prefix compression.
 * It is used within Redis itself.
 */
public class MetalMap extends Box {
}
