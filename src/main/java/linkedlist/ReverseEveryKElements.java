package linkedlist;

/*
Reverse alternating K-element Sub-list (medium) #
Given the head of a LinkedList and a number ‘k’, reverse every alternating ‘k’ sized sub-list starting from the head.
If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
 */
public class ReverseEveryKElements {
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
        head.next.next.next.next.next.next.next.next.next = new ListNode(10);


        ListNode result = ReverseEveryKElements.reverse(head, 3);
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
        ListNode lastKNode = current;
        ListNode prev = null;
        int i = 1;

        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;

            if (i == k) {
                lastKNode.next = current;
                head = prev;
                prev = null;
            } else if (i % k == 0) {
                ListNode temp = lastKNode.next;
                lastKNode.next = prev;
                lastKNode = temp;
                lastKNode.next = current;
                prev = null;
            }

            i++;
        }

        if (prev != null) {
            lastKNode.next = prev;
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
