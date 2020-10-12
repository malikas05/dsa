package slidingwindow;

import java.util.HashMap;
import java.util.Map;
/*
Problem Statement #
Given an array of characters where each character represents a fruit tree,
you are given two baskets and your goal is to put maximum number of fruits in each basket.
The only restriction is that each basket can have only one type of fruit.
You can start with any tree, but once you have started you can’t skip a tree.
You will pick one fruit from each tree until you cannot, i.e., you will stop when you have to pick from a third fruit type.
Write a function to return the maximum number of fruits in both the baskets.

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
public class MaxFruitCountOf2Types {
    public static void main(String[] args) {
        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[]{'A', 'B', 'C', 'A', 'C'}));
        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[]{'A', 'B', 'C', 'B', 'B', 'C'}));
    }

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
