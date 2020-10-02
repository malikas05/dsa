package fastslowpointer;

import java.util.Arrays;

/*
Cycle in a Circular Array (hard) #
We are given an array containing positive and negative numbers. Suppose the array contains a number ‘M’ at a particular index.
Now, if ‘M’ is positive we will move forward ‘M’ indices and if ‘M’ is negative move backwards ‘M’ indices.
You should assume that the array is circular which means two things:
If, while moving forward, we reach the end of the array, we will jump to the first element to continue the movement.
If, while moving backward, we reach the beginning of the array, we will jump to the last element to continue the movement.
Write a method to determine if the array has a cycle. The cycle should have more than one element and should follow one direction which means the cycle should not contain both forward and backward movements.

Example 1:
Input: [1, 2, -1, 2, 2]
Output: true
Explanation: The array has a cycle among indices: 0 -> 1 -> 3 -> 0

Example 2:
Input: [2, 2, -1, 2]
Output: true
Explanation: The array has a cycle among indices: 1 -> 3 -> 1

Example 3:
Input: [2, 1, -1, -2]
Output: false
Explanation: The array does not have any cycle.
 */
public class CircularArrayLoop {
    public static void main(String[] args) {
        System.out.println(CircularArrayLoop.loopExists2(new int[] { 1, 2, -1, 2, 2 }));
        System.out.println(CircularArrayLoop.loopExists2(new int[] { 2, 2, -1, 2 }));
        System.out.println(CircularArrayLoop.loopExists2(new int[] { 2, 1, -1, -2 }));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N^2)
    Space complexity: O(1)
    */
    public static boolean loopExists(int[] arr) {
        if (arr == null || arr.length == 0)
            return false;

        for (int i = 0; i < arr.length; i++) {
            int fast = i, slow = i;
            boolean isForward = arr[i] >= 0;

            do {
                slow = findNextIndex(arr, isForward, slow);
                fast = findNextIndex(arr, isForward, fast);

                if (fast != -1)
                    fast = findNextIndex(arr, isForward, fast);
            } while (slow != -1 && fast != -1 && slow != fast);

            if (slow != -1 && slow == fast)
                return true;
        }

        return false;
    }

    private static int findNextIndex(int[] arr, boolean isForward, int currentIndex) {
        boolean direction = arr[currentIndex] >= 0;
        if (isForward != direction)
            return -1;

        int nextIndex = (currentIndex + arr[currentIndex]) % arr.length;
        if (nextIndex < 0)
            nextIndex += arr.length;

        if (currentIndex == nextIndex)
            return -1;

        return nextIndex;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N) - here we are employing Dynamic Programming to save the results to avoid round trips
    */
    public static boolean loopExists2(int[] arr) {
        if (arr == null || arr.length == 0)
            return false;

        int[] dp = new int[arr.length];
        Arrays.fill(dp, -2);

        for (int i = 0; i < arr.length; i++) {
            int fast = i, slow = i;
            boolean isForward = arr[i] >= 0;

            do {
                slow = dp[slow] < -1 ? dp[slow] = findNextIndex(arr, isForward, slow) : dp[slow];
                fast = dp[fast] < -1 ? dp[fast] = findNextIndex(arr, isForward, fast) : dp[fast];

                if (fast != -1) {
                    fast = dp[fast] < -1 ? dp[fast] = findNextIndex(arr, isForward, fast) : dp[fast];
                }
            } while (slow != -1 && fast != -1 && slow != fast);

            if (slow != -1 && slow == fast)
                return true;
        }

        return false;
    }
}
