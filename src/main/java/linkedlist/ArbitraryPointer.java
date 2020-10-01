package linkedlist;

import java.util.HashMap;
import java.util.Map;

public class ArbitraryPointer {

    public static LinkedListNode deepCopyArbitraryPointer(LinkedListNode head) {
        if (head == null) {
            return null;
        }
        Map<LinkedListNode, LinkedListNode> map = new HashMap<>();
        LinkedListNode cur = head;
        while (cur != null) {
            map.putIfAbsent(cur, new LinkedListNode(cur.data));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).arbitraryPointer = map.get(cur.arbitraryPointer);

            cur = cur.next;
        }

        return map.get(head);
    }
}
