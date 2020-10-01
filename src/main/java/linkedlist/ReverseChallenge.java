package linkedlist;

import java.util.Objects;

public class ReverseChallenge {

    public static void main(String[] args) {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
        singlyLinkedList.insertAtHead(101);
        singlyLinkedList.insertAtHead(171);
        singlyLinkedList.insertAtHead(241);

        singlyLinkedList.printList();
        reverse(singlyLinkedList, true);
        singlyLinkedList.printList();
    }

    /* Input data -> LinkedList
    1->2->3->4->5->NULL
     */

    public static <T> void reverse(SinglyLinkedList<T> list, boolean recursive) {
        if (list.isEmpty()) {
            return;
        }

        if (recursive)
            reverseRecursive2(list.getHeadNode());
        else
            reverseIterative2(list.getHeadNode());
    }

    private static <T> SinglyLinkedList<T>.Node reverseRecursive(SinglyLinkedList<T>.Node curNode, SinglyLinkedList<T> list) {
        if (curNode == null || curNode.nextNode == null) {
            list.getHeadNode().nextNode = null;
            list.setHeadNode(curNode);
            return curNode;
        }

        SinglyLinkedList<T>.Node prevNode = reverseRecursive(curNode.nextNode, list);
        prevNode.nextNode = curNode;
        return prevNode.nextNode;
    }

    private static <T> void reverseIterative(SinglyLinkedList<T>.Node curNode, SinglyLinkedList<T> list) {
        SinglyLinkedList<T>.Node prevNode = null;
        SinglyLinkedList<T>.Node nextNode = null;

        while (curNode != null) {
            nextNode = curNode.nextNode;
            curNode.nextNode = prevNode;
            prevNode = curNode;
            curNode = nextNode;
        }

        list.setHeadNode(prevNode);
    }

    /*
    This method returns the new head
     */
    private static <T> SinglyLinkedList<T>.Node reverseRecursive2(SinglyLinkedList<T>.Node head) {
        if (head == null || head.nextNode == null) {
            return head;
        }

        SinglyLinkedList<T>.Node prevNode = reverseRecursive2(head.nextNode);
        head.nextNode.nextNode = head;
        head.nextNode = null;

        return prevNode;
    }

    private static <T> SinglyLinkedList<T>.Node reverseIterative2(SinglyLinkedList<T>.Node head) {
        SinglyLinkedList<T>.Node prevNode = null;
        SinglyLinkedList<T>.Node curNode = head;

        while (curNode != null) {
            SinglyLinkedList<T>.Node tempNode = curNode.nextNode;
            curNode.nextNode = prevNode;
            prevNode = curNode;
            curNode = tempNode.nextNode;
        }

        return prevNode;
    }
}
