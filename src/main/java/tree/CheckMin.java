package tree;

public class CheckMin {

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.add(6);
        binarySearchTree.add(4);
        binarySearchTree.add(9);
        binarySearchTree.add(2);
        binarySearchTree.add(5);
        binarySearchTree.add(8);
        binarySearchTree.add(12);
        binarySearchTree.add(10);
        binarySearchTree.add(14);

        binarySearchTree.printTree(binarySearchTree.getRoot());

        System.out.println("\nIterative approach: " + findMinIterative(binarySearchTree.getRoot()));
        System.out.println("Recursive approach: " + findMinRecursive(binarySearchTree.getRoot()));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(h) where h - is the height of the tree. In the worst case,
    the BST will be left skewed and the height will be nn and so the time complexity will be O(n)O(n)
    Space complexity: O(1)
     */
    public static int findMinIterative(Node root) {
        int minValue = Integer.MIN_VALUE;
        while (root != null) {
            minValue = root.getData();
            root = root.getLeftChild();
        }

        return minValue;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(h) where h - is the height of the tree. In the worst case,
    the BST will be left skewed and the height will be nn and so the time complexity will be O(n)O(n)
    Space complexity: O(1)
     */
    public static int findMinRecursive(Node root) {
        if (root == null) {
            return -1;
        }

        if (root.leftChild == null) {
            return root.getData();
        } else {
            return findMinRecursive(root.getLeftChild());
        }
    }
}
