package twopointers;

import java.util.Arrays;

/*
Problem Statement #
Given a sorted array, create a new array containing squares of all the number of the input array in the sorted order.

Example 1:
Input: [-2, -1, 0, 2, 3]
Output: [0, 1, 4, 4, 9]

Example 2:
Input: [-3, -1, 0, 1, 2]
Output: [0 1 1 4 9]
 */
public class SortedArraySquares {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(SortedArraySquares.makeSquares(new int[] {-2, -1, 0, 2, 3})));
        System.out.println(Arrays.toString(SortedArraySquares.makeSquares(new int[] {-3, -1, 0, 1, 2})));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static int[] makeSquares(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;

        int leftPointer = 0, rightPointer = arr.length - 1;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] * arr[i];
        }

        while (leftPointer < rightPointer) {
            if (arr[leftPointer] <= arr[rightPointer]) {
                rightPointer--;
                continue;
            }

            int temp = arr[leftPointer];
            arr[leftPointer] = arr[rightPointer];
            arr[rightPointer] = temp;
            rightPointer--;
        }

        return arr;
    }

    public static int[] makeSquares2(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;

        int[] squares = new int[arr.length];
        int newPointer = arr.length - 1;
        int leftPointer = 0, rightPointer = arr.length - 1;

        while (leftPointer < rightPointer) {
            int leftSquare = arr[leftPointer] * arr[leftPointer];
            int rightSquare = arr[rightPointer] * arr[rightPointer];

            if (leftSquare > rightSquare) {
                squares[newPointer--] = leftSquare;
                leftPointer++;
            } else {
                squares[newPointer--] = rightSquare;
                rightPointer--;
            }
        }

        return squares;
    }
}
