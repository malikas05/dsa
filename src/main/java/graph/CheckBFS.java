package graph;

import linkedlist.DoublyLinkedList;
import queue.Queue;

/*
Breadth First Traversal of Graph g from source vertex
 */
public class CheckBFS {

    public static void main(String[] args) {
        Graph graph = new Graph(7);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(2, 0);
        graph.addEdge(2, 5);
        graph.addEdge(5, 6);

        graph.printGraph();

        System.out.println(bfsTraversal(graph, 0));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(V + E)
     */
    public static String bfsTraversal(Graph g, int source) {
        String result = "";
        int numVertices = g.getVertices();
        DoublyLinkedList<Integer>[] adjacencyList = g.getAdjacencyList();
        Queue<Integer> queue = new Queue<>(numVertices);
        boolean[] visited = new boolean[numVertices];

        result += source;
        visited[source] = true;
        queue.enqueue(source);

        while (!queue.isEmpty()) {
            DoublyLinkedList.Node node = adjacencyList[queue.dequeue()].getHeadNode();
            while (node != null) {
                if (!visited[node.getData()]) {
                    result += node.getData();
                    queue.enqueue(node.getData());
                }
                visited[node.getData()] = true;
                node = node.getNextNode();
            }
        }

        return result;
    }
}
