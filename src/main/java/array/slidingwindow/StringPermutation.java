package array.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Permutation in a String (hard) #
Given a string and a pattern, find out if the string contains any permutation of the pattern.
Permutation is defined as the re-arranging of the characters of the string. For example, “abc” has the following six permutations:
abc
acb
bac
bca
cab
cba
If a string has ‘n’ distinct characters it will have n!n! permutations.

Example 1:
Input: String="oidbcaf", Pattern="abc"
Output: true
Explanation: The string contains "bca" which is a permutation of the given pattern.

Example 2:
Input: String="odicf", Pattern="dc"
Output: false
Explanation: No permutation of the pattern is present in the given string as a substring.

Example 3:
Input: String="bcdxabcdy", Pattern="bcdyabcdx"
Output: true
Explanation: Both the string and the pattern are a permutation of each other.

Example 4:
Input: String="aaacb", Pattern="abc"
Output: true
Explanation: The string contains "acb" which is a permutation of the given pattern.
 */
public class StringPermutation {
    public static void main(String[] args) {
        System.out.println(findPermutation("oidbcaf", "abc"));
        System.out.println(findPermutation("odicf", "dc"));
        System.out.println(findPermutation("bcdxabcdy", "bcdyabcdx"));
        System.out.println(findPermutation("aaacb", "abc"));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N + K) where N is the number of characters in str and K is the number of characters in pattern
    Space complexity: O(K)
     */
    public static boolean findPermutation(String str, String pattern) {
        if (str == null || str.isEmpty() || pattern == null || pattern.isEmpty())
            return false;

        int windowStart = 0, matched = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();

        for (char c : pattern.toCharArray())
            charFrequencyMap.put(c, charFrequencyMap.getOrDefault(c, 0) + 1);

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if (charFrequencyMap.get(rightChar) == 0)
                    matched++;
            }

            if (matched == charFrequencyMap.size())
                return true;

            if (windowEnd >= pattern.length() - 1) {
                char leftChar = str.charAt(windowStart++);
                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap.get(leftChar) == 0)
                        matched--;
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
            }
        }

        return false;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N!)
    Space complexity: O(N!)
     */
    public static boolean findPermutation2(String str, String pattern) {
        if (str == null || str.isEmpty() || pattern == null || pattern.isEmpty())
            return false;

        Set<String> permutations = retrievePermutationsFromPattern(pattern);
        int K = pattern.length();
        int windowStart = 0;
        String temp = "";

        for (int windowEnd = 0; windowEnd <= str.length(); windowEnd++) {
            temp = str.substring(windowStart, windowEnd);
            if (permutations.contains(temp))
                return true;

            if (windowEnd - windowStart >= K) {
                windowStart++;
            }
        }

        return false;
    }

    private static Set<String> retrievePermutationsFromPattern(String pattern) {
        Set<String> permutations = new HashSet<>();
        permuteStr(pattern.toCharArray(), 0, permutations);
        return permutations;
    }

    private static void permuteStr(char[] pattern, int currentIndex, Set<String> permutations) {
        if (currentIndex == pattern.length) {
            permutations.add(String.valueOf(pattern));
        }

        for (int i = currentIndex; i < pattern.length; i++) {
            swab(pattern, currentIndex, i);
            permuteStr(pattern, currentIndex + 1, permutations);
            swab(pattern, currentIndex, i);
        }
    }

    private static void swab(char[] pattern, int i, int j) {
        char temp = pattern[i];
        pattern[i] = pattern[j];
        pattern[j] = temp;
    }
}
