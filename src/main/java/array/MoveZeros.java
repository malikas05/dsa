package array;

import java.util.Arrays;

public class MoveZeros {

    public static void main(String[] args) {
        int[] data = {1, 10, 20, 0, 59, 63, 0, 88, 0};
        moveZerosToLeft(data);
        System.out.println(Arrays.toString(data));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    static void moveZerosToLeft(int[] A) {
        int i = A.length - 1;
        int lastIndex = A.length - 1;

        while (i >= 0 && lastIndex > 0) {
            while (A[i] == 0 && i > 0) {
                i--;
            }

            A[lastIndex--] = A[i--];
        }

        for (int j = 0; j <= lastIndex; j++) {
            A[j] = 0;
        }
    }
}
