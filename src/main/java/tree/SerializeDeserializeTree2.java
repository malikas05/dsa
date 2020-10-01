package tree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeTree2 {
    private static final String splitter = ",";
    private static final String nullNode = "X";

    public static void main(String[] args) {
        Node node = new Node(1);
        node.setLeftChild(new Node(2));
        node.setRightChild(new Node(3));
        node.getRightChild().setLeftChild(new Node(4));
        node.getRightChild().setRightChild(new Node(5));

        String serializedStr = serialize(node);
        Node deserializeNode = deserialize(serializedStr);
    }

    public static String serialize2(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current == null) {
                result.append(nullNode).append(splitter);
            } else {
                result.append(current.getData()).append(splitter);
                queue.offer(current.getLeftChild());
                queue.offer(current.getRightChild());
            }
        }

        return result.toString();
    }

    public static String serialize(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current == null) {
                result.append(nullNode).append(splitter);
            } else {
                result.append(current.getData()).append(splitter);
                queue.offer(current.getLeftChild());
                queue.offer(current.getRightChild());
            }
        }

        return result.toString();
    }

    public static Node deserialize(String str) {
        if (str == null || str.isEmpty() || str.toCharArray()[0] == splitter.toCharArray()[0]) {
            return null;
        }

        String[] strSplit = str.split(splitter);
        int startIndex = 0;
        Queue<Node> queue = new ArrayDeque<>();

        Node root = new Node(Integer.parseInt(strSplit[startIndex++]));
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (startIndex < strSplit.length) {
                if (!strSplit[startIndex].equals(nullNode)) {
                    current.setLeftChild(new Node(Integer.parseInt(strSplit[startIndex])));
                    queue.offer(current.getLeftChild());
                }
                startIndex++;
            }

            if (startIndex < strSplit.length) {
                if (!strSplit[startIndex].equals(nullNode)) {
                    current.setRightChild(new Node(Integer.parseInt(strSplit[startIndex])));
                    queue.offer(current.getRightChild());
                }
                startIndex++;
            }
        }

        return root;
    }
}
