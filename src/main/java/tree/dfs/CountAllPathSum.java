package tree.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/*
Problem Statement #
Given a binary tree and a number ‘S’, find all paths in the tree such that the sum of all the node values of each path
equals ‘S’. Please note that the paths can start or end at any node but all paths must follow direction from parent to child (top to bottom).
 */
public class CountAllPathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + CountAllPathSum.countPaths(root, 11));

        root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(6);
        root.left.left = new TreeNode(5);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(3);
        System.out.println("Tree has path: " + CountAllPathSum.countPaths(root, 12));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N^2)
    Space complexity: O(N) for storing subpath or recursion
     */
    public static int countPaths(TreeNode root, int S) {
        return countPathsRec(root, S, new ArrayList<>());
    }

    private static int countPathsRec(TreeNode node, int S, List<Integer> subPath) {
        if (node == null) {
            return 0;
        }

        subPath.add(node.val);
        int pathCount = 0, pathSum = 0;
        ListIterator<Integer> listIterator = subPath.listIterator(subPath.size());

        while (listIterator.hasPrevious()) {
            pathSum += listIterator.previous();
            if (pathSum == S) {
                pathCount++;
            }
        }

        pathCount += countPathsRec(node.left, S, subPath);
        pathCount += countPathsRec(node.right, S, subPath);
        subPath.remove(subPath.size() - 1);

        return pathCount;
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
