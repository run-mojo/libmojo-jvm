package run.mojo.stream;

/**
 * Native record format. We can let Redis know that we are happy to speak
 * natively with records. In listpack format an entire record is a single
 * allocation which is managed in the native side devoid of Java GC concerns.
 */
public class Listpack {

}
