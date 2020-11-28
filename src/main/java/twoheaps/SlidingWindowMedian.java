package twoheaps;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
Problem Statement #
Given an array of numbers and a number ‘k’, find the median of all the ‘k’ sized sub-arrays (or windows) of the array.

Example 1:
Input: nums=[1, 2, -1, 3, 5], k = 2
Output: [1.5, 0.5, 1.0, 4.0]
Explanation: Lets consider all windows of size ‘2’:
[1, 2, -1, 3, 5] -> median is 1.5
[1, 2, -1, 3, 5] -> median is 0.5
[1, 2, -1, 3, 5] -> median is 1.0
[1, 2, -1, 3, 5] -> median is 4.0

Example 2:
Input: nums=[1, 2, -1, 3, 5], k = 3
Output: [1.0, 2.0, 3.0]
Explanation: Lets consider all windows of size ‘3’:
[1, 2, -1, 3, 5] -> median is 1.0
[1, 2, -1, 3, 5] -> median is 2.0
[1, 2, -1, 3, 5] -> median is 3.0
 */
public class SlidingWindowMedian {
    /*
    - Complexity Analysis:
    Time complexity: O(N * K) where N is the total number of elements in nums and K is the sliding window value
    Space complexity: O(K) where K is the sliding window value
     */
    public double[] findSlidingWindowMedian(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        PriorityQueue<NumberWrapper> minHeap = new PriorityQueue<>(Comparator.comparingInt(NumberWrapper::getNumber));
        PriorityQueue<NumberWrapper> maxHeap = new PriorityQueue<>((value1, value2) -> Integer.compare(value2.number, value1.number));
        int resultIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            NumberWrapper numberWrapper = new NumberWrapper(nums[i], i);
            insertNum(numberWrapper, minHeap, maxHeap);

            if (maxHeap.size() + minHeap.size() == k) {
                result[resultIndex] = findMedian(minHeap, maxHeap);
                if (!maxHeap.remove(new NumberWrapper(0, resultIndex))) {
                    minHeap.remove(new NumberWrapper(0, resultIndex));
                }

                resultIndex += 1;
            }
        }

        return result;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(log N)
     */
    private void insertNum(NumberWrapper numberWrapper, PriorityQueue<NumberWrapper> minHeap, PriorityQueue<NumberWrapper> maxHeap) {
        if (maxHeap.isEmpty() || numberWrapper.number <= maxHeap.peek().number) {
            maxHeap.offer(numberWrapper);
        } else {
            minHeap.offer(numberWrapper);
        }

        rebalanceHeaps(minHeap, maxHeap);
    }

    /*
    - Complexity Analysis:
    Time complexity: O(log N)
     */
    private void rebalanceHeaps(PriorityQueue<NumberWrapper> minHeap, PriorityQueue<NumberWrapper> maxHeap) {
        if (maxHeap.size() - minHeap.size() > 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    /*
    - Complexity Analysis:
    Time complexity: O(1)
     */
    private double findMedian(PriorityQueue<NumberWrapper> minHeap, PriorityQueue<NumberWrapper> maxHeap) {
        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.peek().number + maxHeap.peek().number) / 2.0;
        }

        return maxHeap.peek().number;
    }

    public static void main(String[] args) {
        SlidingWindowMedian slidingWindowMedian = new SlidingWindowMedian();
        double[] result = slidingWindowMedian.findSlidingWindowMedian(new int[] { 1, 2, -1, 3, 5 }, 2);
        System.out.print("Sliding window medians are: ");
        for (double num : result)
            System.out.print(num + " ");
        System.out.println();

        slidingWindowMedian = new SlidingWindowMedian();
        result = slidingWindowMedian.findSlidingWindowMedian(new int[] { 1, 2, -1, 3, 5 }, 3);
        System.out.print("Sliding window medians are: ");
        for (double num : result)
            System.out.print(num + " ");
        System.out.println();

        slidingWindowMedian = new SlidingWindowMedian();
        result = slidingWindowMedian.findSlidingWindowMedian(new int[] { 7, 2, 5, -1, 10, 3, 5, 15 }, 4);
        System.out.print("Sliding window medians are: ");
        for (double num : result)
            System.out.print(num + " ");
    }

    private static class NumberWrapper {
        private int number;
        private int index;

        public NumberWrapper(int number, int index) {
            this.number = number;
            this.index = index;
        }

        public int getNumber() {
            return number;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NumberWrapper that = (NumberWrapper) o;

            return index == that.index;
        }

        @Override
        public int hashCode() {
            return index;
        }
    }
}
