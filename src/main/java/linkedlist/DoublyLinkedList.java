package linkedlist;

public class DoublyLinkedList<T extends Integer> {

    public class Node {
        private T data;
        private Node nextNode;
        private Node prevNode;

        public T getData() {
            return data;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public Node getPrevNode() {
            return prevNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        public void setPrevNode(Node prevNode) {
            this.prevNode = prevNode;
        }
    }

    private Node headNode;
    private Node tailNode;
    private int size;

    public DoublyLinkedList() {
        headNode = null;
        tailNode = null;
    }

    public Node getHeadNode() {
        return headNode;
    }

    public Node getTailNode() {
        return tailNode;
    }

    public int getSize() {
        return size;
    }

    public void setHeadNode(Node headNode) {
        this.headNode = headNode;
    }

    public void setTailNode(Node tailNode) {
        this.tailNode = tailNode;
    }

    public boolean isEmpty() {
        return headNode == null && tailNode == null;
    }

    public void insertAtHead(T data) {
        Node node = new Node();
        node.data = data;
        node.nextNode = headNode;
        node.prevNode = null;

        if (headNode != null) {
            headNode.prevNode = node;
        } else {
            tailNode = node;
        }
        headNode = node;
        size++;
    }

    public void insertAtEnd(T data) {
        if (isEmpty()) {
            insertAtHead(data);
            return;
        }

        Node node = new Node();
        node.data = data;
        node.prevNode = tailNode;
        node.nextNode = null;
        tailNode.nextNode = node;
        tailNode = node;
        size++;
    }

    public void deleteAtHead() {
        if (isEmpty()) {
            return;
        }

        headNode = headNode.nextNode;
        if (headNode == null) {
            tailNode = null;
        } else {
            headNode.prevNode = null;
        }
        size--;
    }

    public void deleteAtTail() {
        if (isEmpty()) {
            return;
        }

        tailNode = tailNode.prevNode;
        if (tailNode == null) {
            headNode = null;
        } else {
            tailNode.nextNode = null;
        }
        size--;
    }

    public void printList() {
        if (isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        Node curNode = headNode;
        System.out.println("List: null <- ");

        while (curNode.nextNode != null) {
            System.out.println(curNode.data.toString() + " <-> ");
            curNode = curNode.nextNode;
        }
        System.out.println(curNode.data.toString() + " -> null");
    }
}
