package array;

import java.util.Arrays;
import java.util.List;

public class RotateArray {

    public static void main(String []args){
        List<Integer> arr = Arrays.asList(1, 10, 20, 0, 59, 86, 32, 11, 9, 40);
        System.out.println("Array Before Rotation\n"+arr);
        rotateArray(arr, 2);
        System.out.println("Array After Rotation\n"+arr);
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    static void rotateArray(List<Integer> arr, int n) {
        if (arr.size() == 0 || n == 0) {
            return;
        }

        // Let's normalize rotations
        // if n > array size or n is negative.
        n = n % arr.size();
        if (n < 0)
            n += arr.size();

        // Let's partition the array based on rotations 'n'.
        // For example: 1, 2, 3, 4, 5 where n = 2.
        // -> 5, 4, 3, 2, 1
        // -> 4, 5, 3, 2, 1
        // -> 4, 5, 1, 2, 3
        reverseArray(arr, 0, arr.size() - 1);
        reverseArray(arr, 0, n - 1);
        reverseArray(arr, n, arr.size() - 1);
    }

    static void reverseArray(List<Integer> arr, int start, int end) {
        while (start < end) {
            int temp = arr.get(start);
            arr.set(start, arr.get(end));
            arr.set(end, temp);

            start++;
            end--;
        }
    }
}
