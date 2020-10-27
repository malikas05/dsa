package cyclicsort;

import java.util.Arrays;

/*
Problem Statement #
We are given an array containing ‘n’ distinct numbers taken from the range 0 to ‘n’. Since the array has only ‘n’ numbers out of the total ‘n+1’ numbers, find the missing number.

Example 1:
Input: [4, 0, 3, 1]
Output: 2

Example 2:
Input: [8, 3, 5, 2, 4, 6, 0, 1]
Output: 7
 */
public class MissingNumber {
    public static void main(String[] args) {
        System.out.println(findMissingNumber(new int[]{4, 0, 3, 1}));
        System.out.println(findMissingNumber(new int[]{8, 3, 5, 2, 4, 6, 0, 1}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static int findMissingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int i = 0;
        while (i < nums.length) {
            if (nums[i] < nums.length && nums[i] != i) {
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j) {
                return j;
            }
        }

        return -1;
    }

    /*
    - Concept: We know that there is a formula to calculate the sum of the sequence of numbers up to the given number: N * (N - 1) / 2.
    We can use this concept to calculate the total sum and subtract the current sum from this.
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static int findMissingNumber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int sum = Arrays.stream(nums).sum();
        int max = Arrays.stream(nums).max().getAsInt();

        return (max * (max + 1) / 2) - sum;
    }
}
