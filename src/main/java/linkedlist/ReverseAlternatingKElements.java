package linkedlist;

/*
Reverse alternating K-element Sub-list (medium) #
Given the head of a LinkedList and a number ‘k’, reverse every alternating ‘k’ sized sub-list starting from the head.

If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
 */
public class ReverseAlternatingKElements {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next = new ListNode(9);

        ListNode result = ReverseAlternatingKElements.reverse(head, 2);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static ListNode reverse(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        ListNode current = head;
        ListNode prev = null;
        ListNode lastNodeInReversedSublist;

        while (current != null) {
            ListNode lastNodeOfPreviousPart = prev;
            lastNodeInReversedSublist = current;

            for (int i = 0; i < k && current != null; i++) {
                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }

            lastNodeInReversedSublist.next = current;
            if (lastNodeOfPreviousPart != null) {
                lastNodeOfPreviousPart.next = prev;
            } else {
                // assigning newHead
                head = prev;
            }

            for (int i = 0; i < k && current != null; i++) {
                prev = current;
                current = current.next;
            }
        }

        return head;
    }

    private static class ListNode {
        private int value;
        private ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }
}
