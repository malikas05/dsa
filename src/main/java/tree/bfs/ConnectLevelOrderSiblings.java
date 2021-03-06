package tree.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/*
Problem Statement #
Given a binary tree, connect each node with its level order successor. The last node of each level should point to a null node.
 */
public class ConnectLevelOrderSiblings {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        ConnectLevelOrderSiblings.connect(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    public static void connect(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queueForSameLevelNodes = new ArrayDeque<>();
        queueForSameLevelNodes.offer(root);

        while (!queueForSameLevelNodes.isEmpty()) {
            int size = queueForSameLevelNodes.size();
            TreeNode prev = null;

            for (int i = 0; i < size; i++) {
                TreeNode current = queueForSameLevelNodes.poll();
                if (prev != null) {
                    prev.next = current;
                }

                if (current.left != null) {
                    queueForSameLevelNodes.offer(current.left);
                }

                if (current.right != null) {
                    queueForSameLevelNodes.offer(current.right);
                }

                prev = current;
            }
        }
    }

    private static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        private TreeNode next;

        TreeNode(int x) {
            val = x;
        }

        // level order traversal using 'next' pointer
        public void printLevelOrder() {
            TreeNode nextLevelRoot = this;
            while (nextLevelRoot != null) {
                TreeNode current = nextLevelRoot;
                nextLevelRoot = null;
                while (current != null) {
                    System.out.print(current.val + " ");
                    if (nextLevelRoot == null) {
                        if (current.left != null)
                            nextLevelRoot = current.left;
                        else if (current.right != null)
                            nextLevelRoot = current.right;
                    }
                    current = current.next;
                }
                System.out.println();
            }
        }
    }
}
