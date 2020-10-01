package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
Given an array of integers, find the most frequent integer.
 */
public class MostFrequentInteger {

    @Test
    public void testFindMostFrequent() {
        Assert.assertEquals(1, findMostFrequent2(new int[]{1, 1, 1, 2, 2, 3}));
    }

    @Test
    public void testFindTopMostFrequent() {
        System.out.println(Arrays.toString(findTopMostFrequent2(new int[]{4, 1, -1, 2, -1, 2, 3}, 2)));
    }

    /*
    - Concept: https://www.geeksforgeeks.org/find-the-maximum-repeating-number-in-ok-time/
    - Complexity Analysis:
    Time complexity: O(n)
    Space complexity: O(1)
     */
    public int findMostFrequent(int[] arr) {
        int length = arr.length;
        int freqIntIndex = 0;
        int maxCountFreqInt = arr[0];

        for (int i = 0; i < length; i++) {
            arr[arr[i] % length] += length;
        }

        for (int i = 1; i < length; i++) {
            if (arr[i] > maxCountFreqInt) {
                maxCountFreqInt = arr[i];
                freqIntIndex = i;
            }
        }

        return freqIntIndex;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(n)
    Space complexity: O(n) - to store in HashMap
     */
    public int findMostFrequent2(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        AtomicInteger maxCountFreqInt = new AtomicInteger();
        AtomicInteger freqInt = new AtomicInteger();

        for (int num : arr) {
            freq.compute(num, (k, v) -> {
                if (!Objects.isNull(v)) {
                    v++;
                    if (v > maxCountFreqInt.get()) {
                        maxCountFreqInt.set(v);
                        freqInt.set(k);
                    }
                    return v;
                }
                return 1;
            });
        }

        return freqInt.get();
    }

    /*
    Info: Given a non-empty array of integers, return the k most frequent elements.
    - Complexity Analysis:
    Time complexity: O(n log(n))
    Space complexity: O(n) - to store in HashMap
     */
    public int[] findTopMostFrequent(int[] arr, int top) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : arr) {
            freq.compute(num, (k, v) -> Objects.isNull(v) ? 1 : ++v);
        }

        int[] result = freq.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(top)
                .mapToInt(Map.Entry::getKey)
                .toArray();
        return result;
    }

    /*
    In order to achieve time complexity better than O(n log n), we have to come up with another solution using Heap.

    Concept:
    We take advantage of using PriorityQueue here which is equivalent to Heap in DSA. We specifically use Min Heap here
    so the root element appears always to be the smallest one and children elements are larger than their parent(root)

    Useful links:
    https://www.geeksforgeeks.org/heap-data-structure/
    https://www.geeksforgeeks.org/max-heap-in-java/
    https://www.geeksforgeeks.org/min-heap-in-java/

    - Complexity Analysis:
    Time complexity: O(N log(k)). The complexity of Counter method is O(N). To build a heap and output list takes O(N log(k). Hence the overall complexity of the algorithm is O(N + N log(k)) = O(N log(k))
    Space complexity : O(N) to store the hash map.
     */
    public int[] findTopMostFrequent2(int[] arr, int top) {
        Map<Integer, Integer> freq = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (int num : arr) {
            freq.compute(num, (k, v) -> Objects.isNull(v) ? 1 : ++v);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(freq::get)); // using Comparator.comparingInt to make it Min Heap
        for (Integer key : freq.keySet()) {
            heap.add(key); // the elements will be added based on the min heap logic
            if (heap.size() > top) {
                heap.poll(); // as soon as the size reaches top K elements, the smallest is removed in root
            }
        }

        while (!heap.isEmpty()) {
            result.add(heap.poll());
        }

        Collections.reverse(result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

}
