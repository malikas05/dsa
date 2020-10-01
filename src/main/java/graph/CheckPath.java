package graph;

import linkedlist.DoublyLinkedList;
import stack.Stack;

public class CheckPath {

    public static void main(String[] args) {
        Graph g1 = new Graph(8);
        g1.addEdge(0, 1);
        g1.addEdge(0, 5);
        g1.addEdge(1, 2);
        g1.addEdge(1, 3);
        g1.addEdge(4, 2);
        g1.addEdge(4, 5);
        g1.addEdge(2, 5);
        g1.addEdge(5, 6);
        g1.addEdge(5, 7);
        g1.addEdge(5, 3);
        g1.addEdge(6, 7);
        g1.printGraph();

        System.out.println(checkPath(g1, 1, 4));
    }

    public static boolean checkPath(Graph g, int source, int destination) {
        DoublyLinkedList<Integer>[] adjacencyList = g.getAdjacencyList();
        Stack<DoublyLinkedList.Node> stack = new Stack<>(g.getVertices());

        stack.push(adjacencyList[source].getHeadNode());

        while (!stack.isEmpty()) {
            DoublyLinkedList.Node node = stack.pop();

            while (node != null) {
                if (node.getData().equals(destination)) {
                    return true;
                }
                if (node.getNextNode() != null) {
                    stack.push(node.getNextNode());
                }

                node = adjacencyList[node.getData()].getHeadNode();
            }
        }

        return false;
    }
}
