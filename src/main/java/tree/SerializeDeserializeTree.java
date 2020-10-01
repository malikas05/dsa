package tree;

import java.util.*;

/*
https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
public class SerializeDeserializeTree {

    public static void main(String[] args) {
        Node node = new Node(1);
        node.setLeftChild(new Node(2));
        node.setRightChild(new Node(3));
        node.getRightChild().setLeftChild(new Node(4));
        node.getRightChild().setRightChild(new Node(5));

        String serializedStr = serialize2(node);
        Node deserializedNode = deserialize2(serializedStr);
    }

    private static final String splitter = ",";
    private static final String nullNode = "X";

    public static String serialize(Node root) {
        StringBuilder result = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur == null) {
                result.append(nullNode).append(splitter);
            } else {
                result.append(cur.getData()).append(splitter);
                queue.offer(cur.getLeftChild());
                queue.offer(cur.getRightChild());
            }
        }

        return result.toString();
    }

    public static Node deserialize(String data) {
        String[] input = data.split(splitter);
        if (data.isEmpty() || input[0].isEmpty() || input[0].equals(nullNode)) {
            return null;
        }

        int i = 0;
        Node root = new Node(Integer.parseInt(input[i++]));
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (i < input.length) {
                if (!input[i].equals(nullNode)) {
                    current.setLeftChild(new Node(Integer.parseInt(input[i])));
                    queue.offer(current.getLeftChild());
                }
                i++;
            }
            if (i < input.length) {
                if (!input[i].equals(nullNode)) {
                    current.setRightChild(new Node(Integer.parseInt(input[i])));
                    queue.offer(current.getRightChild());
                }
                i++;
            }
        }

        return root;
    }

    public static String serialize2(Node node) {
        StringBuilder result = new StringBuilder();
        buildString(node, result);
        return result.toString();
    }

    private static void buildString(Node node, StringBuilder result) {
        if (node == null) {
            result.append(nullNode).append(splitter);
        } else {
            result.append(node.getData()).append(splitter);
            buildString(node.getLeftChild(), result);
            buildString(node.getRightChild(), result);
        }
    }

    public static Node deserialize2(String data) {
        Deque<String> queue = new ArrayDeque<>(Arrays.asList(data.split(splitter)));
        return buildTree(queue);
    }

    private static Node buildTree(Deque<String> queue) {
        String current = queue.poll();
        if (current == null || current.equals(nullNode)) return null;
        else {
            Node node = new Node(Integer.parseInt(current));
            node.setLeftChild(buildTree(queue));
            node.setRightChild(buildTree(queue));
            return node;
        }
    }
}
