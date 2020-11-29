package subsets;

import java.util.*;

/*
Problem Statement #
Given a set of numbers that might contain duplicates, find all of its distinct subsets.

Example 1:
Input: [1, 3, 3]
Output: [], [1], [3], [1,3], [3,3], [1,3,3]

Example 2:
Input: [1, 5, 3, 3]
Output: [], [1], [5], [3], [1,5], [1,3], [5,3], [1,5,3], [3,3], [1,3,3], [3,3,5], [1,5,3,3]
[1, 2, 3, 3, 5]
[[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3],
 */
public class SubsetWithDuplicates {
    /*
    - Complexity Analysis:
    Time complexity: O(N * 2^N)
    Space complexity: O(N * 2^N)
     */
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());
        subsets.add(Arrays.asList(nums[0]));
        Arrays.sort(nums);
        int prevSubsetStartIndex = 1;

        for (int i = 1; i < nums.length; i++) {
            int j = 0;
            if (nums[i] == nums[i - 1]) {
                j = prevSubsetStartIndex;
            }

            int subsetSize = subsets.size();
            prevSubsetStartIndex = subsetSize;
            while (j < subsetSize) {
                ArrayList<Integer> newSubset = new ArrayList<>(subsets.get(j));
                newSubset.add(nums[i]);
                subsets.add(newSubset);

                j++;
            }
        }
        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = SubsetWithDuplicates.findSubsets(new int[] { 1, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = SubsetWithDuplicates.findSubsets(new int[] { 1, 5, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }
}
