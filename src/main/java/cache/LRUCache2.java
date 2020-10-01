package cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache2 {
    public static void main(String[] args) {
        LRUCache2 cache = new LRUCache2(2);

        cache.put(1, 1);
        cache.put(2, 2);
        int first = cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        int second = cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        int third = cache.get(1);       // returns -1 (not found)
        int fourth = cache.get(3);       // returns 3
        int fifth = cache.get(4);
    }

    private Integer capacity;
    private Map<Integer, LinkedList.Node> cache;
    private LinkedList linkedList;

    public LRUCache2(Integer capacity) {
        this.capacity = capacity;
        this.linkedList = new LinkedList();
        this.cache = new HashMap<>();
    }

    public Integer get(Integer key) {
        if (cache.containsKey(key)) {
            LinkedList.Node target = cache.get(key);
            this.linkedList.moveToHead(target);
            return target.value;
        } else {
            return -1;
        }
    }

    public void put(Integer key, Integer value) {
        LinkedList.Node oldNode = cache.get(key);
        if (oldNode != null) {
            oldNode.value = value;
            this.linkedList.moveToHead(oldNode);
            return;
        }

        LinkedList.Node node = new LinkedList.Node(key, value);
        if (cache.size() == capacity) {
            this.cache.remove(this.linkedList.getTail().key);
            this.linkedList.removeTail();
        }
        this.linkedList.addToHead(node);
        this.cache.put(key, node);
    }

    private static final class LinkedList {
        private Node head;
        private Node tail;

        public LinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        public void moveToHead(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            addToHead(node);
        }

        public void addToHead(Node node) {
            Node tmp = head.next;
            head.next = node;
            node.next = tmp;
            node.prev = head;
            tmp.prev = node;
        }

        public void removeTail() {
            Node tmp = tail.prev.prev;
            tmp.next = tail;
            tail.prev = tmp;
        }

        public Node getHead() {
            return head.next;
        }

        public Node getTail() {
            return tail.prev;
        }

        private static final class Node {
            private Integer key;
            private Integer value;
            private Node next;
            private Node prev;

            public Node(Integer key, Integer value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
