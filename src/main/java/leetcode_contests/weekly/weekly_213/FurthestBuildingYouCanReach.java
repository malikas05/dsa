package leetcode_contests.weekly.weekly_213;

import java.util.PriorityQueue;

/*
1642. Furthest Building You Can Reach - https://leetcode.com/contest/weekly-contest-213/problems/furthest-building-you-can-reach/
You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
While moving from building i to building i+1 (0-indexed),
If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.

Example 1:
Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
Output: 4
Explanation: Starting at building 0, you can follow these steps:
- Go to building 1 without using ladders nor bricks since 4 >= 2.
- Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
- Go to building 3 without using ladders nor bricks since 7 >= 6.
- Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
It is impossible to go beyond building 4 because you do not have any more bricks or ladders.

Example 2:
Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
Output: 7

Example 3:
Input: heights = [14,3,19,3], bricks = 17, ladders = 0
Output: 3

Constraints:
1 <= heights.length <= 105
1 <= heights[i] <= 106
0 <= bricks <= 109
0 <= ladders <= heights.length
 */
public class FurthestBuildingYouCanReach {
    public static void main(String[] args) {
        System.out.println(furthestBuilding(new int[]{4, 2, 7, 6, 9, 14, 12}, 5, 1));
        System.out.println(furthestBuilding(new int[]{4, 12, 2, 7, 3, 18, 20, 3, 19}, 10, 2));
        System.out.println(furthestBuilding(new int[]{7, 5, 13}, 0, 0));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log (K))
    Space complexity: O(K)
     */
    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 1; i < heights.length; i++) {
            int diff = heights[i] - heights[i - 1];
            if (diff <= 0) {
                continue;
            }

            if (ladders > 0) {
                minHeap.offer(diff);
                ladders -= 1;
            } else if (bricks >= diff) {
                bricks -= diff;
            } else if (minHeap.size() > 0 && diff > minHeap.peek() && bricks >= minHeap.peek()) {
                bricks -= minHeap.poll();
                minHeap.offer(diff);
            } else {
                return i - 1;
            }
        }

        return heights.length - 1;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N ^ 2)
    Space complexity: O(N ^ 2)
     */
    public static int furthestBuilding2(int[] heights, int bricks, int ladders) {
        int[] furthestBuilding = new int[1];
        backTracking(heights, 0, bricks, ladders, furthestBuilding);
        return furthestBuilding[0];
    }

    private static void backTracking(int[] heights, int start, int bricks, int ladders, int[] furthestBuilding) {
        furthestBuilding[0] = Math.max(furthestBuilding[0], start);
        if (start == heights.length - 1) {
            return;
        }

        if (heights[start] >= heights[start + 1]) {
            backTracking(heights, start + 1, bricks, ladders, furthestBuilding);
        } else {
            if (bricks >= (heights[start + 1] - heights[start])) {
                backTracking(heights, start + 1, bricks - (heights[start + 1] - heights[start]), ladders, furthestBuilding);
            }
            if (ladders > 0) {
                backTracking(heights, start + 1, bricks, ladders - 1, furthestBuilding);
            }
        }
    }
}
