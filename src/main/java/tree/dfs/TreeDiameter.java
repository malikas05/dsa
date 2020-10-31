package tree.dfs;

/*
Tree Diameter (medium) #
Given a binary tree, find the length of its diameter.
The diameter of a tree is the number of nodes on the longest path between any two leaf nodes.
The diameter of a tree may or may not pass through the root.

Note: You can always assume that there are at least two leaf nodes in the given tree.
 */
public class TreeDiameter {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
        root.left.left = null;
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.left.right.left = new TreeNode(10);
        root.right.right.left.left = new TreeNode(11);
        System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N) - for recursion
     */
    public static int findDiameter(TreeNode root) {
        if (root == null) {
            return -1;
        }

        int[] maxRadius = new int[1];
        findDiameterRec(root, maxRadius);
        return maxRadius[0] + 1;
    }

    private static int findDiameterRec(TreeNode node, int[] maxRadius) {
        if (node == null) {
            return 0;
        }

        int countNodesLeft = findDiameterRec(node.left, maxRadius);
        int countNodesRight = findDiameterRec(node.right, maxRadius);

        maxRadius[0] = Math.max(maxRadius[0], countNodesLeft + countNodesRight);

        return Math.max(countNodesLeft, countNodesRight) + 1;
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
