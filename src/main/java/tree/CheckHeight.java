package tree;

public class CheckHeight {

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.add(6);
        binarySearchTree.add(4);
        binarySearchTree.add(2);
        binarySearchTree.add(5);
        binarySearchTree.add(9);
        binarySearchTree.add(8);
        binarySearchTree.add(12);
        binarySearchTree.add(10);
        binarySearchTree.add(14);
        binarySearchTree.add(15);
        binarySearchTree.add(16);

        System.out.println(findHeight(binarySearchTree.getRoot()));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
     */
    public static int findHeight(Node root) {
        if (root == null)
            return -1;

        return 1 + Math.max(findHeight(root.getLeftChild()), findHeight(root.getRightChild()));
    }

    public static int findHeight2(Node root) {
        return postOrderHeight(root, 0, 0);
    }

    private static int postOrderHeight(Node node, int curHeight, int maxHeight) {
        if (node != null) {
            maxHeight = node.getLeftChild() != null ? Math.max(postOrderHeight(node.getLeftChild(), curHeight + 1, maxHeight), maxHeight) : curHeight;
            maxHeight = node.getRightChild() != null ? Math.max(postOrderHeight(node.getRightChild(), curHeight + 1, maxHeight), maxHeight) : curHeight;
        }

        return maxHeight;
    }
}
