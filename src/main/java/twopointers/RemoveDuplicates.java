package twopointers;

/*
Problem Statement #
Given an array of sorted numbers, remove all duplicates from it. You should not use any extra space;
after removing the duplicates in-place return the length of the subarray that has no duplicate in it.

Example 1:
Input: [2, 3, 3, 3, 6, 9, 9]
Output: 4
Explanation: The first four elements after removing the duplicates will be [2, 3, 6, 9].

Example 2:
Input: [2, 2, 2, 11]
Output: 2
Explanation: The first two elements after removing the duplicates will be [2, 11].
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        System.out.println(RemoveDuplicates.remove(new int[]{2, 3, 3, 3, 6, 9, 9}));
        System.out.println(RemoveDuplicates.remove(new int[]{2, 2, 2, 11}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static int remove(int[] arr) {
        if (arr == null || arr.length == 0)
            return -1;

        int oldPointer = 1, newPointer = oldPointer, prevValue = arr[0];

        while (oldPointer < arr.length) {
            if (arr[oldPointer] != prevValue) {
                arr[newPointer] = arr[oldPointer];
                prevValue = arr[newPointer];
                newPointer++;
            }

            oldPointer++;
        }

        return newPointer;
    }
}
