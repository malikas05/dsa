package mergeintervals;

import heap.Heap;

import java.util.*;

/*
Minimum Meeting Rooms (hard) #
Given a list of intervals representing the start and end time of ‘N’ meetings, find the minimum number of rooms required to hold all the meetings.

Example 1:
Meetings: [[1,4], [2,5], [7,9]]
Output: 2
Explanation: Since [1,4] and [2,5] overlap, we need two rooms to hold these two meetings. [7,9] can
occur in any of the two rooms later.

Example 2:
Meetings: [[6,7], [2,4], [8,12]]
Output: 1
Explanation: None of the meetings overlap, therefore we only need one room to hold all meetings.

Example 3:
Meetings: [[1,4], [2,3], [3,6]]
Output:2
Explanation: Since [1,4] overlaps with the other two meetings [2,3] and [3,6], we need two rooms to
hold all the meetings.

Example 4:
Meetings: [[4,5], [2,3], [2,4], [3,5]] => [[2,3], [2,4], [3,5], [4,5]]
Output: 2
Explanation: We will need one room for [2,3] and [3,5], and another room for [2,4] and [4,5].
 */
public class MinimumMeetingRooms {
    public static void main(String[] args) {
        List<Meeting> input = new ArrayList<>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        int result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 5));
                add(new Meeting(7, 9));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<>() {
            {
                add(new Meeting(6, 7));
                add(new Meeting(2, 4));
                add(new Meeting(8, 12));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 3));
                add(new Meeting(3, 6));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log(N)) - O(N log(N)) for sorting, O(N log(N)) for polling/offering to priority queue
    Space complexity: O(N) - O(N) for sorting, also for storing in priority queue
    */
    public static int findMinimumMeetingRooms(List<Meeting> meetings) {
        if (meetings == null || meetings.size() < 2)
            return 1;

        Collections.sort(meetings, Comparator.comparingInt(meeting -> meeting.start));
        int minRooms = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (Meeting meeting : meetings) {
            if (!minHeap.isEmpty() && minHeap.peek() <= meeting.start) {
                minHeap.poll();
            }
            minHeap.add(meeting.end);

            minRooms = Math.max(minRooms, minHeap.size());
        }

        return minHeap.size();
    }

    private static class Meeting {
        private int start;
        private int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
