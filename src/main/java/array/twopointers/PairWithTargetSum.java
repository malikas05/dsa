package array.twopointers;

import java.util.Arrays;

/*
Problem Statement #
Given an array of sorted numbers and a target sum, find a pair in the array whose sum is equal to the given target.
Write a function to return the indices of the two numbers (i.e. the pair) such that they add up to the given target.

Example 1:
Input: [1, 2, 3, 4, 6], target=6
Output: [1, 3]
Explanation: The numbers at index 1 and 3 add up to 6: 2+4=6

Example 2:
Input: [2, 5, 9, 11], target=11
Output: [0, 2]
Explanation: The numbers at index 0 and 2 add up to 11: 2+9=11
 */
public class PairWithTargetSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(PairWithTargetSum.search(new int[]{1, 2, 3, 4, 6}, 6)));
        System.out.println(Arrays.toString(PairWithTargetSum.search(new int[]{2, 5, 9, 11}, 11)));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static int[] search(int[] arr, int targetSum) {
        if (arr == null || arr.length == 0)
            return new int[]{-1, -1};

        int[] result = new int[2];
        int leftPointer = 0, rightPointer = arr.length - 1, tempSum = 0;
        while (leftPointer < rightPointer) {
            tempSum = arr[leftPointer] + arr[rightPointer];

            if (tempSum == targetSum) {
                result[0] = leftPointer;
                result[1] = rightPointer;
                return result;
            } else if (tempSum < targetSum) {
                leftPointer++;
            } else {
                rightPointer--;
            }
        }

        return new int[]{-1, -1};
    }
}
