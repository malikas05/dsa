package cyclicsort;

import java.util.Arrays;

/*
Problem Statement #
We are given an array containing ‘n’ objects. Each object, when created, was assigned a unique number from 1 to ‘n’ based on their creation sequence.
This means that the object with sequence number ‘3’ was created just before the object with sequence number ‘4’.
Write a function to sort the objects in-place on their creation sequence number in O(n)O(n) and without any extra space.
For simplicity, let’s assume we are passed an integer array containing only the sequence numbers, though each number is actually an object.

Example 1:
Input: [3, 1, 5, 4, 2]
Output: [1, 2, 3, 4, 5]

Example 2:
Input: [2, 6, 4, 3, 1, 5]
Output: [1, 2, 3, 4, 5, 6]

Example 3:
Input: [1, 5, 6, 4, 3, 2]
Output: [1, 2, 3, 4, 5, 6]
 */
public class CyclicSort {
    public static void main(String[] args) {
        int[] input = {3, 1, 5, 4, 2};
        sort(input);
        System.out.println(Arrays.toString(input));

        input = new int[]{2, 6, 4, 3, 1, 5};
        sort(input);
        System.out.println(Arrays.toString(input));

        input = new int[]{1, 5, 6, 4, 3, 2};
        sort(input);
        System.out.println(Arrays.toString(input));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static void sort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i + 1) {
                i++;
                continue;
            }

            int newPos = nums[i] - 1;
            int temp = nums[newPos];
            nums[newPos] = nums[i];
            nums[i] = temp;
        }
    }
}
