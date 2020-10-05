package leetcode_contests.weekly.weekly_209;

import java.util.ArrayDeque;
import java.util.Queue;

/*
1609. Even Odd Tree: https://leetcode.com/contest/weekly-contest-209/problems/even-odd-tree/
A binary tree is named Even-Odd if it meets the following conditions:
The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.

Example 1:
Input: root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
Output: true
Explanation: The node values on each level are:
Level 0: [1]
Level 1: [10,4]
Level 2: [3,7,9]
Level 3: [12,8,6,2]
Since levels 0 and 2 are all odd and increasing, and levels 1 and 3 are all even and decreasing, the tree is Even-Odd.

Example 2:
Input: root = [5,4,2,3,3,7]
Output: false
Explanation: The node values on each level are:
Level 0: [5]
Level 1: [4,2]
Level 2: [3,3,7]
Node values in the level 2 must be in strictly increasing order, so the tree is not Even-Odd.

Example 3:
Input: root = [5,9,1,3,5,7]
Output: false
Explanation: Node values in the level 1 should be even integers.

Example 4:
Input: root = [1]
Output: true

Example 5:
Input: root = [11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17]
Output: true

Constraints:
The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 106
 */
public class EvenOddTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.left.left = new TreeNode(12);
        root.left.left.right = new TreeNode(8);
        root.right.left.left = new TreeNode(6);
        root.right.right.right = new TreeNode(2);

        System.out.println(isEvenOddTree(root));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    public static boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        return breadthFirstTraversal(queue, 0);
    }

    private static boolean breadthFirstTraversal(Queue<TreeNode> queue, int level) {
        Queue<TreeNode> children = new ArrayDeque<>();
        boolean isEvenLevel = level % 2 == 0;

        if (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int prevValue = isEvenLevel ? node.val - 1 : node.val + 1;
            while (node != null) {
                if (isEvenLevel) {
                    if (node.val % 2 == 0 || node.val <= prevValue) return false;
                } else {
                    if (node.val % 2 != 0 || node.val >= prevValue) return false;
                }

                if (node.left != null) children.offer(node.left);
                if (node.right != null) children.offer(node.right);

                prevValue = node.val;
                node = queue.poll();
            }

            return breadthFirstTraversal(children, level + 1);
        }

        return true;
    }

    private static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
