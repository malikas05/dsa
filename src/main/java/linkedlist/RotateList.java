package linkedlist;

public class RotateList {

    public static void main(String[] args) {
        LinkedListNode head =
                new LinkedListNode(1,
                        new LinkedListNode(2,
                                new LinkedListNode(3,
                                        new LinkedListNode(4,
                                                new LinkedListNode(5)
                                        )
                                )
                        )
                );

        rotateList(head, 3);
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static LinkedListNode rotateList(LinkedListNode head, int n) {
        if (head == null || n == 0) {
            return head;
        }

        LinkedListNode cur = head;
        LinkedListNode lastNode = null;
        int size = 0;

        while (cur != null) {
            size++;
            lastNode = cur;
            cur = cur.next;
        }

        n = n % size;
        if (n < 0) {
            n = size + n;
        } else if (n == 0) {
            return head;
        }

        cur = head;
        while (cur != null && n-- > 0) {
            cur = cur.next;
        }

        lastNode.next = head;
        head = cur.next;
        cur.next = null;

        return head;
    }
}
