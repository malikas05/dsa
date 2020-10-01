package array.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MaxFruitCountOf2Types {
    public static void main(String[] args) {
        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[]{'A', 'B', 'C', 'A', 'C'}));
        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[]{'A', 'B', 'C', 'B', 'B', 'C'}));
    }

    /*
    Example 1:
    Input: Fruit=['A', 'B', 'C', 'A', 'C']
    Output: 3
    Explanation: We can put 2 'C' in one basket and one 'A' in the other from the subarray ['C', 'A', 'C']

    Example 2:
    Input: Fruit=['A', 'B', 'C', 'B', 'B', 'C']
    Output: 5
    Explanation: We can put 3 'B' in one basket and two 'C' in the other basket.
    This can be done if we start with the second letter: ['B', 'C', 'B', 'B', 'C']
     */

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
    */
    public static int findLength(char[] arr) {
        if (arr == null || arr.length == 0)
            return -1;

        Map<Character, Integer> map = new HashMap<>();
        int windowStart = 0, maxSubFruits = 0;

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            char c = arr[windowEnd];
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.size() > 2) {
                char leftMostChar = arr[windowStart++];
                if (map.get(leftMostChar) == 1)
                    map.remove(leftMostChar);
                else
                    map.put(leftMostChar, map.get(leftMostChar) - 1);
            }

            maxSubFruits = Math.max(maxSubFruits, windowEnd - windowStart + 1);
        }

        return maxSubFruits;
    }
}
