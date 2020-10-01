package linkedlist;

public class AddIntegers {

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    static LinkedListNode addIntegers(LinkedListNode integer1, LinkedListNode integer2) {
        LinkedListNode result = null;
        LinkedListNode last = null;
        int carry = 0;

        while (integer1 != null || integer2 != null || carry != 0) {
            int first = integer1 != null ? integer1.data : 0;
            int second = integer2 != null ? integer2.data : 0;
            int sum = first + second + carry;
            LinkedListNode newNode = new LinkedListNode(sum % 10);
            carry = sum / 10;

            if (result == null) {
                result = newNode;
            } else {
                last.next = newNode;
            }

            last = newNode;
            if (integer1 != null) {
                integer1 = integer1.next;
            }
            if (integer2 != null) {
                integer2 = integer2.next;
            }
        }

        return result;
    }
}
