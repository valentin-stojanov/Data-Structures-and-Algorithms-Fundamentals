public class FibonacciNumber {
    static int counter = 0;
    public static void main(String[] args) {
        int fib = fibonacci(5);

        System.out.println(fib);
        System.out.println(counter);
    }

    private static int fibonacci(int n) {
        counter++;

        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
