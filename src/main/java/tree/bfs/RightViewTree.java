package tree.bfs;

import java.util.*;

/*
Right View of a Binary Tree (easy) #
Given a binary tree, return an array containing nodes in its right view. The right view of a binary tree is the set of nodes visible when the tree is seen from the right side.
 */
public class RightViewTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.left.left.left = new TreeNode(3);
        List<TreeNode> result = RightViewTree.traverse(root);
        for (TreeNode node : result) {
            System.out.print(node.val + " ");
        }
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    public static List<TreeNode> traverse(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queueForSameLevelNodes = new ArrayDeque<>();
        queueForSameLevelNodes.offer(root);

        while (!queueForSameLevelNodes.isEmpty()) {
            int size = queueForSameLevelNodes.size();

            for (int i = 0; i < size; i++) {
                TreeNode current = queueForSameLevelNodes.poll();

                if (i == size - 1) {
                    result.add(current);
                }

                if (current.left != null) {
                    queueForSameLevelNodes.offer(current.left);
                }

                if (current.right != null) {
                    queueForSameLevelNodes.offer(current.right);
                }
            }
        }

        return result;
    }

    private static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
