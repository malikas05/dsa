package array;

public class SearchMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{2, 4, 9, 13}, {3, 5, 11, 18}, {6, 8, 16, 21}, {9, 11, 20, 25}};
        System.out.println(searchInMatrix(matrix, 16));
    }

    private static class IntPair extends Pair<Integer, Integer> {
        public IntPair(int first, int second) {
            super(first, second);
        }
    }

    private static class Pair<X, Y> {
        public X first;
        public Y second;

        public Pair(X first, Y second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N + M)
    Space complexity: O(1)
     */
    public static IntPair searchInMatrix(int matrix[][], int value) {
        if (matrix == null || matrix.length < 1)
            return new IntPair(-1, -1);

        int x = 0, y = matrix[0].length - 1;

        while (x < matrix.length && y > 0) {
            if (matrix[x][y] > value) {
                y--;
            } else if (matrix[x][y] < value) {
                x++;
            } else {
                return new IntPair(x, y);
            }
        }

        return new IntPair(-1, -1);
    }
}
