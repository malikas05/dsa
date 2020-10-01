package math;

import java.util.Arrays;
import java.util.List;

public class FindMissing {

    public static void main(String[] args) {
        System.out.println(findMissing(Arrays.asList(3, 7, 1, 2, 8, 4, 5)));
    }

    /*
    - Concept: The sum formula for the sequence of numbers up to the given number is the following: n * (n + 1) / 2
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    static int findMissing(List<Integer> input) {
        if (input.isEmpty()) {
            return -1;
        }

        int maxValue = -1;
        int currentSum = 0;

        for (int value : input) {
            maxValue = Math.max(value, maxValue);
            currentSum += value;
        }

        int realSum = maxValue * (maxValue + 1) / 2;

        return realSum - currentSum;
    }
}
