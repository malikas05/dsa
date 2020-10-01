package tree;

public class CheckKNodes {

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

        System.out.println(findKNodes(binarySearchTree.getRoot(), 2));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
     */
    public static String findKNodes(Node root, int k) {
        if (root == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        postOrder(root, k, 0, result);

        return result.toString().substring(0, result.lastIndexOf(","));
    }

    private static void postOrder(Node node, int k, int curDepth, StringBuilder result) {
        if (node != null) {
            if (k != curDepth) {
                postOrder(node.getLeftChild(), k, curDepth + 1, result);
                postOrder(node.getRightChild(), k, curDepth + 1, result);
            } else {
                result.append(node.getData()).append(",");
            }
        }
    }
}
