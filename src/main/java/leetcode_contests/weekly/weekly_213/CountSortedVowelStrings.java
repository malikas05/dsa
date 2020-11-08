package leetcode_contests.weekly.weekly_213;

/*
1641. Count Sorted Vowel Strings - https://leetcode.com/problems/count-sorted-vowel-strings/
Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.

Example 1:
Input: n = 1
Output: 5
Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].

Example 2:
Input: n = 2
Output: 15
Explanation: The 15 sorted strings that consist of vowels only are
["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.

Example 3:
Input: n = 33
Output: 66045

Constraints:
1 <= n <= 50
 */
public class CountSortedVowelStrings {
    public static void main(String[] args) {
        System.out.println(countVowelStrings(33));
    }

    public static int countVowelStrings(int n) {
        char[] chars = {'a', 'e', 'i', 'o', 'u'};
        int[] ans = new int[1];
        backTracking(chars, 0, n, ans);

        return ans[0];
    }

    private static void backTracking(char[] chars, int start, int n, int[] ans) {
        if (n == 0) {
            ans[0] += 1;
            return;
        }

        for (int i = start; i < chars.length; i++) {
            backTracking(chars, i, n - 1, ans);
        }
    }
}
