package linkedlist;

public class ReverseK {
    public static void main(String[] args) {
        LinkedListNode head =
                new LinkedListNode(1,
                        new LinkedListNode(2,
                                new LinkedListNode(3,
                                        new LinkedListNode(4,
                                                new LinkedListNode(5,
                                                        new LinkedListNode(6,
                                                                new LinkedListNode(7)
                                                        )
                                                )
                                        )
                                )
                        )
                );

        reverseKNodes(head, 7);
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    static LinkedListNode reverseKNodes(LinkedListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        head = reverse(head, k);

        return head;
    }

    private static LinkedListNode reverse(LinkedListNode head, int k) {
        LinkedListNode cur = head;
        LinkedListNode prev = null;
        int counter = 0;

        while (cur != null && counter++ < k) {
            LinkedListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;

            if (counter == k) {
                head.next = reverse(cur, k);
            }
        }

        return prev;
    }
}
