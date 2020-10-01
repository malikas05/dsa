package tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class DFS {

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

        System.out.println(String.format("DFS: %s", depthFirstSearch(binarySearchTree)));
        System.out.println(String.format("DFS - Pre-order: %s", dfsPre(binarySearchTree.getRoot(), new StringBuilder())));
        System.out.println(String.format("DFS - In-order: %s", dfsIn(binarySearchTree.getRoot(), new StringBuilder())));
        System.out.println(String.format("DFS - Post-order: %s", dfsPost(binarySearchTree.getRoot(), new StringBuilder())));
    }

    private static String depthFirstSearch(BinarySearchTree tree) {
        if (Objects.isNull(tree.getRoot())) {
            return "";
        }

        StringBuilder strBuilder = new StringBuilder();
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(tree.getRoot());

        while (!stack.isEmpty()) {
            Node curNode = stack.pop();

            strBuilder.append(curNode.getData()).append(" ");

            if (!Objects.isNull(curNode.getRightChild()))
                stack.push(curNode.getRightChild());
            if (!Objects.isNull(curNode.getLeftChild()))
                stack.push(curNode.getLeftChild());
        }

        return strBuilder.toString();
    }

    private static String dfsPre(Node node, StringBuilder strBuilder) {
        if (!Objects.isNull(node)) {
            strBuilder.append(node.getData()).append(" ");
            dfsPre(node.getLeftChild(), strBuilder);
            dfsPre(node.getRightChild(), strBuilder);
        }
        return strBuilder.toString();
    }

    private static String dfsIn(Node node, StringBuilder strBuilder) {
        if (!Objects.isNull(node)) {
            dfsIn(node.getLeftChild(), strBuilder);
            strBuilder.append(node.getData()).append(" ");
            dfsIn(node.getRightChild(), strBuilder);
        }
        return strBuilder.toString();
    }

    private static String dfsPost(Node node, StringBuilder strBuilder) {
        if (!Objects.isNull(node)) {
            dfsPost(node.getLeftChild(), strBuilder);
            dfsPost(node.getRightChild(), strBuilder);
            strBuilder.append(node.getData()).append(" ");
        }
        return strBuilder.toString();
    }
}
