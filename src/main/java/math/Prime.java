package math;

public class Prime {
    public static void main(String[] args) {
        System.out.println(isPrime(7, 7 / 2));
    }

    public static Object isPrime(int num, int i) {
        if (num == 0 || num == 1)
            return false;

        if (i == 1)
            return true;

        if (num % i == 0)
            return false;

        return isPrime(num, --i);
    }
}
