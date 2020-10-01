package math;

public class DivideInteger {
    public static void main(String[] args) {
        System.out.println(integerDivide(40, 5));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(log N)
    Space complexity: O(1)
     */
    public static int integerDivide(int x, int y) {
        if (y == -1) {
            return -1;
        }

        if (x < y) {
            return 0;
        } else if (x == y) {
            return 1;
        } else if (y == 1) {
            return x;
        }

        int q = 1;
        int val = y;

        while (val < x) {
            val <<= 1;
            q <<= 1;
        }

        if (val > x) {
            val >>= 1;
            q >>= 1;

            return q + integerDivide(x - val, y);
        }

        return q;
    }
}
