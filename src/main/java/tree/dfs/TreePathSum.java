package tree.dfs;

/*
Problem Statement #
Given a binary tree and a number ‘S’, find if the tree has a path from root-to-leaf
such that the sum of all the node values of that path equals ‘S’.
 */
public class TreePathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + TreePathSum.hasPath(root, 23));
        System.out.println("Tree has path: " + TreePathSum.hasPath(root, 16));
    }

    public static boolean hasPath(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.val == sum && root.left == null && root.right == null) {
            return true;
        }

        return hasPath(root.left, sum - root.val) || hasPath(root.right, sum - root.val);
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N) for recursion
     */
    public static boolean hasPath2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        return getRootToLeafSum(root, 0, sum);
    }

    private static boolean getRootToLeafSum(TreeNode node, int prevSum, int targetSum) {
        prevSum += node.val;
        if (node.left != null && getRootToLeafSum(node.left, prevSum, targetSum)) {
            return true;
        }

        if (node.right != null && getRootToLeafSum(node.right, prevSum, targetSum)) {
            return true;
        }

        return prevSum == targetSum;
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
