package tree.dfs;

/*
Path with Maximum Sum (hard) #
Find the path with the maximum sum in a given binary tree. Write a function that returns the maximum sum.
A path can be defined as a sequence of nodes between any two nodes and doesn’t necessarily pass through the root.
The path must contain at least one node.
 */
public class MaximumPathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println("Maximum Path Sum: " + MaximumPathSum.findMaximumPathSum(root));

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        System.out.println("Maximum Path Sum: " + MaximumPathSum.findMaximumPathSum(root));

        root = new TreeNode(-1);
        root.left = new TreeNode(-3);
        System.out.println("Maximum Path Sum: " + MaximumPathSum.findMaximumPathSum(root));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N) - for recursion
     */
    public static int findMaximumPathSum(TreeNode root) {
        int[] maxSum = {Integer.MIN_VALUE};
        findMaximumPathSumRec(root, maxSum);

        return maxSum[0];
    }

    public static int findMaximumPathSumRec(TreeNode node, int[] maxSum) {
        if (node == null) {
            return 0;
        }

        int leftSum = Math.max(findMaximumPathSumRec(node.left, maxSum), 0);
        int rightSum = Math.max(findMaximumPathSumRec(node.right, maxSum), 0);

        int currentSum = node.val + leftSum + rightSum;
        maxSum[0] = Math.max(maxSum[0], currentSum);

        return node.val + Math.max(leftSum, rightSum);
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
