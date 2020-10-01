package array.twopointers;

/*
Problem Statement #
Given an array containing 0s, 1s and 2s, sort the array in-place. You should treat numbers of the array as objects, hence, we can’t count 0s, 1s, and 2s to recreate the array.
The flag of the Netherlands consists of three colors: red, white and blue; and since our input array also consists of three different numbers that is why it is called Dutch National Flag problem.

Example 1:
Input: [1, 0, 2, 1, 0]
Output: [0 0 1 1 2]

Example 2:
Input: [2, 2, 0, 1, 2, 0]
Output: [0 0 1 2 2 2 ]
 */

import java.util.Arrays;

public class DutchFlag {
    public static void main(String[] args) {
        int[] arr = {1, 0, 2, 1, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{2, 2, 0, 1, 2, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0)
            return;

        int low = 0, high = arr.length - 1, i = 0;

        while (i <= high) {
            if (arr[i] == 0) {
                swap(arr, i, low);
                i++;
                low++;
            } else if (arr[i] == 1) {
                i++;
            } else {
                swap(arr, i, high);
                high--;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
