package array.twopointers;

import java.util.ArrayList;
import java.util.List;

/*
Problem Statement #
Given an array with positive numbers and a target number, find all of its contiguous subarrays whose product is less than the target number.

Example 1:
Input: [2, 5, 3, 10], target=30
Output: [2], [5], [2, 5], [3], [5, 3], [10]
Explanation: There are six contiguous subarrays whose product is less than the target.

Example 2:
Input: [8, 2, 6, 5], target=50
Output: [8], [2], [8, 2], [6], [2, 6], [5], [6, 5]
Explanation: There are seven contiguous subarrays whose product is less than the target.
 */
public class SubarrayProductLessThanK {
    public static void main(String[] args) {
        System.out.println(SubarrayProductLessThanK.findSubarrays(new int[]{2, 5, 3, 10}, 30));
        System.out.println(SubarrayProductLessThanK.findSubarrays(new int[]{8, 2, 6, 5}, 50));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N^2)
    Space complexity: O(1)
     */
    public static List<List<Integer>> findSubarrays(int[] arr, int target) {
        List<List<Integer>> subarrays = new ArrayList<>();
        if (arr == null || arr.length == 0)
            return subarrays;

        for (int i = 0; i < arr.length; i++) {
            findSubarraysHelper(arr, target, i, subarrays);
        }

        return subarrays;
    }

    private static void findSubarraysHelper(int[] arr, int target, int left, List<List<Integer>> subarrays) {
        List<Integer> subarray = new ArrayList<>();
        if (arr[left] < target) {
            subarray.add(arr[left]);
            subarrays.add(new ArrayList<>(subarray));
        } else return;

        if (left < arr.length - 1) {
            int right = left + 1;
            int product = arr[left];

            while (right < arr.length) {
                product *= arr[right];
                if (product < target) {
                    subarray.add(arr[right]);
                    subarrays.add(new ArrayList<>(subarray));
                } else
                    break;

                right++;
            }
        }
    }
}
