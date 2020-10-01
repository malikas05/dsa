package algo;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class MergeSort {

    public static void main(String[] args) {
        int[] data = {4, 3, 5, 1, 2, 10, 7};
        mergeSort2(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }

    /*
    MergeSort:
    - Complexity Analysis:
    Time complexity: O(N log(N)) in all cases
    Space complexity: O(N) to store data into subset arrays
     */
    public static void mergeSort(int[] data, int left, int right) {
        if (right <= left) return;
        int mid = (left + right) / 2;
        mergeSort(data, left, mid);
        mergeSort(data, mid + 1, right);
        merge(data, left, mid, right);
    }

    public static void merge(int[] data, int left, int mid, int right) {
        // calculating lengths
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;

        // creating temporary subarrays
        int[] leftArray = new int[lengthLeft];
        int[] rightArray = new int[lengthRight];

        // copying our sorted subarrays into temporaries
        IntStream.range(0, lengthLeft).forEach(i -> leftArray[i] = data[left + i]);
        IntStream.range(0, lengthRight).forEach(i -> rightArray[i] = data[mid + i + 1]);

        // iterators containing current index of temp subarrays
        AtomicInteger leftIndex = new AtomicInteger(0);
        AtomicInteger rightIndex = new AtomicInteger(0);

        // copying from leftArray and rightArray back into array
        IntStream.range(left, right + 1).forEach(i -> {
            // if there are still uncopied elements in R and L, copy minimum of the two
            if (leftIndex.get() < lengthLeft && rightIndex.get() < lengthRight) {
                if (leftArray[leftIndex.get()] < rightArray[rightIndex.get()]) {
                    data[i] = leftArray[leftIndex.getAndIncrement()];
                } else {
                    data[i] = rightArray[rightIndex.getAndIncrement()];
                }
            }
            // if all the elements have been copied from rightArray, copy the rest of leftArray
            else if (leftIndex.get() < lengthLeft) {
                data[i] = leftArray[leftIndex.getAndIncrement()];
            }
            // if all the elements have been copied from leftArray, copy the rest of rightArray
            else if (rightIndex.get() < lengthRight) {
                data[i] = rightArray[rightIndex.getAndIncrement()];
            }
        });
    }

    public static void mergeSort2(int[] data, int left, int right) {
        if (right <= left) return;
        int mid = (left + right) / 2;
        mergeSort2(data, left, mid);
        mergeSort2(data, mid + 1, right);
        merge2(data, left, mid, right);
    }

    public static void merge2(int[] data, int left, int mid, int right) {
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;

        int[] leftArray = new int[lengthLeft];
        int[] rightArray = new int[lengthRight];

        for (int i = 0; i < lengthLeft; i++) {
            leftArray[i] = data[left + i];
        }
        for (int i = 0; i < lengthRight; i++) {
            rightArray[i] = data[mid + i + 1];
        }

        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = left; i < right + 1; i++) {
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    data[i] = leftArray[leftIndex++];
                } else {
                    data[i] = rightArray[rightIndex++];
                }
            } else if (leftIndex < lengthLeft) {
                data[i] = leftArray[leftIndex++];
            } else if (rightIndex < lengthRight) {
                data[i] = rightArray[rightIndex++];
            }
        }
    }
}
