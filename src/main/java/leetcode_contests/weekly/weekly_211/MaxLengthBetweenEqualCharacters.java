package leetcode_contests.weekly.weekly_211;

import java.util.HashMap;
import java.util.Map;

/*
1624. Largest Substring Between Two Equal Characters - https://leetcode.com/problems/largest-substring-between-two-equal-characters/
Given a string s, return the length of the longest substring between two equal characters, excluding the two characters. If there is no such substring return -1.
A substring is a contiguous sequence of characters within a string.

Example 1:
Input: s = "aa"
Output: 0
Explanation: The optimal substring here is an empty substring between the two 'a's.

Example 2:
Input: s = "abca"
Output: 2
Explanation: The optimal substring here is "bc".

Example 3:
Input: s = "cbzxy"
Output: -1
Explanation: There are no characters that appear twice in s.

Example 4:
Input: s = "cabbac"
Output: 4
Explanation: The optimal substring here is "abba". Other non-optimal substrings include "bb" and "".

Constraints:
1 <= s.length <= 300
s contains only lowercase English letters.
 */
public class MaxLengthBetweenEqualCharacters {
    public static void main(String[] args) {
        System.out.println(maxLengthBetweenEqualCharacters("aa"));
        System.out.println(maxLengthBetweenEqualCharacters("abca"));
        System.out.println(maxLengthBetweenEqualCharacters("cbzxy"));
        System.out.println(maxLengthBetweenEqualCharacters("cabbac"));
        System.out.println(maxLengthBetweenEqualCharacters("ojdncpvyneq"));
        System.out.println(maxLengthBetweenEqualCharacters("mgntdygtxrvxjnwksqhxuxtrv"));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    public static int maxLengthBetweenEqualCharacters(String s) {
        if (s == null || s.isEmpty())
            return -1;

        Map<Character, CharacterPos> map = new HashMap<>();
        int maxSubLength = -1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, new CharacterPos(c, i));
            } else {
                map.get(c).end = i;
                maxSubLength = Math.max(map.get(c).end - map.get(c).start - 1, maxSubLength);
            }
        }

        return maxSubLength;
    }

    private static class CharacterPos {
        private char c;
        private int start;
        private int end = -1;

        public CharacterPos(char c, int start) {
            this.c = c;
            this.start = start;
        }
    }
}
