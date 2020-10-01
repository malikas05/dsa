package tree;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

public class CheckKthMax {

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.add(6);
        binarySearchTree.add(4);
        binarySearchTree.add(2);
        binarySearchTree.add(5);
        binarySearchTree.add(9);
        binarySearchTree.add(8);
        binarySearchTree.add(12);
        binarySearchTree.add(10);
        binarySearchTree.add(14);

        System.out.println(findKthMax(binarySearchTree.getRoot(), 3));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    public static int findKthMax(Node root, int k) {
        StringBuilder result = new StringBuilder();
        inOrderTraversal(root, result);

        String[] array = result.toString().split(",");
        if (Integer.parseInt(array[array.length - k]) >= 0) {
            return Integer.parseInt(array[array.length - k]);
        }

        return -1;
    }

    // In Order Traversal is used to sort the tree in ascending order
    private static void inOrderTraversal(Node node, StringBuilder result) {
        if (node.getLeftChild() != null)
            inOrderTraversal(node.getLeftChild(), result);
        result.append(node.getData()).append(",");
        if (node.getRightChild() != null)
            inOrderTraversal(node.getRightChild(), result);
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log N)
    Space complexity: O(N)
     */
    public static int findKthMax2(Node root, int k) {
        if (root == null || k == 0) {
            return -1;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            heap.offer(curNode.getData());

            if (curNode.leftChild != null) {
                queue.offer(curNode.leftChild);
            }
            if (curNode.rightChild != null) {
                queue.offer(curNode.rightChild);
            }
        }

        int i = 0;
        while (!heap.isEmpty() && i < k - 1) {
            heap.poll();
            i++;
        }

        return i == k - 1 ? (!heap.isEmpty() ? heap.poll() : -1 ) : -1;
    }
}
