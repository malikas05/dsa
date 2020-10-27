package cyclicsort;

import java.util.ArrayList;
import java.util.List;

/*
Problem Statement #
We are given an unsorted array containing numbers taken from the range 1 to ‘n’.
The array can have duplicates, which means some numbers will be missing. Find all those missing numbers.

Example 1:
Input: [2, 3, 1, 8, 2, 3, 5, 1]
Output: 4, 6, 7
Explanation: The array should have all numbers from 1 to 8, due to duplicates 4, 6, and 7 are missing.

Example 2:
Input: [2, 4, 1, 2]
Output: 3

Example 3:
Input: [2, 3, 2, 1]
Output: 4
 */
public class AllMissingNumbers {
    public static void main(String[] args) {
        System.out.println(findNumbers(new int[]{2, 3, 1, 8, 2, 3, 5, 1}));
        System.out.println(findNumbers(new int[]{2, 4, 1, 2}));
        System.out.println(findNumbers(new int[]{2, 3, 2, 1}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static List<Integer> findNumbers(int[] nums) {
        List<Integer> missingNumbers = new ArrayList<>();

        int i = 0;
        while (i < nums.length) {
            if (nums[i] - 1 != i && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] - 1 != j) {
                missingNumbers.add(j + 1);
            }
        }

        return missingNumbers;
    }
}
