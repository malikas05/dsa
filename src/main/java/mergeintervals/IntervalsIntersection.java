package mergeintervals;

import java.util.ArrayList;
import java.util.List;

/*
Problem Statement #
Given two lists of intervals, find the intersection of these two lists. Each list consists of disjoint intervals sorted on their start time.

Example 1:
Input: arr1=[[1, 3], [5, 6], [7, 9]], arr2=[[2, 3], [5, 7]]
Output: [2, 3], [5, 6], [7, 7]
Explanation: The output list contains the common intervals between the two lists.

Example 2:
Input: arr1=[[1, 3], [5, 7], [9, 12]], arr2=[[5, 10]]
Output: [5, 7], [9, 10]
Explanation: The output list contains the common intervals between the two lists.
 */
public class IntervalsIntersection {
    public static void main(String[] args) {
        Interval[] input1 = new Interval[] { new Interval(1, 3), new Interval(5, 6), new Interval(7, 9) };
        Interval[] input2 = new Interval[] { new Interval(2, 3), new Interval(5, 7) };
        Interval[] result = IntervalsIntersection.merge(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input1 = new Interval[] { new Interval(1, 3), new Interval(5, 7), new Interval(9, 12) };
        input2 = new Interval[] { new Interval(5, 10) };
        result = IntervalsIntersection.merge(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N + M)
    Space complexity: O(1)
    */
    public static Interval[] merge(Interval[] arr1, Interval[] arr2) {
        if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0)
            return null;

        List<Interval> intervalsIntersection = new ArrayList<>();
        int i = 0, j = 0;
        Interval first = null;
        Interval second = null;

        while (i < arr1.length && j < arr2.length) {
            first = arr1[i];
            second = arr2[j];

            if (first.end < second.start) {
                i++;
                continue;
            } else if (second.end < first.start) {
                j++;
                continue;
            }

            intervalsIntersection.add(new Interval(Math.max(first.start, second.start), Math.min(first.end, second.end)));
            i++;
        }

        return intervalsIntersection.toArray(new Interval[intervalsIntersection.size()]);
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
