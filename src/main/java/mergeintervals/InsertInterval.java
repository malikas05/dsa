package mergeintervals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
Problem Statement #
Given a list of non-overlapping intervals sorted by their start time, insert a given interval at the correct position and merge all necessary intervals to produce a list that has only mutually exclusive intervals.

Example 1:
Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,6]
Output: [[1,3], [4,7], [8,12]]
Explanation: After insertion, since [4,6] overlaps with [5,7], we merged them into one [4,7].

Example 2:
Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,10]
Output: [[1,3], [4,12]]
Explanation: After insertion, since [4,10] overlaps with [5,7] & [8,12], we merged them into [4,12].

Example 3:
Input: Intervals=[[2,3],[5,7]], New Interval=[1,4]
Output: [[1,4], [5,7]]
Explanation: After insertion, since [1,4] overlaps with [2,3], we merged them into one [1,4].
 */
public class InsertInterval {
    public static void main(String[] args) {
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insert(input, new Interval(4, 6)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insert(input, new Interval(4, 10)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new Interval(2, 3));
        input.add(new Interval(5, 7));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insert(input, new Interval(1, 4)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
    */
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals.size() < 2)
            return intervals;
        if (newInterval == null)
            return intervals;

        List<Interval> mergedIntervals = new ArrayList<>();
        Iterator<Interval> intervalItr = intervals.iterator();
        Interval interval = null;

        while (intervalItr.hasNext()) {
            interval = intervalItr.next();
            if (newInterval.start > interval.end) {
                mergedIntervals.add(new Interval(interval.start, interval.end));
            } else
                break;
        }

        if (interval != null && intervalItr.hasNext()) {
            int start = Math.min(interval.start, newInterval.start);
            int end = Math.max(interval.end, newInterval.end);

            while (intervalItr.hasNext()) {
                interval = intervalItr.next();
                if (interval.start <= end)
                    end = Math.max(end, interval.end);
                else {
                    mergedIntervals.add(new Interval(start, end));
                    start = interval.start;
                    end = interval.end;
                }
            }

            mergedIntervals.add(new Interval(start, end));
        }

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
