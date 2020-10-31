package tree.dfs;

import java.util.*;

/*
Problem Statement #
Given a binary tree and a number ‘S’, find all paths from root-to-leaf such that the sum of all the node values of each path equals ‘S’.
 */
public class FindAllTreePaths {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        List<List<Integer>> result = FindAllTreePaths.findPaths(root, sum);
        System.out.println("Tree paths with sum " + sum + ": " + result);
    }

    public static List<List<Integer>> findPaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();

        checkPath(root, sum, new ArrayList<>(), allPaths);
        return allPaths;
    }

    private static void checkPath(TreeNode node, int sum, List<Integer> singlePath, List<List<Integer>> allPaths) {
        if (node == null) {
            return;
        }

        singlePath.add(node.val);
        if (node.val == sum && node.left == null && node.right == null) {
            allPaths.add(new ArrayList<>(singlePath));
            return;
        }

        if (node.left != null) {
            checkPath(node.left, sum - node.val, singlePath, allPaths);
            singlePath.remove(singlePath.size() - 1);
        }
        if (node.right != null) {
            checkPath(node.right, sum - node.val, singlePath, allPaths);
            singlePath.remove(singlePath.size() - 1);
        }
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
