package leetcode_contests.weekly.weekly_210;

import java.util.*;

/*
1615. Maximal Network Rank - https://leetcode.com/problems/maximal-network-rank/
 */
public class MaxNetworkRank {
    public static void main(String[] args) {
        System.out.println(maximalNetworkRank(4, new int[][]{{0, 1}, {0, 3}, {1, 2}, {1, 3}}));
        System.out.println(maximalNetworkRank(5, new int[][]{{0, 1}, {0, 3}, {1, 2}, {1, 3}, {2, 3}, {2, 4}}));
        System.out.println(maximalNetworkRank(8, new int[][]{{0, 1}, {1, 2}, {2, 3}, {2, 4}, {5, 6}, {5, 7}}));
        System.out.println(maximalNetworkRank(5, new int[][]{{2, 3}, {0, 3}, {0, 4}, {4, 1}}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    public static int maximalNetworkRank(int n, int[][] roads) {
        if (n <= 0 || roads == null || roads.length < 1)
            return 0;

        Map<Integer, Set<Integer>> cityToRoads = new HashMap<>();
        for (int[] arr : roads) {
            cityToRoads.putIfAbsent(arr[0], new HashSet<>());
            cityToRoads.get(arr[0]).add(arr[1]);

            cityToRoads.putIfAbsent(arr[1], new HashSet<>());
            cityToRoads.get(arr[1]).add(arr[0]);
        }

        int maxKey = 0;
        int maxRank = 0;

        for (Map.Entry<Integer, Set<Integer>> entry : cityToRoads.entrySet()) {
            if (entry.getValue().size() >= maxRank) {
                maxRank = entry.getValue().size();
                maxKey = entry.getKey();
            }
        }

        int maxRankSecond = 0;
        for (Map.Entry<Integer, Set<Integer>> entry : cityToRoads.entrySet()) {
            if (entry.getKey() != maxKey) {
                if (cityToRoads.get(maxKey).contains(entry.getKey())) {
                    cityToRoads.get(entry.getKey()).remove(maxKey);
                }

                maxRankSecond = Math.max(maxRankSecond, entry.getValue().size());
            }
        }

        return maxRank + maxRankSecond;
    }
}
