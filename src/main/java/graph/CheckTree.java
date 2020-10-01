package graph;

import linkedlist.DoublyLinkedList;
import stack.Stack;

import java.util.HashMap;
import java.util.Map;

public class CheckTree {

    public static void main(String[] args) {
        Graph g1 = new Graph(5);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        g1.printGraph();
        System.out.println(isTree(g1));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(V + E)
     */
    public static boolean isTree(Graph g) {
        DoublyLinkedList<Integer>[] adjacencyList = g.getAdjacencyList();
        Map<Integer, Integer> numVisitsForEachNode = new HashMap<>();
        int totalNumVisits = 1;
        Stack<DoublyLinkedList.Node> stack = new Stack<>(g.getVertices());
        stack.push(adjacencyList[0].getHeadNode());

        while (!stack.isEmpty()) {
            DoublyLinkedList.Node node = stack.pop();

            while (node != null) {
                if (node.getNextNode() != null) {
                    stack.push(node.getNextNode());
                }

                totalNumVisits++;
                numVisitsForEachNode.put(node.getData(), numVisitsForEachNode.getOrDefault(node.getData(), 0) + 1);
                node = adjacencyList[node.getData()].getHeadNode();
            }
        }

        return numVisitsForEachNode.values().stream().allMatch(value -> value <= 1) && totalNumVisits == g.getVertices();
    }
}
