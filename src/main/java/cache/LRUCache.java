package cache;

import java.util.HashMap;

/*
- Concept: https://www.interviewcake.com/concept/java/lru-cache
- Leetcode: https://leetcode.com/problems/lru-cache/
 */
public class LRUCache {

    /*
    - Complexity Analysis:
    Time complexity: O(1) for get and put operations
    Space complexity: O(N)
    */
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

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

    private int capacity;

    private LinkedList linkedList;
    private HashMap<Integer, Node> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;

        linkedList = new LinkedList();
        cache = new HashMap<>();
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node target = cache.get(key);
            linkedList.moveToHead(target);
            return target.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node != null) {
            linkedList.moveToHead(node);
            node.value = value;
        } else {
            Node newNode = new Node(key, value);
            if (cache.size() == capacity) {
                cache.remove(linkedList.getTail().key);
                linkedList.removeTail();
            }
            linkedList.addToHead(newNode);
            cache.put(key, newNode);
        }
    }

    /*
    LinkedList and Node helper classes
     */
    class LinkedList {
        Node head;
        Node tail;

        public LinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        void moveToHead(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            addToHead(node);
        }

        void addToHead(Node node) {
            Node tmp = head.next;
            head.next = node;
            node.next = tmp;
            node.prev = head;
            tmp.prev = node;
        }

        void removeTail() {
            Node tmp = tail.prev.prev;
            tmp.next = tail;
            tail.prev = tmp;
        }

        Node getHead() {
            return head.next;
        }

        Node getTail() {
            return tail.prev;
        }
    }

    class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
