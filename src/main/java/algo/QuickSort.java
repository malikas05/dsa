package algo;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] data = {4, 3, 5, 1, 2, 10, 7};
        quickSort2(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }

    /*
    QuickSort:
    - Complexity Analysis:
    Time complexity: O(N log(N)) in best and average cases; O(N^2) in worst case
    Space complexity: O(N) - used to store method data on the stack for each invocation
     */
    public static void quickSort(int[] data, int left, int right) {
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

        if (left < j) {
            quickSort(data, left, j);
        }

        if (right > i) {
            quickSort(data, i, right);
        }
    }

    public static void quickSort2(int[] data, int left, int right) {

    }
}
