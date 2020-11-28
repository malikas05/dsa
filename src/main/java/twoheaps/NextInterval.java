package twoheaps;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
Next Interval (hard) #
Given an array of intervals, find the next interval of each interval.
In a list of intervals, for an interval ‘i’ its next interval ‘j’ will have the smallest ‘start’ greater than or equal to the ‘end’ of ‘i’.
Write a function to return an array containing indices of the next interval of each input interval.
If there is no next interval of a given interval, return -1. It is given that none of the intervals have the same start point.

Example 1:
Input: Intervals [[2,3], [3,4], [5,6]]
Output: [1, 2, -1]
Explanation: The next interval of [2,3] is [3,4] having index ‘1’. Similarly, the next interval of [3,4] is [5,6] having index ‘2’. There is no next interval for [5,6] hence we have ‘-1’.

Example 2:
Input: Intervals [[3,4], [1,5], [4,6]]
Output: [2, -1, -1]
Explanation: The next interval of [3,4] is [4,6] which has index ‘2’. There is no next interval for [1,5] and [4,6].
 */
public class NextInterval {
    /*
    - Complexity Analysis:
    Time complexity: O(N log N)
    Space complexity: O(N)
     */
    public static int[] findNextInterval(Interval[] intervals) {
        int[] result = new int[intervals.length];
        PriorityQueue<Interval> minHeapStart = new PriorityQueue<>(Comparator.comparingInt(Interval::getStart));
        PriorityQueue<Interval> minHeapStartCopy = new PriorityQueue<>(Comparator.comparingInt(Interval::getStart));

        for (int i = 0; i < intervals.length; i++) {
            intervals[i].index = i;
            minHeapStart.offer(intervals[i]);
            minHeapStartCopy.offer(intervals[i]);
        }

        for (int i = 0; i < intervals.length; i++) {
            Interval currentInterval = intervals[i];
            while (!minHeapStart.isEmpty() && (currentInterval.end > minHeapStart.peek().start || currentInterval.equals(minHeapStart.peek()))) {
                minHeapStart.poll();
            }

            result[i] = minHeapStart.isEmpty() ? -1 : minHeapStart.peek().index;
            minHeapStart.clear();
            minHeapStart.addAll(minHeapStartCopy);
        }

        return result;
    }

    public static void main(String[] args) {
        Interval[] intervals = new Interval[]{new Interval(2, 3), new Interval(3, 4), new Interval(5, 6)};
        int[] result = NextInterval.findNextInterval(intervals);
        System.out.print("Next interval indices are: ");
        for (int index : result)
            System.out.print(index + " ");
        System.out.println();

        intervals = new Interval[]{new Interval(3, 4), new Interval(1, 5), new Interval(4, 6)};
        result = NextInterval.findNextInterval(intervals);
        System.out.print("Next interval indices are: ");
        for (int index : result)
            System.out.print(index + " ");
    }

    private static class Interval {
        private int start;
        private int end;
        private int index;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Interval interval = (Interval) o;

            if (start != interval.start) return false;
            if (end != interval.end) return false;
            return index == interval.index;
        }

        @Override
        public int hashCode() {
            int result = start;
            result = 31 * result + end;
            result = 31 * result + index;
            return result;
        }
    }
}
