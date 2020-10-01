package graph;

import linkedlist.DoublyLinkedList;
import stack.Stack;

/*
Depth First Traversal of Graph g from source vertex
 */
public class CheckDFS {

    public static void main(String[] args) {
        Graph graph = new Graph(7);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(3, 5);
        graph.addEdge(2, 6);

        graph.printGraph();

        System.out.println(dfsTraversal(graph, 0));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(V + E)
     */
    public static String dfsTraversal(Graph g, int source) {
        String result = "";
        int numVertices = g.getVertices();
        DoublyLinkedList<Integer>[] adjacencyList = g.getAdjacencyList();
        boolean[] visited = new boolean[numVertices];
        Stack<DoublyLinkedList.Node> stack = new Stack<>(numVertices);

        result += source;
        visited[source] = true;
        stack.push(adjacencyList[source].getHeadNode());

        while (!stack.isEmpty()) {
            DoublyLinkedList.Node node = stack.pop();

            while (node != null) {
                if (!visited[node.getData()]) {
                    result += node.getData();
                    visited[node.getData()] = true;
                }
                if (node.getNextNode() != null)
                    stack.push(node.getNextNode());
                node = adjacencyList[node.getData()].getHeadNode();
            }
        }

        return result;
    }

    /*
    - Info: This method starts traversing from right node
    - Complexity Analysis:
    Time complexity: O(V + E)
     */
    public static String dfsTraversal2(Graph g, int source) {
        String result = "";
        int num_of_vertices = g.getVertices();
        DoublyLinkedList<Integer>[] adjacencyList = g.getAdjacencyList();
        boolean[] visited = new boolean[num_of_vertices];
        Stack<Integer> stack = new Stack<>(num_of_vertices);
        stack.push(source);

        while (!stack.isEmpty()) {
            int curNode = stack.pop();
            result += curNode;

            DoublyLinkedList.Node temp = null;
            if (adjacencyList[curNode] != null) {
                temp = adjacencyList[curNode].getHeadNode();
            }

            while (temp != null) {
                if (!visited[temp.getData()]) {
                    stack.push(temp.getData());
                    visited[temp.getData()] = true;
                }
                temp = temp.getNextNode();
            }
        }

        return result;
    }
}
