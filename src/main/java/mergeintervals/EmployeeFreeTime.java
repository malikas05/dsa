package mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Employee Free Time (hard) #
For ‘K’ employees, we are given a list of intervals representing the working hours of each employee.
Our goal is to find out if there is a free interval that is common to all employees.
You can assume that each list of employee working hours is sorted on the start time.

Example 1:
Input: Employee Working Hours=[[[1,3], [5,6]], [[2,3], [6,8]]]
Output: [3,5]
Explanation: Both the employess are free between [3,5].

Example 2:
Input: Employee Working Hours=[[[1,3], [9,12]], [[2,4]], [[6,8]]]
Output: [4,6], [8,9]
Explanation: All employess are free between [4,6] and [8,9].

Example 3:
Input: Employee Working Hours=[[[1,3]], [[2,4]], [[3,5], [7,9]]]
Output: [5,7]
Explanation: All employess are free between [5,7].
 */
public class EmployeeFreeTime {
    public static void main(String[] args) {
        List<List<Interval>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(5, 6))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(2, 3), new Interval(6, 8))));
        List<Interval> result = EmployeeFreeTime.findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(9, 12))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(6, 8))));
        result = EmployeeFreeTime.findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new Interval(1, 3))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(3, 5), new Interval(7, 9))));
        result = EmployeeFreeTime.findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(5, 6), new Interval(10, 12), new Interval(14,16))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(2, 3), new Interval(6,8))));
        result = EmployeeFreeTime.findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
    }

    public static List<Interval> findEmployeeFreeTime(List<List<Interval>> schedule) {
        if (schedule == null || schedule.size() == 0)
            return null;

        List<Interval> result = new ArrayList<>();
        List<List<Interval>> freeSchedule = new ArrayList<>();
        int maxFreeScheduleSize = 0;
        int startHour = Integer.MAX_VALUE, finishHour = 0;

        for (List<Interval> intervals : schedule) {
            for (Interval interval : intervals) {
                startHour = Math.min(startHour, interval.end);
                finishHour = Math.max(finishHour, interval.start);
            }
        }

        for (int k = 0; k < schedule.size(); k++) {
            List<Interval> singleSchedule = schedule.get(k);
            List<Interval> freeSingleSchedule = new ArrayList<>();

            if (singleSchedule.get(0).start > startHour)
                freeSingleSchedule.add(new Interval(startHour, singleSchedule.get(0).start));
            for (int i = 1; i < singleSchedule.size(); i++) {
                if (singleSchedule.get(i).start > singleSchedule.get(i - 1).end) {
                    freeSingleSchedule.add(new Interval(singleSchedule.get(i - 1).end, singleSchedule.get(i).start));
                }
            }

            if (singleSchedule.get(singleSchedule.size() - 1).end < finishHour)
                freeSingleSchedule.add(new Interval(singleSchedule.get(singleSchedule.size() - 1).end, finishHour));

            freeSchedule.add(freeSingleSchedule);
            maxFreeScheduleSize = Math.max(maxFreeScheduleSize, freeSingleSchedule.size());
        }

        int i = 0;
        while (i < maxFreeScheduleSize) {
            Interval freeInterval = new Interval(0, 24);
            for (List<Interval> intervals : freeSchedule) {
                if (intervals.size() > i) {
                    freeInterval.start = Math.max(intervals.get(i).start, freeInterval.start);
                    freeInterval.end = Math.min(intervals.get(i).end, freeInterval.end);
                }
            }

            result.add(freeInterval);
            i++;
        }

        return result;
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
