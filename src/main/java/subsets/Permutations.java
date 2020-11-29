package subsets;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
Problem Statement #
Given a set of distinct numbers, find all of its permutations.
Permutation is defined as the re-arranging of the elements of the set. For example, {1, 2, 3} has the following six permutations:
{1, 2, 3}
{1, 3, 2}
{2, 1, 3}
{2, 3, 1}
{3, 1, 2}
{3, 2, 1}
If a set has ‘n’ distinct elements it will have n!n! permutations.

Example 1:
Input: [1,3,5]
Output: [1,3,5], [1,5,3], [3,1,5], [3,5,1], [5,1,3], [5,3,1]

[1]

[3,1]
[1,3]

[5,3,1]
[3,5,1]
[3,1,5]
[5,1,3]
[1,5,3]
[1,3,5]
 */
public class Permutations {
    /*
    - Complexity Analysis:
    Time complexity: O(N * N!)
    Space complexity: O(N * N!)
     */
    public static List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> permutations = new ArrayDeque<>();
        permutations.offer(new ArrayList<>());

        for (int num : nums) {
            int n = permutations.size();

            for (int i = 0; i < n; i++) {
                List<Integer> oldPermutation = permutations.poll();
                for (int j = 0; j <= oldPermutation.size(); j++) {
                    List<Integer> newPermutation = new ArrayList<>(oldPermutation);
                    newPermutation.add(j, num);

                    if (newPermutation.size() == nums.length) {
                        result.add(newPermutation);
                    } else {
                        permutations.offer(newPermutation);
                    }
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> findPermutations2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        findPermutationsRec(nums, 0, new ArrayList<>(), result);

        return result;
    }

    private static void findPermutationsRec(int[] nums, int index, List<Integer> currentPermutation, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(currentPermutation);
            return;
        }

        for (int i = 0; i <= currentPermutation.size(); i++) {
            List<Integer> newPermutation = new ArrayList<>(currentPermutation);
            newPermutation.add(i, nums[index]);
            findPermutationsRec(nums, index + 1, newPermutation, result);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = Permutations.findPermutations2(new int[] { 1, 3, 5 });
        System.out.print("Here are all the permutations: " + result);
    }
}
