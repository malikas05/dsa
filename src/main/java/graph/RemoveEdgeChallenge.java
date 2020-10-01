package graph;

import linkedlist.DoublyLinkedList;

/*
Remove the edge between two vertices
 */
public class RemoveEdgeChallenge {

    public static void main(String[] args) {
        Graph g1 = new Graph(5);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(1, 3);
        g1.addEdge(3, 4);
        g1.printGraph();

        removeEdge(g1, 1, 2);
        g1.printGraph();
    }

    /*
    - Complexity Analysis:
    Time complexity: O(E)
     */
    public static void removeEdge(Graph g, int source, int destination) {
        if (source == destination) {
            return;
        }

        DoublyLinkedList<Integer>.Node node = null;

        if (g.getAdjacencyList()[source] != null) {
            node = g.getAdjacencyList()[source].getHeadNode();
        }

        while (node != null) {
            if (node.getData().equals(destination)) {
                if (node.getPrevNode() != null && node.getNextNode() != null) {
                    node.getPrevNode().setNextNode(node.getNextNode());
                    node.getNextNode().setPrevNode(node.getPrevNode());
                } else if (node.getPrevNode() != null) {
                    node.getPrevNode().setNextNode(null);
                } else if (node.getNextNode() != null) {
                    g.getAdjacencyList()[source].setHeadNode(node.getNextNode());
                }
                return;
            }
            node = node.getNextNode();
        }
    }
}
