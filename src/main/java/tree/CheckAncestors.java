package tree;

public class CheckAncestors {

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

        System.out.println(findAncestors(binarySearchTree.getRoot(), 10));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(h) where h - is the height of the tree and O(N) in case of skewed tree
     */
    public static String findAncestors(Node root, int k) {
        if (root == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        Node curNode = root;

        while (curNode != null) {
            if (k == curNode.getData())
                return result.toString().substring(1);

            result.insert(0, curNode.getData()).insert(0, ",");

            if (k > curNode.getData())
                curNode = curNode.rightChild;
            else if (k < curNode.getData())
                curNode = curNode.leftChild;
        }

        return "";
    }
}
