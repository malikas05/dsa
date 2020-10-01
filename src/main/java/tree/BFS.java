package tree;

import java.util.*;

public class BFS {

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.add(6);
        binarySearchTree.add(4);
        binarySearchTree.add(9);
        binarySearchTree.add(2);
        binarySearchTree.add(5);
        binarySearchTree.add(8);
        binarySearchTree.add(12);
        binarySearchTree.add(10);
        binarySearchTree.add(14);

        System.out.println(breadthFirstSearch(binarySearchTree));
    }

    private static String breadthFirstSearch(BinarySearchTree tree) {
        if (Objects.isNull(tree.getRoot())) {
            return "";
        }

        StringBuilder strBuilder = new StringBuilder();
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(tree.getRoot());

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            strBuilder.append(curNode.getData()).append(" ");

            if (!Objects.isNull(curNode.getLeftChild()))
                queue.offer(curNode.getLeftChild());
            if (!Objects.isNull(curNode.getRightChild()))
                queue.offer(curNode.getRightChild());
        }

        return strBuilder.toString();
    }
}
