package algo;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {
        int[] data = {4, 3, 5, 1, 2, 10, 7};
        Arrays.sort(data);
        System.out.println(binarySearchRecursive(data, 7, 0, data.length));
        System.out.println(binarySearchRecursive(data, 7, 0, data.length));
    }

    /*
    - Prerequisite: the array in question must be sorted in advance for the algorithm to achieve the correct search
    - Complexity Analysis:
    Time complexity: O(log n)
    Space complexity: O(1)
     */
    public static int binarySearchRecursive(int[] sortedArray, int key, int low, int high) {
        int mid = low + (high - low) / 2;

        if (high < low) {
            return -1;
        }

        if (key == sortedArray[mid]) {
            return mid;
        } else if (key > sortedArray[mid]) {
            return binarySearchRecursive(sortedArray, key, mid + 1, high);
        } else {
            return binarySearchRecursive(sortedArray, key, low, mid - 1);
        }
    }

    public static int binarySearchIterative(int[] sortedArray, int key, int low, int high) {

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (key < sortedArray[mid]) {
                high = mid - 1;
            } else if (key > sortedArray[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
