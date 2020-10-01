package cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.TreeMap;

/*
https://leetcode.com/problems/lfu-cache/
 */
public class LFUCache {

    /*
    - Complexity Analysis:
    Time complexity: O(1) for get and put operations
    Space complexity: O(N)
    */
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }

    private int capacity;
    private int minCountKey = 1;

    private HashMap<Integer, Integer> cache;
    private HashMap<Integer, Integer> counts;
    private HashMap<Integer, LinkedHashSet<Integer>> frequencies;

    public LFUCache(int capacity) {
        this.capacity = capacity;

        this.cache = new HashMap<>();
        this.counts = new HashMap<>();
        this.frequencies = new HashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key))
            return -1;

        int oldCount = counts.get(key);
        int newCount = counts.computeIfPresent(key, (k, v) -> ++v);
        frequencies.get(oldCount).remove(key);
        removeFreq(oldCount, newCount);
        putIntoFreq(key, newCount);

        return cache.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0 )
            return;

        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key);
            return;
        }

        if (cache.size() >= capacity) {
            Integer next = frequencies.get(minCountKey).iterator().next();
            frequencies.get(minCountKey).remove(next);
            cache.remove(next);
            counts.remove(next);

            if (frequencies.get(minCountKey).isEmpty()) {
                frequencies.remove(minCountKey);
            }
        }

        cache.put(key, value);
        counts.put(key, 1);
        putIntoFreq(key, 1);
        minCountKey = 1;
    }

    private void putIntoFreq(int key, int count) {
        frequencies.computeIfAbsent(count, k -> new LinkedHashSet<>()).add(key);
    }

    private void removeFreq(int count, int newCount) {
        if (!frequencies.containsKey(count)) {
            return;
        }

        if (frequencies.get(count).isEmpty()) {
            frequencies.remove(count);
            minCountKey = frequencies.get(minCountKey) == null || frequencies.get(minCountKey).isEmpty() ? newCount : minCountKey;
        }
    }
}
