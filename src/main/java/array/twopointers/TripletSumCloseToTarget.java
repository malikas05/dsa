package array.twopointers;

import java.util.Arrays;

/*
Problem Statement #
Given an array of unsorted numbers and a target number, find a triplet in the array whose sum is as close to the target number as possible, return the sum of the triplet.
If there are more than one such triplet, return the sum of the triplet with the smallest sum.

Example 1:
Input: [-2, 0, 1, 2], target=2
Output: 1
Explanation: The triplet [-2, 1, 2] has the closest sum to the target.

Example 2:
Input: [-3, -1, 1, 2], target=1
Output: 0
Explanation: The triplet [-3, 1, 2] has the closest sum to the target.

Example 3:
Input: [1, 0, 1, 1], target=100
Output: 3
Explanation: The triplet [1, 1, 1] has the closest sum to the target.
 */
public class TripletSumCloseToTarget {
    public static void main(String[] args) {
        System.out.println(TripletSumCloseToTarget.searchTriplet(new int[]{-2, 0, 1, 2}, 2));
        System.out.println(TripletSumCloseToTarget.searchTriplet(new int[]{-3, -1, 1, 2}, 1));
        System.out.println(TripletSumCloseToTarget.searchTriplet(new int[]{1, 0, 1, 1}, 100));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log(N) + N^2) => N^2, where N(log(N)) comes from sorting and N^2 comes from nested while loop
    Space complexity: O(N) from sorting
     */
    public static int searchTriplet(int[] arr, int targetSum) {
        if (arr == null || arr.length < 3)
            return -1;

        int[] smallestSumDiff = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            search(arr, targetSum,i + 1, arr[i], smallestSumDiff);
        }

        return smallestSumDiff[0];
    }

    private static void search(int[] arr, int targetSum, int left, int leftValue, int[] smallestSumDiff) {
        int right = arr.length - 1;
        while (left < right) {
            int currentSum = leftValue + arr[left] + arr[right];
            int targetDiff = targetSum - currentSum;

            if (Math.abs(targetDiff) < smallestSumDiff[1]) {
                smallestSumDiff[0] = currentSum;
                smallestSumDiff[1] = Math.abs(targetDiff);
            }

            if (targetDiff > 0)
                left++;
            else
                right--;
        }
    }
}
