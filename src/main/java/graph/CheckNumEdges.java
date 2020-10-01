package graph;

import linkedlist.DoublyLinkedList;

import java.util.HashMap;
import java.util.Map;

/*
In this problem, you have to implement the numEdges() method to take an undirected graph
as an input and find the total number of edges in the graph.
NOTE:
Make sure line #32 is uncommented for this to work properly
 */
public class CheckNumEdges {

    public static void main(String[] args) {
        Graph g1 = new Graph(8);
        g1.addEdge(0, 1);
        g1.addEdge(0, 4);
        g1.addEdge(1, 2);
        g1.addEdge(1, 3);
        g1.addEdge(2, 5);
        g1.addEdge(3, 5);
        g1.addEdge(4, 2);
        g1.addEdge(4, 5);
        g1.addEdge(5, 6);
        g1.addEdge(5, 7);
        g1.addEdge(6, 7);

        g1.printGraph();

        System.out.println(numEdges(g1));

    }

    /*
    - Info: Just traversing the whole graph. Since it's undirected graph,
    we should divide the final number 2 to get only one way edges.
    - Complexity Analysis:
    Time complexity: O(V + E)
    Space complexity: O(1)
     */
    public static int numEdges(Graph g) {
        DoublyLinkedList<Integer>[] adjacencyList = g.getAdjacencyList();
        int numEdges = 0;

        for (int i = 0; i < adjacencyList.length; i++) {
            DoublyLinkedList.Node node = adjacencyList[i] != null ? adjacencyList[i].getHeadNode() : null;

            while (node != null) {
                numEdges++;
                node = node.getNextNode();
            }
        }

        return numEdges / 2;
    }

    /*
    - Info: This method is using HashMap to store the connections which allows to avoid counting double edges
    and save some time.
    - Complexity Analysis:
    Time complexity: O(V + E)
    Space complexity: O(N)
     */
    public static int numEdges2(Graph g) {
        Map<String, Boolean> map = new HashMap<>();
        DoublyLinkedList<Integer>[] adjacencyList = g.getAdjacencyList();
        int numEdges = 0;

        for (int i = 0; i < adjacencyList.length; i++) {
            DoublyLinkedList.Node node = adjacencyList[i].getHeadNode();

            while (node != null) {
                if (!map.containsKey(node.getData().toString() + i)) {
                    numEdges++;
                    map.put(i + node.getData().toString(), true);
                }
                node = node.getNextNode();
            }
        }

        return numEdges;
    }
}
