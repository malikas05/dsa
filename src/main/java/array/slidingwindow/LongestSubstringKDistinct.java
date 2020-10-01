package array.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringKDistinct {
    public static void main(String[] args) {
        System.out.println(findLength("araaci", 1));
    }

    /*
    Example 1:
    Input: String="araaci", K=2
    Output: 4
    Explanation: The longest substring with no more than '2' distinct characters is "araa".

    Example 2:
    Input: String="araaci", K=1
    Output: 2
    Explanation: The longest substring with no more than '1' distinct characters is "aa".

    Example 3:
    Input: String="cbbebi", K=3
    Output: 5
    Explanation: The longest substrings with no more than '3' distinct characters are "cbbeb" & "bbebi".
     */

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
    */
    public static int findLength(String str, int k) {
        if (str == null || str.isEmpty() || str.length() < k)
            return -1;

        Map<Character, Integer> map = new HashMap<>();
        int windowStart = 0, longestSubstr = 0;
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char c = str.charAt(windowEnd);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.size() > k) {
                char windowStartChar = str.charAt(windowStart++);
                if (map.get(windowStartChar) == 1)
                    map.remove(windowStartChar);
                else
                    map.put(windowStartChar, map.get(windowStartChar) - 1);
            }

            longestSubstr = Math.max(longestSubstr, windowEnd - windowStart + 1);
        }

        return longestSubstr;
    }

}
