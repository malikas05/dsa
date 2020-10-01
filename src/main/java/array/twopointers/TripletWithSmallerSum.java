package array.twopointers;

import java.util.Arrays;

/*
Problem Statement #
Given an array arr of unsorted numbers and a target sum, count all triplets in it such that arr[i] + arr[j] + arr[k] < target where i, j, and k are three different indices.
Write a function to return the count of such triplets.

Example 1:
Input: [-1, 0, 2, 3], target=3
Output: 2
Explanation: There are two triplets whose sum is less than the target: [-1, 0, 3], [-1, 0, 2]

Example 2:
Input: [-1, 4, 2, 1, 3], target=5
Output: 4
Explanation: There are four triplets whose sum is less than the target: [-1, 1, 4], [-1, 1, 3], [-1, 1, 2], [-1, 2, 3]
 */
public class TripletWithSmallerSum {
    public static void main(String[] args) {
        System.out.println(TripletWithSmallerSum.searchTriplets(new int[]{-1, 0, 2, 3}, 3));
        System.out.println(TripletWithSmallerSum.searchTriplets(new int[]{-1, 4, 2, 1, 3}, 5));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log(N) + N^2) => N^2, where N(log(N)) comes from sorting and N^2 comes from nested while loop
    Space complexity: O(N) from sorting
     */
    public static int searchTriplets(int[] arr, int target) {
        int count = -1;
        if (arr == null || arr.length < 3)
            return count;

        count = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            count += search(arr, target, i + 1, arr[i]);
        }

        return count;
    }

    private static int search(int[] arr, int target, int left, int leftValue) {
        int count = 0;
        int right = arr.length - 1;
        while (left < right) {
            int currentSum = leftValue + arr[left] + arr[right];
            if (currentSum < target) {
                count += right - left;
                left++;
            } else {
                right--;
            }
        }

        return count;
    }
}
