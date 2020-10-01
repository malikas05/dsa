package strings;

public class PalindromeSubStrings {
    public static void main(String[] args) {
        String str = "aabbbaa";
        System.out.println(findAllPalindromeSubstrings(str));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N^2)
    Space complexity: O(1)
    */
    public static int findAllPalindromeSubstrings(String input) {
        if (input == null || input.length() < 2)
            return -1;

        int result = 0;
        int subPointer = 2;
        for (int i = 0; i < input.length() - 1; i++) {
            subPointer += i;
            while (subPointer <= input.length()) {
                if (isPalindrome(input.substring(i, subPointer)))
                    result += 1;

                subPointer++;
            }

            subPointer = 2;
        }

        return result;
    }

    private static boolean isPalindrome(String substring) {
        int start = 0;
        int end = substring.length() - 1;

        while (start < end) {
            if (substring.charAt(start) != substring.charAt(end)) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }
}
