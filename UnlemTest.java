public class OperatorExample {
    public static void main(String[] args) {
        int a = 10;
        int b = 5;
        int c = 20;

        int result1 = (a + b) * c;
        System.out.println("(a + b) * c = " + result1);

        int result2 = a % b;
        System.out.println("a % b = " + result2);

        boolean result3 = (a > b) && (c > a);
        System.out.println("(a > b) && (c > a) = " + result3);

        boolean result4 = (a < b) || (c > a);
        System.out.println("(a < b) || (c > a) = " + result4);

        boolean result5 = !(a > b);
        System.out.println("!(a > b) = " + result5);
    }
}
