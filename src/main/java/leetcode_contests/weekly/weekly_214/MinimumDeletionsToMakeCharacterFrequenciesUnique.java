package leetcode_contests.weekly.weekly_214;

import java.util.HashSet;
import java.util.Set;

/*
1647. Minimum Deletions to Make Character Frequencies Unique - https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
A string s is called good if there are no two different characters in s that have the same frequency.
Given a string s, return the minimum number of characters you need to delete to make s good.
The frequency of a character in a string is the number of times it appears in the string.
For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.

Example 1:
Input: s = "aab"
Output: 0
Explanation: s is already good.

Example 2:
Input: s = "aaabbbcc"
Output: 2
Explanation: You can delete two 'b's resulting in the good string "aaabcc".
Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".

Example 3:
Input: s = "ceabaacb"
Output: 2
Explanation: You can delete both 'c's resulting in the good string "eabaab".
Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).

Constraints:
1 <= s.length <= 105
s contains only lowercase English letters.
 */
public class MinimumDeletionsToMakeCharacterFrequenciesUnique {
    public static void main(String[] args) {
        System.out.println(minDeletions("aab"));
        System.out.println(minDeletions("aaabbbcc"));
        System.out.println(minDeletions("ceabaacb"));
        System.out.println(minDeletions("bbcebab"));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N ^ 2) - in the worst case when we have all chars of the same frequency
    Space complexity: O(K) - number of unique frequencies
     */
    public static int minDeletions(String s) {
        int[] charFreqArr = new int[26];
        for (char c : s.toCharArray()) {
            charFreqArr[c - 97] = charFreqArr[c - 97] + 1;
        }

        int countDeleteChars = 0;
        Set<Integer> existingFreq = new HashSet<>();
        for (int charFreq : charFreqArr) {
            if (charFreq > 0) {
                if (existingFreq.contains(charFreq)) {
                    while (existingFreq.contains(charFreq) & charFreq != 0) {
                        countDeleteChars++;
                        charFreq -= 1;
                    }
                    existingFreq.add(charFreq);
                } else {
                    existingFreq.add(charFreq);
                }
            }
        }

        return countDeleteChars;
    }
}
