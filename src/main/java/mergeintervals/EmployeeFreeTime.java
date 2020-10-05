package mergeintervals;

import java.util.*;

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

Example 4:
Input: Employee Working Hours=[[[1,3], [5,6], [10,12], [14,16]], [[2,3],[6,8]]]
Output: [5,7]
Explanation: All employess are free between [5,7].
 */
public class EmployeeFreeTime {
    public static void main(String[] args) {
        List<List<Interval>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(5, 6))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(2, 3), new Interval(6, 8))));
        List<Interval> result = EmployeeFreeTime.findEmployeeFreeTime3(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(9, 12))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(6, 8))));
        result = EmployeeFreeTime.findEmployeeFreeTime3(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new Interval(1, 3))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(3, 5), new Interval(7, 9))));
        result = EmployeeFreeTime.findEmployeeFreeTime3(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(5, 6), new Interval(10, 12), new Interval(14,16))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(2, 3), new Interval(6,8))));
        result = EmployeeFreeTime.findEmployeeFreeTime3(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
    */
    public static List<Interval> findEmployeeFreeTime(List<List<Interval>> schedule) {
        if (schedule == null || schedule.size() == 0)
            return null;

        List<Interval> result = new ArrayList<>();
        List<List<Interval>> freeScheduleForAllEmployees = new ArrayList<>();
        int maxFreeScheduleSize = 0;
        int startHour = Integer.MAX_VALUE, finishHour = 0;

        for (List<Interval> intervals : schedule) {
            for (Interval interval : intervals) {
                startHour = Math.min(startHour, interval.end);
                finishHour = Math.max(finishHour, interval.start);
            }
        }

        for (int k = 0; k < schedule.size(); k++) {
            List<Interval> employeeSchedule = schedule.get(k);
            List<Interval> employeeFreeSchedule = new ArrayList<>();

            if (employeeSchedule.get(0).start > startHour)
                employeeFreeSchedule.add(new Interval(startHour, employeeSchedule.get(0).start));
            for (int i = 1; i < employeeSchedule.size(); i++) {
                if (employeeSchedule.get(i).start > employeeSchedule.get(i - 1).end) {
                    employeeFreeSchedule.add(new Interval(employeeSchedule.get(i - 1).end, employeeSchedule.get(i).start));
                }
            }

            if (employeeSchedule.get(employeeSchedule.size() - 1).end < finishHour)
                employeeFreeSchedule.add(new Interval(employeeSchedule.get(employeeSchedule.size() - 1).end, finishHour));

            freeScheduleForAllEmployees.add(employeeFreeSchedule);
            maxFreeScheduleSize = Math.max(maxFreeScheduleSize, employeeFreeSchedule.size());
        }

        int i = 0;
        while (i < maxFreeScheduleSize) {
            Interval freeInterval = new Interval(0, 24);
            for (List<Interval> intervals : freeScheduleForAllEmployees) {
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

    /*
    - Complexity Analysis:
    Time complexity: O(N log(N)) - for sorting
    Space complexity: O(N) - for sorting
    */
    public static List<Interval> findEmployeeFreeTime2(List<List<Interval>> schedule) {
        if (schedule == null || schedule.size() == 0)
            return null;

        List<Interval> allSchedules = new ArrayList<>();
        for (List<Interval> employeeSchedule : schedule) {
            allSchedules.addAll(employeeSchedule);
        }

        Collections.sort(allSchedules, Comparator.comparingInt(Interval::getStart));
        List<Interval> freeSchedules = new ArrayList<>();

        Iterator<Interval> intervalIterator = allSchedules.iterator();
        Interval prevInterval = intervalIterator.next();
        while (intervalIterator.hasNext()) {
            Interval currentInterval = intervalIterator.next();
            if (prevInterval.end < currentInterval.start)
                freeSchedules.add(new Interval(prevInterval.end, currentInterval.start));

            prevInterval = currentInterval;
        }

        return freeSchedules;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log(K)) where N - number of intervals and K - number of employees. We store no more than K elements in the heap at any given time.
    Space complexity: O(K) - for minHeap
    */
    public static List<Interval> findEmployeeFreeTime3(List<List<Interval>> schedule) {
        if (schedule == null || schedule.size() == 0)
            return null;

        List<Interval> result = new ArrayList<>();
        PriorityQueue<Employee> minHeap = new PriorityQueue<>(Comparator.comparingInt(employee -> employee.interval.start));
        for (int i = 0; i < schedule.size(); i++) {
            minHeap.offer(new Employee(schedule.get(i).get(0), i, 0));
        }

        Interval previousInterval = minHeap.peek().interval;
        while (!minHeap.isEmpty()) {
            Employee topElem = minHeap.poll();
            if (previousInterval.end < topElem.interval.start) {
                result.add(new Interval(previousInterval.end, topElem.interval.start));
                previousInterval = topElem.interval;
            } else if (previousInterval.end < topElem.interval.end) {
                previousInterval = topElem.interval;
            }

            if (schedule.get(topElem.employeeId).size() > topElem.intervalIndex + 1) {
                minHeap.offer(new Employee(schedule.get(topElem.employeeId).get(topElem.intervalIndex + 1), topElem.employeeId, topElem.intervalIndex + 1));
            }
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

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }

    private static class Employee {
        private Interval interval;
        private int employeeId;
        private int intervalIndex;

        public Employee(Interval interval, int employeeId, int intervalIndex) {
            this.interval = interval;
            this.employeeId = employeeId;
            this.intervalIndex = intervalIndex;
        }
    }
}
