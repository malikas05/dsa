package fastslowpointer;

import java.util.HashSet;
import java.util.Set;

/*
Problem Statement #
Any number will be called a happy number if, after repeatedly replacing it with a number equal to the sum of the square of all of its digits, leads us to number ‘1’.
All other (not-happy) numbers will never reach ‘1’. Instead, they will be stuck in a cycle of numbers which does not include ‘1’.
 */
public class HappyNumber {
    public static void main(String[] args) {
        System.out.println(HappyNumber.find(23));
        System.out.println(HappyNumber.find(12));
    }

    public static boolean find(int num) {
        int fast = num, slow = num;

        do {
            fast = sumOfSquareDigits(sumOfSquareDigits(fast));
            slow = sumOfSquareDigits(slow);
        } while (fast != slow);

        return fast == 1;
    }

    private static int sumOfSquareDigits(int num) {
        int sum = 0;
        while (num != 0) {
            sum += Math.pow(num % 10, 2);
            num /= 10;
        }

        return sum;
    }

    public static boolean find2(int num) {
        Set<Integer> visitedNumbers = new HashSet<>();

        int tempSum = 0;
        while (!visitedNumbers.contains(num)) {
            visitedNumbers.add(num);

            while (num != 0) {
                tempSum += Math.pow(num % 10, 2);
                num /= 10;
            }

            num = tempSum;
            tempSum = 0;

            if (num == 1)
                return true;
        }

        return false;
    }
}
