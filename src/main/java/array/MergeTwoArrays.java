package array;

import java.util.Arrays;

public class MergeTwoArrays {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeArrays(new int[]{1, 3, 4, 5}, new int[]{2, 6, 7, 8})));
    }

    // merge arr1 and arr2 into a new result array
    public static int[] mergeArrays(int[] arr1, int[] arr2) {
        // write your code here
        int arr1Size = arr1.length;
        int arr2Size = arr2.length;
        int[] mergedArr = new int[arr1Size + arr2Size];
        int i = 0, j = 0, k = 0;

        while (i < arr1Size && j < arr2Size) {
            if (arr1[i] < arr2[j]) {
                mergedArr[k++] = arr1[i++];
            } else {
                mergedArr[k++] = arr2[j++];
            }
        }

        while (i < arr1Size) {
            mergedArr[k++] = arr1[i++];
        }

        while (j < arr2Size) {
            mergedArr[k++] = arr2[j++];
        }

        return mergedArr; // make a new resultant array and return your results in that
    }
}
