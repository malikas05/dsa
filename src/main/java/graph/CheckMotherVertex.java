package graph;

import linkedlist.DoublyLinkedList;
import queue.Queue;

public class CheckMotherVertex {

    public static void main(String[] args) {
        Graph g1 = new Graph(4);
        g1.addEdge(3, 0);
        g1.addEdge(3, 1);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.printGraph();
        System.out.println(findMotherVertex(g1));

        Graph g2 = new Graph(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 0);
        g2.printGraph();
        System.out.println(findMotherVertex(g2));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(V + E)
    Space complexity: O(N)
     */
    public static int findMotherVertex(Graph g) {
        for (int i = 0; i < g.getVertices(); i++) {
            Queue<Integer> queue = new Queue<>(g.getVertices());
            boolean[] visited = new boolean[g.getVertices()];
            visited[i] = true;
            queue.enqueue(i);

            while (!queue.isEmpty()) {
                DoublyLinkedList.Node node = g.getAdjacencyList()[queue.dequeue()].getHeadNode();

                while (node != null) {
                    if (!visited[node.getData()]) {
                        visited[node.getData()] = true;
                        queue.enqueue(node.getData());
                    }
                    node = node.getNextNode();
                }
            }

            boolean motherVertex = true;
            for (int j = 0; j < visited.length; j++) {
                if (!visited[j]) {
                    motherVertex = false;
                    break;
                }
            }
            if (motherVertex)
                return i;
        }
        return -1;
    }

}
