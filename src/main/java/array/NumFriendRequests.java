package array;

import java.util.Arrays;

/*
825. Friends Of Appropriate Ages - https://leetcode.com/problems/friends-of-appropriate-ages/
Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.
Person A will NOT friend request person B (B != A) if any of the following conditions are true:
age[B] <= 0.5 * age[A] + 7
age[B] > age[A]
age[B] > 100 && age[A] < 100
Otherwise, A will friend request B.
Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
How many total friend requests are made?

Example 1:
Input: [16,16]
Output: 2
Explanation: 2 people friend request each other.

Example 2:
Input: [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.

Example 3:
Input: [20,30,100,110,120]
Output: 3
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.

Notes:
1 <= ages.length <= 20000.
1 <= ages[i] <= 120.
 */
public class NumFriendRequests {
    public static void main(String[] args) {
        System.out.println(numFriendRequests(new int[]{16, 16}));
        System.out.println(numFriendRequests(new int[]{16, 17, 18}));
        System.out.println(numFriendRequests(new int[]{20, 30, 100, 110, 120}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(K) where K is the max age
     */
    public static int numFriendRequests(int[] ages) {
        if (ages == null || ages.length < 2)
            return 0;

        int[] countAge = new int[121];
        for (int age : ages)
            countAge[age] += 1;

        for (int i = 1; i <= 120; i++) {
            countAge[i] += countAge[i - 1];
        }

        int totalRequests = 0;
        for (int age : ages) {
            if (age / 2 + 7 < age)
                totalRequests += countAge[age] - countAge[age / 2 + 7] - 1;
        }

        return totalRequests;
    }

    /*
    - Complexity Analysis(Highly Inefficient):
    Time complexity: O(N^2)
    Space complexity: O(N) - for sorting
     */
    public static int numFriendRequests2(int[] ages) {
        if (ages == null || ages.length < 2)
            return 0;

        Arrays.sort(ages);
        int totalRequest = 0;

        for (int i = ages.length - 1; i >= 0; i--) {
            int j = i - 1;
            while (j >= 0 && ages[j] > ages[i] * 0.5 + 7) {
                totalRequest += ages[j] == ages[i] ? 2 : 1;
                j--;
            }
        }

        return totalRequest;
    }
}
