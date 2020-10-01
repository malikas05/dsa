package tree;

public class NthHighest {

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.add(100);
        binarySearchTree.add(50);
        binarySearchTree.add(200);
        binarySearchTree.add(25);
        binarySearchTree.add(75);
        binarySearchTree.add(125);
        binarySearchTree.add(350);

        System.out.println(findNthHighestInBST(binarySearchTree.getRoot(), 7).getData());
    }

    public static int currentCount = 0;

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static Node findNthHighestInBST(Node node, int n) {
        if (node == null)
            return null;

        Node foundNode = postOrderTraversal(node, n);
        return n > currentCount ? null : foundNode;
    }

    private static Node postOrderTraversal(Node node, int n) {
        Node foundNode;
        if (node.getRightChild() != null) {
            foundNode = postOrderTraversal(node.getRightChild(), n);
            if (currentCount == n)
                return foundNode;
        }

        if (++currentCount == n)
            return node;

        if (node.getLeftChild() != null) {
            foundNode = postOrderTraversal(node.getLeftChild(), n);
            if (currentCount == n)
                return foundNode;
        }

        return node;
    }
}
