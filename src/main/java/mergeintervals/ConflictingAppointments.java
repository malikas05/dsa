package mergeintervals;

import java.util.Arrays;
import java.util.Comparator;

/*
Problem Statement #
Given an array of intervals representing ‘N’ appointments, find out if a person can attend all the appointments.

Example 1:
Appointments: [[1,4], [2,5], [7,9]]
Output: false
Explanation: Since [1,4] and [2,5] overlap, a person cannot attend both of these appointments.

Example 2:
Appointments: [[6,7], [2,4], [8,12]]
Output: true
Explanation: None of the appointments overlap, therefore a person can attend all of them.

Example 3:
Appointments: [[4,5], [2,3], [3,6]]
Output: false
Explanation: Since [4,5] and [3,6] overlap, a person cannot attend both of these appointments.
 */
public class ConflictingAppointments {
    public static void main(String[] args) {
        Interval[] intervals = { new Interval(1, 4), new Interval(2, 5), new Interval(7, 9) };
        boolean result = ConflictingAppointments.canAttendAllAppointments(intervals);
        System.out.println("Can attend all appointments: " + result);

        Interval[] intervals1 = { new Interval(6, 7), new Interval(2, 4), new Interval(8, 12) };
        result = ConflictingAppointments.canAttendAllAppointments(intervals1);
        System.out.println("Can attend all appointments: " + result);

        Interval[] intervals2 = { new Interval(4, 5), new Interval(2, 3), new Interval(3, 6) };
        result = ConflictingAppointments.canAttendAllAppointments(intervals2);
        System.out.println("Can attend all appointments: " + result);
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log(N)) - for sorting
    Space complexity: O(N) - Arrays.sort() uses Timsort which needs O(N) space
    */
    public static boolean canAttendAllAppointments(Interval[] intervals) {
        if (intervals == null || intervals.length < 2)
            return true;

        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval.start));
        int i = 0;
        Interval interval = intervals[i++];

        while (i < intervals.length) {
            if (interval.end > intervals[i].start)
                return false;

            interval = intervals[i];
            i++;
        }

        return true;
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
