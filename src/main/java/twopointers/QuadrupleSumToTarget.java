package twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Quadruple Sum to Target (medium) #
Given an array of unsorted numbers and a target number, find all unique quadruplets in it, whose sum is equal to the target number.

Example 1:
Input: [4, 1, 2, -1, 1, -3], target=1
Output: [-3, -1, 1, 4], [-3, 1, 1, 2]
Explanation: Both the quadruplets add up to the target.

Example 2:
Input: [2, 0, -1, 1, -2, 2], target=2
Output: [-2, 0, 2, 2], [-1, 0, 1, 2]
Explanation: Both the quadruplets add up to the target.
 */
public class QuadrupleSumToTarget {
    public static void main(String[] args) {
        System.out.println(QuadrupleSumToTarget.searchQuadruplets(new int[]{4, 1, 2, -1, 1, -3}, 1));
        System.out.println(QuadrupleSumToTarget.searchQuadruplets(new int[]{2, 0, -1, 1, -2, 2}, 2));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log(N) + N^3) => N^3, where N(log(N)) comes from sorting and N^3 comes from nested while loops
    Space complexity: O(N) from sorting
     */
    public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
        if (arr == null || arr.length < 4)
            return null;

        List<List<Integer>> quadruplets = new ArrayList<>();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 3; i++) {
            if (i > 0 && arr[i] == arr[i - 1])
                continue;

            for (int j = i + 1; j < arr.length - 2; j++) {
                if (j > i + 1 && arr[j] == arr[j - 1])
                    continue;

                search(arr, target, i, j, quadruplets);
            }
        }

        return quadruplets;
    }

    private static void search(int[] arr, int target, int i, int j, List<List<Integer>> quadruplets) {
        int left = j + 1;
        int right = arr.length - 1;

        while (left < right) {
            int currentSum = arr[i] + arr[j] + arr[left] + arr[right];

            if (currentSum == target) {
                quadruplets.add(Arrays.asList(arr[i], arr[j], arr[left], arr[right]));
                left++;
                right--;
            } else if (currentSum > target) {
                right--;
            } else {
                left++;
            }
        }
    }
}
