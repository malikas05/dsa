package fastslowpointer;

/*
Problem Statement #
Given the head of a Singly LinkedList that contains a cycle, write a function to find the starting node of the cycle.
 */
public class LinkedListCycleStart {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value);

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value);

        head.next.next.next.next.next.next = head;
        System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value);
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
    */
    public static ListNode findCycleStart(ListNode head) {
        ListNode slow = findCycle(head);

        int cycleLength = calculateCycleLength(head, slow);
        ListNode pointer = head;
        slow = head;
        while (cycleLength-- > 0) {
            slow = slow.next;
        }

        while (pointer != slow) {
            pointer = pointer.next;
            slow = slow.next;
        }

        return pointer;
    }

    private static ListNode findCycle(ListNode head) {
        ListNode fastPointer = head;
        ListNode slowPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;

            if (fastPointer == slowPointer) {
                return slowPointer;
            }
        }

        return null;
    }

    private static int calculateCycleLength(ListNode head, ListNode slow) {
        int cycleCount = 0;
        ListNode current = head;
        do {
            cycleCount++;
            current = current.next;
        } while (current != slow);

        return cycleCount;
    }

    private static class ListNode {
        private int value;
        private ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }
}
