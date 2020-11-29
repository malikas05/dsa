package subsets;

import java.util.ArrayList;
import java.util.List;

/*
Problem Statement #
Given a string, find all of its permutations preserving the character sequence but changing case.

Example 1:

Input: "ad52"
Output: "ad52", "Ad52", "aD52", "AD52"
Example 2:

Input: "ab7c"
Output: "ab7c", "Ab7c", "aB7c", "AB7c", "ab7C", "Ab7C", "aB7C", "AB7C"
 */
public class LetterCaseStringPermutation {
    /*
    - Complexity Analysis:
    Time complexity: O(N * 2^N)
    Space complexity: O(N * 2^N)
     */
    public static List<String> findLetterCaseStringPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        if (str == null) {
            return permutations;
        }
        permutations.add(str);

        for (int i = 0; i < str.length(); i++) {
            int n = permutations.size();

            for (int j = 0; j < n; j++) {
                if (Character.isLetter(str.charAt(i))) {
                    char[] newStrCharArray = str.toCharArray();
                    if (Character.isLowerCase(newStrCharArray[i])) {
                        newStrCharArray[i] = Character.toUpperCase(newStrCharArray[i]);
                    } else {
                        newStrCharArray[i] = Character.toLowerCase(newStrCharArray[i]);
                    }

                    permutations.add(String.valueOf(newStrCharArray));
                }
            }
        }
        return permutations;
    }

    public static void main(String[] args) {
        List<String> result = LetterCaseStringPermutation.findLetterCaseStringPermutations("ad52");
        System.out.println(" String permutations are: " + result);

        result = LetterCaseStringPermutation.findLetterCaseStringPermutations("ab7c");
        System.out.println(" String permutations are: " + result);
    }
}
