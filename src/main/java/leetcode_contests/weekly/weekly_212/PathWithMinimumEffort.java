package leetcode_contests.weekly.weekly_212;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
1631. Path With Minimum Effort - https://leetcode.com/problems/path-with-minimum-effort/
You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0),
and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left,
or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

Example 1:
Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

Example 2:
Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].

Example 3:
Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
Output: 0
Explanation: This route does not require any effort.

Constraints:
rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 106
 */
public class PathWithMinimumEffort {
    public static void main(String[] args) {
        System.out.println(minimumEffortPath(new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}}));
        System.out.println(minimumEffortPath(new int[][]{{1, 2, 3}, {3, 8, 4}, {5, 3, 5}}));
        System.out.println(minimumEffortPath(new int[][]{{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}}));
    }

    /*
    The first idea came to our mind is to use Dijikstra algorithm,
    which costs O(E log V), where E is number of edges E = 4*M*N, V is number of veritices V = M*N

    - Complexity Analysis:
    Time complexity: O(E log(V)) where E - is the number of edges and V - is the number of vertices in the graph
    Space complexity: O(V) - number of vertices
     */

    private static final int[] DIR = new int[]{0, 1, 0, -1, 0};

    public static int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        // By using minHeap, it will be guaranteed that we will always have access to the cell with the least distance
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.offer(new int[]{0, 0, 0});

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int d = current[0], r = current[1], c = current[2];
            if (r == m - 1 && c == n - 1) return d;

            for (int i = 0; i < 4; i++) {
                int nr = r + DIR[i], nc = c + DIR[i + 1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int newDist = Math.max(d, Math.abs(heights[nr][nc] - heights[r][c]));
                    if (newDist < dist[nr][nc]) {
                        dist[nr][nc] = newDist;
                        minHeap.offer(new int[]{newDist, nr, nc});
                    }
                }
            }
        }

        return 0;
    }
}
