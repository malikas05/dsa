package slidingwindow;

/*
Problem Statement #
Given an array of positive numbers and a positive number ‘S’, find the length of the smallest contiguous subarray whose sum is greater than or equal to ‘S’. Return 0, if no such subarray exists.

Example 1:
Input: [2, 1, 5, 2, 3, 2], S=7
Output: 2
Explanation: The smallest subarray with a sum great than or equal to '7' is [5, 2].

Example 2:
Input: [2, 1, 5, 2, 8], S=7
Output: 1
Explanation: The smallest subarray with a sum greater than or equal to '7' is [8].

Example 3:
Input: [3, 4, 1, 1, 6], S=8
Output: 3
Explanation: Smallest subarrays with a sum greater than or equal to '8' are [3, 4, 1] or [1, 1, 6].
 */
public class MinSizeSubArraySum {
    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
    */
    public static int findMinSubArray(int S, int[] arr) {
        if (S <= 0 || arr == null || arr.length == 0)
            return -1;

        int windowSum = 0;
        int windowStart = 0;
        int minLength = arr.length;

        for (int windowEnd = windowStart; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            while (windowSum >= S) {
                minLength = Math.min(minLength, windowEnd - windowStart + 1);
                windowSum -= arr[windowStart++];
            }
        }

        return minLength;
    }
}
