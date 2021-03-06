package graph;

import linkedlist.DoublyLinkedList;

public class Graph {

    private int vertices;
    private DoublyLinkedList<Integer>[] adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new DoublyLinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new DoublyLinkedList<>();
        }
    }

    public int getVertices() {
        return vertices;
    }

    public DoublyLinkedList<Integer>[] getAdjacencyList() {
        return adjacencyList;
    }

    public void addEdge(int source, int destination) {
        if (source < vertices && destination < vertices) {
            adjacencyList[source].insertAtEnd(destination);

            //for undirected graph uncomment the line below
//            this.adjacencyList[destination].insertAtEnd(source);
        }
    }

    public void printGraph() {
        System.out.println(">>Adjacency List of Directed Graph<<");
        for (int i = 0; i < vertices; i++) {
            if (adjacencyList[i] != null) {
                System.out.print("|" + i + "| => ");
                DoublyLinkedList<Integer>.Node temp = adjacencyList[i].getHeadNode();
                while (temp != null) {
                    System.out.print("[" + temp.getData() + "] -> ");
                    temp = temp.getNextNode();
                }
                System.out.println("null");
            } else {
                System.out.print("|" + i + "| => "+ "null");
            }
        }
    }
}
