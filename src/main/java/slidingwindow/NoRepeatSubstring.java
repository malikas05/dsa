package slidingwindow;

import java.util.Arrays;

public class NoRepeatSubstring {
    public static void main(String[] args) {
        System.out.println(findLength("aabccbb"));
    }

    /*
    Example 1:
    Input: String="aabccbb"
    Output: 3
    Explanation: The longest substring without any repeating characters is "abc".

    Example 2:
    Input: String="abbbb"
    Output: 2
    Explanation: The longest substring without any repeating characters is "ab".

    Example 3:
    Input: String="abccde"
    Output: 3
    Explanation: Longest substrings without any repeating characters are "abc" & "cde".
     */

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(K), K - is the number of distinct characters.
    We can use char array with 26 English characters if there is a constraint given. This way, it will be O(1) space.
    */
    public static int findLength(String str) {
        //aabccbb
        if (str == null || str.isEmpty())
            return -1;

        int[] chars = new int[26];
        Arrays.fill(chars, -1);
        int maxLength = 0, windowStart = 0;

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            int charIndex = str.charAt(windowEnd) - 97;

            if (chars[charIndex] != -1) {
                windowStart = Math.max(windowStart, chars[charIndex] + 1);
            }

            chars[charIndex] = windowEnd;
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }
}
