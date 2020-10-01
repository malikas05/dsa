package graph;

import linkedlist.DoublyLinkedList;
import queue.Queue;

/*
Find the shortest path in graph from source to destination.
 */
public class CheckMin {

    public static void main(String[] args) {
        Graph g1 = new Graph(6);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(0, 3);
        g1.addEdge(3, 5);
        g1.addEdge(2, 4);
        g1.addEdge(5, 4);
        g1.printGraph();

        System.out.println(findShortestPathLength(g1, 2, 5));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(V + E)
     */
    public static int findShortestPathLength(Graph g, int source, int destination) {
        if (source == destination) {
            return 0;
        }

        Queue<Integer> queue = new Queue<>(g.getVertices());
        int[] distance = new int[g.getVertices()];
        boolean[] visited = new boolean[g.getVertices()];

        queue.enqueue(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            Integer curNode = queue.dequeue();

            DoublyLinkedList.Node temp = null;
            if (g.getAdjacencyList()[curNode] != null) {
                temp = g.getAdjacencyList()[curNode].getHeadNode();
            }

            while (temp != null) {
                if (!visited[temp.getData()]) {
                    queue.enqueue(temp.getData());
                    visited[temp.getData()] = true;
                    distance[temp.getData()] = distance[curNode] + 1;
                }
                if (temp.getData() == destination) {
                    return distance[destination];
                }
                temp = temp.getNextNode();
            }
        }

        return -1;
    }
}
