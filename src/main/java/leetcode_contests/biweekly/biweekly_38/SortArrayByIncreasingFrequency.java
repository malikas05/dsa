package leetcode_contests.biweekly.biweekly_38;

import java.util.*;

/*
1636. Sort Array by Increasing Frequency - https://leetcode.com/problems/sort-array-by-increasing-frequency/
Given an array of integers nums, sort the array in increasing order based on the frequency of the values.
If multiple values have the same frequency, sort them in decreasing order.
Return the sorted array.

Example 1:
Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.

Example 2:
Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.

Example 3:
Input: nums = [-1,1,-6,4,5,-6,1,4,1]
Output: [5,-1,4,4,-6,-6,1,1,1]

Constraints:
1 <= nums.length <= 100
-100 <= nums[i] <= 100
 */
public class SortArrayByIncreasingFrequency {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(frequencySort(new int[]{1, 1, 2, 2, 2, 3})));
        System.out.println(Arrays.toString(frequencySort(new int[]{2, 3, 1, 3, 2})));
        System.out.println(Arrays.toString(frequencySort(new int[]{-1, 1, -6, 4, 5, -6, 1, 4, 1})));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log(N))
    Space complexity: O(K * N) where K - is the number of max frequencies
     */
    public static int[] frequencySort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        int[] bucket = new int[201];
        for (int num : nums) {
            bucket[num + 100] += 1;
        }

        Map<Integer, List<Integer>> frequencyMap = new TreeMap<>();
        for (int i = 200; i >= 0; i--) {
            if (bucket[i] > 0) {
                if (!frequencyMap.containsKey(bucket[i])) {
                    frequencyMap.put(bucket[i], new ArrayList<>());
                }

                List<Integer> numbersByFrequency = frequencyMap.get(bucket[i]);
                numbersByFrequency.add(i - 100);
            }
        }

        int[] result = new int[nums.length];
        int i = 0;
        for (Map.Entry<Integer, List<Integer>> entry : frequencyMap.entrySet()) {
            int frequency = entry.getKey();
            for (int num : entry.getValue()) {
                for (int j = 0; j < frequency; j++) {
                    result[i++] = num;
                }
            }
        }

        return result;
    }
}
