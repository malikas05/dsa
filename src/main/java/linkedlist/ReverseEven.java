package linkedlist;

public class ReverseEven {

    public static void main(String[] args) {
        LinkedListNode head =
                new LinkedListNode(7,
                        new LinkedListNode(14,
                                new LinkedListNode(21,
                                        new LinkedListNode(28,
                                                new LinkedListNode(9)
                                        )
                                )
                        )
                );

        reverseEvenNodes(head);
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static LinkedListNode reverseEvenNodes(LinkedListNode head) {
        LinkedListNode cur = head;
        LinkedListNode curEvenHead = null;

        while (cur != null && cur.next != null) {
            LinkedListNode even = cur.next;
            cur.next = even.next;

            even.next = curEvenHead;
            curEvenHead = even;

            cur = cur.next;
        }

        return mergeAlternating(head, curEvenHead);
    }

    private static LinkedListNode mergeAlternating(LinkedListNode list1, LinkedListNode list2) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        LinkedListNode head = list1;

        while (list1.next != null && list2 != null) {
            LinkedListNode temp = list2;
            list2 = list2.next;

            temp.next = list1.next;
            list1.next = temp;
            list1 = temp.next;
        }

        if (list1.next == null) {
            list1.next = list2;
        }

        return head;
    }
}
