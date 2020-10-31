package tree.dfs;

/*
Problem Statement #
Given a binary tree where each node can only have a digit (0-9) value,
each root-to-leaf path will represent a number. Find the total sum of all the numbers represented by all paths.

 */
public class SumOfPathNumbers {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);
        System.out.println("Total Sum of Path Numbers: " + SumOfPathNumbers.findSumOfPathNumbers(root));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N) for recursion
     */
    public static int findSumOfPathNumbers(TreeNode root) {
        return findPath(root, 0);
    }

    private static int findPath(TreeNode node, int pathSum) {
        if (node == null) {
            return 0;
        }

        pathSum = pathSum * 10 + node.val;
        if (node.left == null && node.right == null) {
            return pathSum;
        }

        return findPath(node.left, pathSum) + findPath(node.right, pathSum);
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
