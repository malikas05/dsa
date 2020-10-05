package leetcode_contests.biweekly.biweekly_36;

import java.util.*;

/*
1604. Alert Using Same Key-Card Three or More Times in a One Hour Period: https://leetcode.com/contest/biweekly-contest-36/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
Leetcode company workers use key-cards to unlock office doors. Each time a worker uses their key-card, the security system saves the worker's name and the time when it was used.
The system emits an alert if any worker uses the key-card three or more times in a one-hour period.
You are given a list of strings keyName and keyTime where [keyName[i], keyTime[i]] corresponds to a person's name and the time when their key-card was used in a single day.
Access times are given in the 24-hour time format "HH:MM", such as "23:51" and "09:49".
Return a list of unique worker names who received an alert for frequent keycard use. Sort the names in ascending order alphabetically.
Notice that "10:00" - "11:00" is considered to be within a one-hour period, while "23:51" - "00:10" is not considered to be within a one-hour period.

Example 1:
Input: keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"], keyTime = ["10:00","10:40","11:00","09:00","11:00","13:00","15:00"]
Output: ["daniel"]
Explanation: "daniel" used the keycard 3 times in a one-hour period ("10:00","10:40", "11:00").

Example 2:
Input: keyName = ["alice","alice","alice","bob","bob","bob","bob"], keyTime = ["12:01","12:00","18:00","21:00","21:20","21:30","23:00"]
Output: ["bob"]
Explanation: "bob" used the keycard 3 times in a one-hour period ("21:00","21:20", "21:30").

Example 3:
Input: keyName = ["john","john","john"], keyTime = ["23:58","23:59","00:01"]
Output: []

Example 4:
Input: keyName = ["leslie","leslie","leslie","clare","clare","clare","clare"], keyTime = ["13:00","13:20","14:00","18:00","18:51","19:30","19:49"]
Output: ["clare","leslie"]

Constraints:
1 <= keyName.length, keyTime.length <= 105
keyName.length == keyTime.length
keyTime are in the format "HH:MM".
[keyName[i], keyTime[i]] is unique.
1 <= keyName[i].length <= 10
keyName[i] contains only lowercase English letters.
 */
public class KeyCardAlert {
    public static void main(String[] args) {
        System.out.println(alertNames(new String[]{"daniel", "daniel", "daniel", "luis", "luis", "luis", "luis"}, new String[]{"10:00", "10:40", "11:00", "09:00", "11:00", "13:00", "15:00"}));
        System.out.println(alertNames(new String[]{"alice", "alice", "alice", "bob", "bob", "bob", "bob"}, new String[]{"12:01", "12:00", "18:00", "21:00", "21:20", "21:30", "23:00"}));
        System.out.println(alertNames(new String[]{"john", "john", "john"}, new String[]{"23:58", "23:59", "00:01"}));
        System.out.println(alertNames(new String[]{"leslie", "leslie", "leslie", "clare", "clare", "clare", "clare"}, new String[]{"13:00", "13:20", "14:00", "18:00", "18:51", "19:30", "19:49"}));
        System.out.println(alertNames(new String[]{"a", "a", "a", "a", "a", "b", "b", "b", "b", "b", "b"}, new String[]{"23:20", "11:09", "23:30", "23:02", "15:28", "22:57", "23:40", "03:43", "21:55", "20:38", "00:19"}));
        System.out.println(alertNames(new String[]{"a", "a", "a", "a", "a", "a", "a", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "c", "c", "c", "c", "c", "c", "c", "c"},
                new String[]{"06:52", "10:09", "22:19", "04:12", "22:04", "19:30", "09:39", "09:17", "16:32", "21:32", "12:21", "15:57", "21:45", "07:00", "08:28", "13:29", "11:58", "18:28", "04:40", "16:57", "11:49", "00:08", "23:34", "02:38", "16:12"}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log(N))
    Space complexity: O(N)
     */
    public static List<String> alertNames(String[] keyName, String[] keyTime) {
        if (keyName == null || keyName.length < 3 || keyTime == null || keyTime.length < 3)
            return Collections.emptyList();

        Map<String, Worker> workersToAccessTime = new HashMap<>();
        Set<String> treeSet = new TreeSet<>();

        for (int i = 0; i < keyName.length; i++) {
            String[] hourMinute = keyTime[i].split(":");
            int timeInt = Integer.parseInt(hourMinute[0]) * 60 + Integer.parseInt(hourMinute[1]);

            if (!workersToAccessTime.containsKey(keyName[i])) {
                workersToAccessTime.put(keyName[i], new Worker(1, timeInt));
            } else {
                workersToAccessTime.get(keyName[i]).accessTimes.add(timeInt);
            }
        }

        for (Map.Entry<String, Worker> entry : workersToAccessTime.entrySet()) {
            int windowStart = 0, windowEnd = 1;

            List<Integer> accessTimes = entry.getValue().accessTimes;
            Collections.sort(accessTimes);
            while (windowEnd < accessTimes.size()) {
                if (accessTimes.get(windowEnd) <= accessTimes.get(windowStart) + 60) {
                    entry.getValue().accessCount += 1;
                    windowEnd += 1;
                } else {
                    windowStart += 1;
                    entry.getValue().accessCount -= 1;
                }

                if (entry.getValue().accessCount == 3)
                    treeSet.add(entry.getKey());
            }
        }

        return new ArrayList<>(treeSet);
    }

    private static class Worker {
        private int accessCount;
        private List<Integer> accessTimes = new ArrayList<>();

        public Worker(int accessCount, int accessTime) {
            this.accessCount = accessCount;
            this.accessTimes.add(accessTime);
        }
    }
}
