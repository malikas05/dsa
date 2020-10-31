package tree.dfs;

/*
Problem Statement #
Given a binary tree and a number sequence,
find if the sequence is present as a root-to-leaf path in the given tree.
 */
public class PathWithGivenSequence {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[] { 1, 0, 7 }));
        System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[] { 1, 1, 6 }));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N) for recursion
     */
    public static boolean findPath(TreeNode root, int[] sequence) {
        if (root == null || sequence == null) {
            return false;
        }

        return findPathByLevel(root, sequence, 0);
    }

    private static boolean findPathByLevel(TreeNode node, int[] sequence, int level) {
        if (node == null || level >= sequence.length || sequence[level] != node.val) {
            return false;
        }

        if (node.left == null && node.right == null && level == sequence.length - 1) {
            return true;
        }

        return findPathByLevel(node.left, sequence, level + 1) || findPathByLevel(node.right, sequence, level + 1);
    }

    private static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }
}
