package mergeintervals;

import java.util.*;

/*
Problem Statement #
Given a list of intervals, merge all the overlapping intervals to produce a list that has only mutually exclusive intervals.

Example 1:
Intervals: [[1,4], [2,5], [7,9]]
Output: [[1,5], [7,9]]
Explanation: Since the first two intervals [1,4] and [2,5] overlap, we merged them into
one [1,5].

Example 2:
Intervals: [[6,7], [2,4], [5,9]]
Output: [[2,4], [5,9]]
Explanation: Since the intervals [6,7] and [5,9] overlap, we merged them into one [5,9].

Example 3:
Intervals: [[1,4], [2,6], [3,5]]
Output: [[1,6]]
Explanation: Since all the given intervals overlap, we merged them into one.
 */

public class MergeIntervals {
    public static void main(String[] args) {
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 5));
        input.add(new Interval(7, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new Interval(6, 7));
        input.add(new Interval(2, 4));
        input.add(new Interval(5, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 6));
        input.add(new Interval(3, 5));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log(N)) - for sorting
    Space complexity: O(N) - For Java, depending on its version, Collection.sort() either uses Merge sort or Timsort, and both these algorithms need O(N) space
    */
    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() < 2)
            return intervals;

        Collections.sort(intervals, Comparator.comparingInt(a -> a.start));
        List<Interval> mergedIntervals = new LinkedList<>();

        Interval mergedInterval = null;
        for (Interval interval : intervals) {
            if (mergedInterval == null) {
                mergedInterval = interval;
                mergedIntervals.add(mergedInterval);
            } else {
                if (interval.start <= mergedInterval.end) {
                    mergedInterval.end = Math.max(interval.end, mergedInterval.end);
                } else {
                    mergedInterval = interval;
                    mergedIntervals.add(mergedInterval);
                }
            }
        }

        return mergedIntervals;
    }

    public static List<Interval> merge2(List<Interval> intervals) {
        if (intervals.size() < 2)
            return intervals;

        Collections.sort(intervals, Comparator.comparingInt(a -> a.start));
        List<Interval> mergedIntervals = new LinkedList<>();

        Iterator<Interval> intervalItr = intervals.iterator();
        Interval interval = intervalItr.next();
        int start = interval.start;
        int end = interval.end;

        while (intervalItr.hasNext()) {
            interval = intervalItr.next();
            if (interval.start <= end)
                end = Math.max(interval.end, end);
            else {
                mergedIntervals.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }

        mergedIntervals.add(new Interval(start, end));

        return mergedIntervals;
    }

    private static class Interval {
        private int start;
        private int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
