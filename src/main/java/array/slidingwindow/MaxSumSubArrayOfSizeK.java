package array.slidingwindow;

public class MaxSumSubArrayOfSizeK {
    /*
    Input: [2, 3, 4, 1, 5], k=2
    Output: 7
    Explanation: Subarray with maximum sum is [3, 4].
     */
    public static void main(String[] args) {
        System.out.println(findMaxSumSubArray(2, new int[] {2, 3, 4, 1, 5}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
    */
    public static int findMaxSumSubArray(int k, int[] arr) {
        if (k <= 0 || arr == null || arr.length == 0)
            return -1;

        int maxSum = 0;
        int currentSum = 0;
        int slidingIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            if (i >= k - 1) {
                maxSum = Math.max(maxSum, currentSum);
                currentSum -= arr[slidingIndex++];
            }
        }

        return maxSum;
    }
}
