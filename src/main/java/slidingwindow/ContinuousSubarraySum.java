package slidingwindow;

import java.util.Arrays;

/*
Given an unsorted array of non-negative integers.
Return the start and end indices of a continuous subarray which adds to a given number - S.

Example 1:
[1, 2, 3, 7, 5], 12 => [1, 3]

Example 2:
[5, 5, 1, 3, 7, 5], 12 => [4, 5]

Example 3:
[3, 3, 3, 1, 1], 8 => [1, 4]
 */
public class ContinuousSubarraySum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(contiguousSubarrayForS(new int[]{1, 2, 3, 7, 5}, 12)));
        System.out.println(Arrays.toString(contiguousSubarrayForS(new int[]{5, 5, 1, 3, 7, 5}, 12)));
        System.out.println(Arrays.toString(contiguousSubarrayForS(new int[]{3, 3, 3, 1, 1}, 8)));
    }

    // [5, 1, 2, 3, 7, 5], 12 => (1, 4)
    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
    */
    public static int[] contiguousSubarrayForS(int[] array, int S) {
        if (array == null || array.length == 0 || S < 0)
            return new int[]{-1, -1};

        int windowStart = 0;
        int currentSum = 0;

        for (int windowEnd = 0; windowEnd < array.length; windowEnd++) {
            currentSum += array[windowEnd];

            if (currentSum == S) {
                return new int[]{windowStart, windowEnd};
            } else if (currentSum > S) {
                while (windowStart < windowEnd && currentSum > S) {
                    currentSum -= array[windowStart++];

                    if (currentSum == S) {
                        return new int[]{windowStart, windowEnd};
                    }
                }
            }
        }

        return new int[]{-1, -1};
    }
}
