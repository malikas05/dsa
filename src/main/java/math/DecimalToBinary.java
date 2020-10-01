package math;

public class DecimalToBinary {
    public static int decimalToBinary(int n) {
        if (n < 2)
            return n;

        return n % 2 + decimalToBinary(n / 2) * 10;
    }
}
