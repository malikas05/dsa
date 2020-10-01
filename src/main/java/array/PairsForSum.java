package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an array of non-negative integers and a positive integer n. Print out all the pairs of integers whose sum is n.
 */
public class PairsForSum {

    @Test
    public void testFindPairs() {
        List<Integer> pairs = findPair(new int[]{2, 1, 12, 10, 11, 9}, 11);
        System.out.println(Arrays.toString(pairs.toArray()));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(n log n), where n log n - sorting
    Space complexity: O(1)
     */
    public List<Integer> findPair(int[] arr, int target) {
        quickSort(arr, 0, arr.length - 1);

        int lowIndex = 0;
        int highIndex = arr.length - 1;
        List<Integer> pairs = new ArrayList<>();

        while (lowIndex < highIndex) {
            if (arr[highIndex] > target) {
                highIndex--;
                continue;
            }
            int tempSum = arr[lowIndex] + arr[highIndex];
            if (tempSum == target) {
                pairs.add(arr[lowIndex]);
                pairs.add(arr[highIndex]);
                lowIndex++;
                highIndex++;
            } else if (tempSum < target) {
                lowIndex++;
            } else {
                highIndex--;
            }
        }

        return pairs;
    }

    private void quickSort(int[] data, int left, int right) {
        int pivotValue = data[(left + right) / 2];
        int i = left;
        int j = right;

        while (i <= j) {
            while (data[i] < pivotValue) i++;
            while (data[j] > pivotValue) j--;

            if (i <= j) {
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
                i++;
                j--;
            }
        }

        if (left < j) quickSort(data, left, j);
        if (right > i) quickSort(data, i, right);
    }

}
