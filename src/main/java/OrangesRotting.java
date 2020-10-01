import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/*
https://leetcode.com/problems/rotting-oranges/
 */
public class OrangesRotting {

    @Test
    public void testOrangesRotting() {
        System.out.println(orangesRotting(new int[][]{
                {2,1,1},
                {1,1,0},
                {0,1,1}
        }));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(n), where n is the number of cells in the grid
    Space complexity: O(n)
     */
    public int orangesRotting(int[][] grid) {
        int freshCount = 0;
        int rowCount = grid.length;
        int colCount = grid[0].length;
        Queue<Integer> rottenOranges = new ArrayDeque<>();

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (grid[i][j] == 1) {
                    freshCount++;
                } else if (grid[i][j] == 2) {
                    rottenOranges.add(i * colCount + j);
                }
            }
        }

        int numMin = 0;
        while (freshCount != 0 && !rottenOranges.isEmpty()) {
            int size = rottenOranges.size();
            for (int i = 0; i < size; i++) {
                int rottenPos = rottenOranges.poll();
                int row = rottenPos / colCount;
                int col = rottenPos % colCount;

                if (row - 1 >= 0 && grid[row - 1][col] == 1) {
                    rottenOranges.add((row - 1) * colCount + col);
                    grid[row - 1][col] = 2;
                    freshCount--;
                }
                if (row + 1 < rowCount && grid[row + 1][col] == 1) {
                    rottenOranges.add((row + 1) * colCount + col);
                    grid[row + 1][col] = 2;
                    freshCount--;
                }
                if (col - 1 >= 0 && grid[row][col - 1] == 1) {
                    rottenOranges.add(row * colCount + col - 1);
                    grid[row][col - 1] = 2;
                    freshCount--;
                }
                if (col + 1 < colCount && grid[row][col + 1] == 1) {
                    rottenOranges.add(row * colCount + col + 1);
                    grid[row][col + 1] = 2;
                    freshCount--;
                }
            }
            numMin++;
        }

        return freshCount == 0 ? numMin : -1;
    }

    public static void main(String[] args) {
        List<List<Integer>> grid = Arrays.asList(Arrays.asList(0, 1, 1, 0, 1), Arrays.asList(0, 1, 0, 1, 0),
                Arrays.asList(0, 0, 0, 0, 1), Arrays.asList(0, 1, 0, 0, 0));
        System.out.println(minHours(4, 5, grid));
    }

    /*
    https://leetcode.com/discuss/interview-question/411357/
     */
    public static int minHours(int rows, int columns, List<List<Integer>> grid) {
        int humans = 0;
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid.get(i).get(j) == 0) {
                    humans += 1;
                } else {
                    queue.offer(columns * i + j);
                }
            }
        }

        int numMin = 0;
        while (humans != 0 && !queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int pos = queue.poll();
                int row = pos / columns;
                int col = pos % columns;

                if (row - 1 >= 0 && grid.get(row - 1).get(col) == 0) {
                    queue.offer(columns * (row - 1) + col);
                    grid.get(row - 1).set(col, 1);
                    humans--;
                }
                if (row + 1 < rows && grid.get(row + 1).get(col) == 0) {
                    queue.offer(columns * (row + 1) + col);
                    grid.get(row + 1).set(col, 1);
                    humans--;
                }
                if (col - 1 >= 0 && grid.get(row).get(col - 1) == 0) {
                    queue.offer(columns * row + col - 1);
                    grid.get(row).set(col - 1, 1);
                    humans--;
                }
                if (col + 1 < columns && grid.get(row).get(col + 1) == 0) {
                    queue.offer(columns * row + col + 1);
                    grid.get(row).set(col + 1, 1);
                    humans--;
                }
            }
            numMin++;
        }

        return numMin;
    }
}
