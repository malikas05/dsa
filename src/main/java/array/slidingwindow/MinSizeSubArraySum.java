package array.slidingwindow;

public class MinSizeSubArraySum {

    /*
    Input: [3, 4, 1, 1, 6], S=8
    Output: 3
    Explanation: Smallest subarrays with a sum greater than or equal to '8' are [3, 4, 1] or [1, 1, 6].
     */

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
