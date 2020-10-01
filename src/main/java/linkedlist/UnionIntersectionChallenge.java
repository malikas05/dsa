package linkedlist;

import java.util.HashSet;

public class UnionIntersectionChallenge {

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.insertAtHead(15);
        list1.insertAtHead(22);
        list1.insertAtHead(8);

        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
        list2.insertAtHead(7);
        list2.insertAtHead(14);
        list2.insertAtHead(22);

        list1.printList();
        list2.printList();
        SinglyLinkedList<Integer> union = intersection(list1, list2);
        union.printList();
    }

    public static <T> SinglyLinkedList<T> union(SinglyLinkedList<T> list1, SinglyLinkedList<T> list2) {
        SinglyLinkedList<T> result = new SinglyLinkedList<T>();
        // Write -- Your -- Code
        SinglyLinkedList<T>.Node curNode = list1.getHeadNode();
        SinglyLinkedList<T>.Node prevNode = null;
        if (!list1.isEmpty()) {
            result.insertAtHead(curNode.data);
            prevNode = result.getHeadNode();
            curNode = curNode.nextNode;

            while (curNode != null) {
                prevNode.nextNode = curNode;
                prevNode = curNode;
                curNode = curNode.nextNode;
            }
        }

        if (!list2.isEmpty()) {
            curNode = list2.getHeadNode();

            while (curNode != null) {
                prevNode.nextNode = curNode;
                prevNode = curNode;
                curNode = curNode.nextNode;
            }
        }

        result.removeDuplicatesWithHashing();
        return result;
    }

    public static <T> SinglyLinkedList<T> intersection(SinglyLinkedList<T> list1, SinglyLinkedList<T> list2) {
        SinglyLinkedList<T> result = new SinglyLinkedList<T>();
        // Write -- Your -- Code
        HashSet<T> intersection = new HashSet<>();
        SinglyLinkedList<T>.Node curNode = list1.getHeadNode();

        if (!list1.isEmpty()) {
            while (curNode != null) {
                intersection.add(curNode.data);
                curNode = curNode.nextNode;
            }
        }

        if (!list2.isEmpty()) {
            curNode = list2.getHeadNode();

            while (curNode != null) {
                if (intersection.contains(curNode.data)) {
                    result.insertAtHead(curNode.data);
                }
                curNode = curNode.nextNode;
            }
        }

        return result;
    }

}
