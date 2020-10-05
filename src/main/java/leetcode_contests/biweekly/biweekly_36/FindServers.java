package leetcode_contests.biweekly.biweekly_36;

import java.util.*;

/*
1606. Find Servers That Handled Most Number of Requests: https://leetcode.com/contest/biweekly-contest-36/problems/find-servers-that-handled-most-number-of-requests/
You have k servers numbered from 0 to k-1 that are being used to handle multiple requests simultaneously.
Each server has infinite computational capacity but cannot handle more than one request at a time.
The requests are assigned to servers according to a specific algorithm:
The ith (0-indexed) request arrives.
If all servers are busy, the request is dropped (not handled at all).
If the (i % k)th server is available, assign the request to that server.
Otherwise, assign the request to the next available server (wrapping around the list of servers and starting from 0 if necessary).
For example, if the ith server is busy, try to assign the request to the (i+1)th server, then the (i+2)th server, and so on.
You are given a strictly increasing array arrival of positive integers, where arrival[i] represents the arrival time of the ith request,
and another array load, where load[i] represents the load of the ith request (the time it takes to complete).
Your goal is to find the busiest server(s). A server is considered busiest if it handled the most number of requests successfully among all the servers.

Return a list containing the IDs (0-indexed) of the busiest server(s). You may return the IDs in any order.

Example 1:
Input: k = 3, arrival = [1,2,3,4,5], load = [5,2,3,3,3]
Output: [1]
Explanation:
All of the servers start out available.
The first 3 requests are handled by the first 3 servers in order.
Request 3 comes in. Server 0 is busy, so it's assigned to the next available server, which is 1.
Request 4 comes in. It cannot be handled since all servers are busy, so it is dropped.
Servers 0 and 2 handled one request each, while server 1 handled two requests. Hence server 1 is the busiest server.

Example 2:
Input: k = 3, arrival = [1,2,3,4], load = [1,2,1,2]
Output: [0]
Explanation:
The first 3 requests are handled by first 3 servers.
Request 3 comes in. It is handled by server 0 since the server is available.
Server 0 handled two requests, while servers 1 and 2 handled one request each. Hence server 0 is the busiest server.

Example 3:
Input: k = 3, arrival = [1,2,3], load = [10,12,11]
Output: [0,1,2]
Explanation: Each server handles a single request, so they are all considered the busiest.

Example 4:
Input: k = 3, arrival = [1,2,3,4,8,9,10], load = [5,2,10,3,1,2,2]
Output: [1]

Example 5:
Input: k = 1, arrival = [1], load = [1]
Output: [0]

Constraints:
1 <= k <= 105
1 <= arrival.length, load.length <= 105
arrival.length == load.length
1 <= arrival[i], load[i] <= 109
arrival is strictly increasing.
 */
public class FindServers {
    public static void main(String[] args) {
        System.out.println(busiestServers(3, new int[]{1, 2, 3, 4, 5}, new int[]{5, 2, 3, 3, 3}));
        System.out.println(busiestServers(3, new int[]{1, 2, 3, 4}, new int[]{1, 2, 1, 2}));
        System.out.println(busiestServers(3, new int[]{1, 2, 3}, new int[]{10, 12, 11}));
        System.out.println(busiestServers(3, new int[]{1, 2, 3, 4, 8, 9, 1}, new int[]{5, 2, 10, 3, 1, 2, 2}));
        System.out.println(busiestServers(1, new int[]{1}, new int[]{1}));
        System.out.println(busiestServers(2, new int[]{1, 4, 5, 7}, new int[]{3, 2, 7, 8}));
        System.out.println(busiestServers(3, new int[]{3, 4, 6, 8, 9, 11, 12, 16}, new int[]{1, 2, 8, 6, 5, 3, 8, 3}));
    }

    public static List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        if (k < 1 || arrival == null || arrival.length == 0 || load == null || load.length == 0)
            return null;

        TreeMap<Integer, Server> availableServers = new TreeMap<>();
        PriorityQueue<Server> busyServers = new PriorityQueue<>(Comparator.comparingInt(Server::getFinishedTime));
        List<Server> list = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            Server server = new Server(i);
            availableServers.put(i, server);
            list.add(server);
        }

        int maxTotalProcessedRequests = 0;

        for (int i = 0; i < arrival.length; i++) {
            while (!busyServers.isEmpty() && busyServers.peek().finishedTime <= arrival[i]) {
                Server server = busyServers.poll();
                availableServers.put(server.id, server);
            }

            if (availableServers.size() == 0)
                continue;
            int requestId = i % k;
            int serverId = availableServers.ceilingKey(requestId) != null ? availableServers.ceilingKey(requestId) : availableServers.firstKey();
            Server server = availableServers.get(serverId);
            server.finishedTime = arrival[i] + load[i];
            server.totalProcessedRequests += 1;
            maxTotalProcessedRequests = Math.max(maxTotalProcessedRequests, server.totalProcessedRequests);
            busyServers.offer(server);
            availableServers.remove(serverId);
        }

        List<Integer> result = new ArrayList<>();
        for (Server server : list) {
            if (server.totalProcessedRequests == maxTotalProcessedRequests)
                result.add(server.id);
        }

        return result;
    }

    private static class Server {
        private int id;
        private int finishedTime;
        private int totalProcessedRequests;

        public Server(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public int getFinishedTime() {
            return finishedTime;
        }

        public int getTotalProcessedRequests() {
            return totalProcessedRequests;
        }
    }
}
