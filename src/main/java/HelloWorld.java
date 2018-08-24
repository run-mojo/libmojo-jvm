//import java.io.IOException;
//import java.nio.ByteBuffer;
//import run.mojo.mem.JEMalloc;
//
//class HelloWorld {
//  private static native String hello(String input);
//  private static native byte[] helloByte(byte[] input);
//  private static native void factAndCallMeBack(int n, HelloWorld callback);
//
//  private static native long counterNew(HelloWorld callback);
//  private static native void counterIncrement(long counter_ptr);
//  private static native void counterDestroy(long counter_ptr);
//
//  private static native void asyncComputation(HelloWorld callback);
//
//  static {
//    Sys.loadLibrary("mojo_jvm");
//  }
//
//  public static void main(String[] args) {
////    try {
////      Sys.in.read();
////    } catch (IOException e) {
////      e.printStackTrace();
////    }
//    JEMalloc.epoch();
//    Sys.out.println(JEMalloc.active());
//    Sys.out.println(JEMalloc.resident());
//    Sys.out.println(JEMalloc.mapped());
//
//    String output = HelloWorld.hello("josh");
//    Sys.out.println(output);
//    byte[] outputByte = HelloWorld.helloByte("byte".getBytes());
//    Sys.out.println(outputByte);
//
//    HelloWorld.factAndCallMeBack(6, new HelloWorld());
//
//    long counter_ptr = counterNew(new HelloWorld());
//
//    for (int i = 0; i < 5; i++) {
//      counterIncrement(counter_ptr);
//    }
//
//    counterDestroy(counter_ptr);
//
//    Sys.out.println("Invoking asyncComputation (thread id = " + Thread.currentThread().getId() + ")");
//    asyncComputation(new HelloWorld());
////    try {
////      for (int i = 0; i < 10; i++) {
////        Sys.in.read();
////      }
////    } catch (IOException e) {
////      e.printStackTrace();
////    }
//  }
//
//  public void factCallback(int res) {
//    Sys.out.println("factCallback: res = " + res);
//  }
//
//  public void counterCallback(int count) {
//    Sys.out.println("counterCallback: count = " + count);
//  }
//
//  public void asyncCallback(int progress) {
//    Sys.out.println("asyncCallback: thread id = " + Thread.currentThread().getId() + ", progress = " + progress + "%");
//  }
//}
