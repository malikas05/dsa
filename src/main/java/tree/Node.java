package tree;

public class Node {
    private int data;
    Node leftChild;
    Node rightChild;

    Node(int value) {
        this.data = value;
        leftChild = null;
        rightChild = null;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public int getData() {
        return data;
    }

    public void setData(int value) {
        this.data = value;
    }

    public void setLeftChild(Node left) {
        this.leftChild = left;
    }

    public void setRightChild(Node right) {
        this.rightChild = right;
    }

}
