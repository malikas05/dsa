package graph;

import java.util.*;

/*
743. Network Delay Time - https://leetcode.com/problems/network-delay-time/
There are N network nodes, labelled 1 to N.
Given times, a list of travel times as directed edges times[i] = (u, v, w),
where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Example 1:
Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2

Note:
N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.

Concept:
Dijkstra's Algorithm:
https://www.codingame.com/playgrounds/1608/shortest-paths-with-dijkstras-algorithm/dijkstras-algorithm
 */
public class NetworkDelayTime {
    public static void main(String[] args) {
        System.out.println(networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println(networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 7}, {1, 3, 4}, {2, 1, 2}}, 3, 1));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(E log(V)) where E - is the number of edges and V - is the number of vertices in the graph
    Space complexity: O(V) - number of vertices
     */
    public static int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<Network>> dist = new HashMap<>();
        for (int[] time : times) {
            dist.putIfAbsent(time[0], new ArrayList<>());
            dist.get(time[0]).add(new Network(time[0], time[1], time[2]));
        }

        if (!dist.containsKey(K)) {
            return -1;
        }

        int[] visited = new int[N + 1];
        Arrays.fill(visited, -1);
        visited[K] = 0;
        PriorityQueue<Network> heap = new PriorityQueue<>(Comparator.comparingInt(network -> network.time));
        heap.addAll(dist.get(K));

        while (!heap.isEmpty()) {
            Network cur = heap.poll();
            if (visited[cur.end] != -1) {
                continue;
            }

            visited[cur.end] = cur.time;
            if (dist.containsKey(cur.end)) {
                for (Network child : dist.get(cur.end)) {
                    child.time += visited[cur.end];
                    heap.offer(child);
                }
            }
        }

        int timeToReturn = 0;
        for (int i = 1; i < visited.length; i++) {
            if (visited[i] == -1)
                return -1;

            timeToReturn = Math.max(timeToReturn, visited[i]);
        }

        return timeToReturn;
    }

    private static class Network {
        private int start;
        private int end;
        private int time;

        public Network(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }
}
