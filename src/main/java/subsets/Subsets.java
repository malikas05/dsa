package subsets;

import java.util.ArrayList;
import java.util.List;

/*
Problem Statement #
Given a set with distinct elements, find all of its distinct subsets.

Example 1:

Input: [1, 3]
Output: [], [1], [3], [1,3]
Example 2:

Input: [1, 5, 3]
Output: [], [1], [5], [3], [1,5], [1,3], [5,3], [1,5,3]
 */
public class Subsets {
    /*
    - Complexity Analysis:
    Time complexity: O(N * 2^N)
    Space complexity: O(N * 2^N)
     */
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());

        for (int num : nums) {
            int n = subsets.size();

            for (int j = 0; j < n; j++) {
                List<Integer> newSubset = new ArrayList<>(subsets.get(j));
                newSubset.add(num);

                subsets.add(newSubset);
            }
        }

        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = Subsets.findSubsets(new int[]{1, 3});
        System.out.println("Here is the list of subsets: " + result);

        result = Subsets.findSubsets(new int[]{1, 5, 3});
        System.out.println("Here is the list of subsets: " + result);
    }
}
