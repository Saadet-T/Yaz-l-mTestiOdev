public class Example {
  public static void main(String[] args) {
    int x = 5;
    int y = 5;
    String s1 = "hello";
    String s2 = "hello";
    String s3 = new String("hello");
    
    if (x == y) {
      System.out.println("x is equal to y");
    }
    
    if (s1 == s2) {
      System.out.println("s1 is the same object as s2");
    }
    
    if (s1 == s3) {
      System.out.println("s1 is the same object as s3");
    } else {
      System.out.println("s1 is not the same object as s3");
    }
    
    Object obj1 = new Object();
    Object obj2 = new Object();
    
    if (obj1 == obj2) {
      System.out.println("obj1 is the same object as obj2");
    } else {
      System.out.println("obj1 is not the same object as obj2");
    }
  }
}