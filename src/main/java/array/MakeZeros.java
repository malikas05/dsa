package array;

import java.util.Arrays;

public class MakeZeros {
    public static void main(String[] args) {
        int[][] matrix = {{5, 4, 3, 9}, {2, 0, 7, 6}, {1, 3, 4, 0}, {9, 8, 3, 4}};
        makeZeros(matrix);

        System.out.println(Arrays.toString(matrix));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N*M)
    Space complexity: O(N+M)
     */
    private static void makeZeros(int[][] matrix) {
        if (matrix == null || matrix.length < 1)
            return;

        int[][] duplicateArr = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            duplicateArr[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0)
                    continue;

                fillDuplicateArrWithZeros(duplicateArr, i, j);
            }
        }

        for (int i = 0; i < duplicateArr.length; i++) {
            matrix[i] = Arrays.copyOf(duplicateArr[i], duplicateArr[i].length);
        }
    }

    private static void fillDuplicateArrWithZeros(int[][] duplicateArr, int x, int y) {
        for (int i = 0; i < duplicateArr[0].length; i++) {
            duplicateArr[x][i] = 0;
        }

        for (int i = 0; i < duplicateArr.length; i++) {
            duplicateArr[i][y] = 0;
        }
    }
}
