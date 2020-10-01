package graph;

import linkedlist.DoublyLinkedList;
import queue.Queue;

public class CheckCycle {

    public static void main(String[] args) {
        Graph g1 = new Graph(4);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(1, 3);
        g1.addEdge(3, 0);
        g1.printGraph();
        System.out.println(detectCycle(g1));

        System.out.println();
        Graph g2 = new Graph(3);
        g2.addEdge(0,1);
        g2.addEdge(1,2);
        g2.printGraph();
        System.out.println(detectCycle(g2));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(V + E)
     */
    public static boolean detectCycle(Graph g) {
        boolean[] visited = new boolean[g.getVertices()];
        Queue<Integer> queue = new Queue<>(g.getVertices());
        queue.enqueue(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            DoublyLinkedList.Node node = g.getAdjacencyList()[queue.dequeue()].getHeadNode();

            while (node != null) {
                if (!visited[node.getData()]) {
                    visited[node.getData()] = true;
                    queue.enqueue(node.getData());
                    node = node.getNextNode();
                } else {
                    return true;
                }
            }
        }

        return false;
    }
}
