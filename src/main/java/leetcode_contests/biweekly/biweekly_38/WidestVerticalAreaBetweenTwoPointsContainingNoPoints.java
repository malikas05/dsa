package leetcode_contests.biweekly.biweekly_38;

import java.util.Arrays;

/*
1637. Widest Vertical Area Between Two Points Containing No Points - https://leetcode.com/problems/widest-vertical-area-between-two-points-containing-no-points/
Given n points on a 2D plane where points[i] = [xi, yi], Return the widest vertical area between two points such that no points are inside the area.
A vertical area is an area of fixed-width extending infinitely along the y-axis (i.e., infinite height). The widest vertical area is the one with the maximum width.
Note that points on the edge of a vertical area are not considered included in the area.

Example 1:​
Input: points = [[8,7],[9,9],[7,4],[9,7]]
Output: 1
Explanation: Both the red and the blue area are optimal.

Example 2:
Input: points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
Output: 3

Constraints:
n == points.length
2 <= n <= 105
points[i].length == 2
0 <= xi, yi <= 10^9
 */
public class WidestVerticalAreaBetweenTwoPointsContainingNoPoints {
    public static void main(String[] args) {
        System.out.println(maxWidthOfVerticalArea(new int[][]{{8, 7}, {9, 9}, {7, 4}, {9, 7}}));
        System.out.println(maxWidthOfVerticalArea(new int[][]{{3, 1}, {9, 0}, {1, 0}, {1, 4}, {5, 3}, {8, 8}}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log(N))
    Space complexity: O(log N) - using Quicksort which requires O(log N) space
     */
    public static int maxWidthOfVerticalArea(int[][] points) {
        if (points == null || points.length == 0) {
            return -1;
        }

        int[] xCoordinatesArray = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            xCoordinatesArray[i] = points[i][0];
        }

        Arrays.sort(xCoordinatesArray);

        int widestVerticalArea = 0;
        for (int i = 1; i < points.length; i++) {
            widestVerticalArea = Math.max(widestVerticalArea, xCoordinatesArray[i] - xCoordinatesArray[i - 1]);
        }

        return widestVerticalArea;
    }
}
