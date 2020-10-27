package cyclicsort;

import java.util.ArrayList;
import java.util.List;

/*
Problem Statement #
We are given an unsorted array containing ‘n’ numbers taken from the range 1 to ‘n’.
The array has some numbers appearing twice, find all these duplicate numbers without using any extra space.

Example 1:
Input: [3, 4, 4, 5, 5]
Output: [4, 5]

Example 2:
Input: [5, 4, 7, 2, 3, 5, 3]
Output: [3, 5]
 */
public class FindAllDuplicate {
    public static void main(String[] args) {
        System.out.println(findNumbers(new int[]{3, 4, 4, 5, 5}));
        System.out.println(findNumbers(new int[]{5, 4, 7, 2, 3, 5, 3}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static List<Integer> findNumbers(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        List<Integer> duplicateNumbers = new ArrayList<>();
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            } else {
                if (i != nums[i] - 1) {
                    duplicateNumbers.add(nums[i]);
                }
                i++;
            }
        }

        return duplicateNumbers;
    }
}
