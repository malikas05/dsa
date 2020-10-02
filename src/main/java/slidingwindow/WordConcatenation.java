package slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Words Concatenation (hard) #
Given a string and a list of words, find all the starting indices of substrings in the given string that are a concatenation
of all the given words exactly once without any overlapping of words. It is given that all words are of the same length.

Example 1:
Input: String="catfoxcat", Words=["cat", "fox"]
Output: [0, 3]
Explanation: The two substring containing both the words are "catfox" & "foxcat".

Example 2:
Input: String="catcatfoxfox", Words=["cat", "fox"]
Output: [3]
Explanation: The only substring containing both the words is "catfox".
 */
public class WordConcatenation {
    public static void main(String[] args) {
        System.out.println(WordConcatenation.findWordConcatenation("catfoxcat", new String[]{"cat", "fox"}));
        System.out.println(WordConcatenation.findWordConcatenation("catcatfoxfox", new String[]{"cat", "fox"}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N + K) where N is the number of characters in str and K is the number of strings in words
    Space complexity: O(K)
     */
    public static List<Integer> findWordConcatenation(String str, String[] words) {
        List<Integer> resultIndices = new ArrayList<>();
        if (str == null || str.isEmpty() || words == null || words.length == 0)
            return resultIndices;

        int windowStart = 0, matched = 0, matchedStartingIndex = 0, singleWordLength = words[0].length();
        Map<String, Integer> patternsMap = new HashMap<>();
        for (String word : words)
            patternsMap.put(word, patternsMap.getOrDefault(word, 0) + 1);

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            if (windowEnd - windowStart + 1 != singleWordLength) {
                continue;
            }
            String rightWord = str.substring(windowStart, windowEnd + 1);
            if (patternsMap.containsKey(rightWord)) {
                patternsMap.put(rightWord, patternsMap.get(rightWord) - 1);
                if (patternsMap.get(rightWord) == 0)
                    matched++;
            }

            if (matched == patternsMap.size()) {
                resultIndices.add(matchedStartingIndex);
                String leftWord = str.substring(matchedStartingIndex, windowStart);
                patternsMap.put(leftWord, patternsMap.get(leftWord) + 1);
                matchedStartingIndex = windowStart;
                matched--;
            }

            if (patternsMap.get(rightWord) < 0) {
                matchedStartingIndex = windowStart;
            }
            windowStart = windowEnd + 1;
        }

        return resultIndices;
    }
}
