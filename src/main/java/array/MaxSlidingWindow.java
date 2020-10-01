package array;

import java.util.*;

public class MaxSlidingWindow {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Array = " + Arrays.toString(array));
        System.out.println("Max = " + findMaxSlidingWindow(array, 3));

        int[] array2 = {10, 6, 9, -3, 23, -1, 34, 56, 67, -1, -4, -8, -2, 9, 10, 34, 67};
        System.out.println("Array = " + Arrays.toString(array2));
        System.out.println("Max = " + findMaxSlidingWindow(array2, 3));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(W)
     */
    public static ArrayDeque<Integer> findMaxSlidingWindow(int[] arr, int windowSize) {
        ArrayDeque<Integer> result = new ArrayDeque<>();
        Deque<Integer> list = new LinkedList<>();

        if (arr.length > 0) {
            if (arr.length < windowSize) {
                return result;
            }

            for (int i = 0; i < windowSize; i++) {
                while (!list.isEmpty() && arr[i] >= arr[list.peekLast()])
                    list.removeLast();

                list.addLast(i);
            }

            for (int i = windowSize; i < arr.length; i++) {
                result.add(arr[list.peek()]);

                while (!list.isEmpty() && list.peek() <= i - windowSize)
                    list.removeFirst();

                while (!list.isEmpty() && arr[i] >= arr[list.peekLast()])
                    list.removeLast();

                list.addLast(i);
            }

            result.add(arr[list.peek()]);
        }

        return result;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log(W)), where N - is the number of elements in arr and W - is the sliding window
    Space complexity: O(W)
     */
    public static ArrayDeque<Integer> findMaxSlidingWindow2(int[] arr, int windowSize) {
        ArrayDeque<Integer> result = new ArrayDeque<>();
        Deque<Integer> list = new LinkedList<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());

        if (arr.length > 0) {
            if (arr.length < windowSize) {
                return result;
            }

            for (int i = 0; i < windowSize; i++) {
                list.addFirst(arr[i]);
                heap.offer(arr[i]);
            }

            result.add(heap.poll());
            list.removeLast();

            for (int i = windowSize; i < arr.length; i++) {
                list.addFirst(arr[i]);
                heap.addAll(list);
                result.add(heap.poll());

                list.removeLast();
                heap.clear();
            }
        }

        return result;
    }
}
