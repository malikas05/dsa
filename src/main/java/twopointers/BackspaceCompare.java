package twopointers;

/*
Comparing Strings containing Backspaces (medium) #
Given two strings containing backspaces (identified by the character ‘#’), check if the two strings are equal.

Example 1:
Input: str1="xy#z", str2="xzz#"
Output: true
Explanation: After applying backspaces the strings become "xz" and "xz" respectively.

Example 2:
Input: str1="xy#z", str2="xyz#"
Output: false
Explanation: After applying backspaces the strings become "xz" and "xy" respectively.

Example 3:
Input: str1="xp#", str2="xyz##"
Output: true
Explanation: After applying backspaces the strings become "x" and "x" respectively.
In "xyz##", the first '#' removes the character 'z' and the second '#' removes the character 'y'.

Example 4:
Input: str1="xywrrmp", str2="xywrrmu#p"
Output: true
Explanation: After applying backspaces the strings become "xywrrmp" and "xywrrmp" respectively.
 */
public class BackspaceCompare {
    public static void main(String[] args) {
        System.out.println(BackspaceCompare.compare("xy#z", "xzz#"));
        System.out.println(BackspaceCompare.compare("xy#z", "xyz#"));
        System.out.println(BackspaceCompare.compare("xp#", "xyz##"));
        System.out.println(BackspaceCompare.compare("xywrrmp", "xywrrmu#p"));
    }

    private static final char backspace = '#';

    /*
    - Complexity Analysis:
    Time complexity: O(N + M) where N is the number of chars in str1 and M is the number of chars in str2
    Space complexity: O(1)
     */
    public static boolean compare(String str1, String str2) {
        if (str1 == null || str2 == null)
            return false;

        int index1 = str1.length() - 1, index2 = str2.length() - 1;
        while (index1 >= 0 && index2 >= 0) {
            int i1 = getNextValidCharIndex(str1, index1);
            int i2 = getNextValidCharIndex(str2, index2);

            if (i1 < 0 && i2 < 0)
                return true;

            if (i1 < 0 || i2 < 0)
                return false;

            if (str1.charAt(i1) != str2.charAt(i2))
                return false;

            index1 = i1 - 1;
            index2 = i2 - 1;
        }

        return true;
    }

    private static int getNextValidCharIndex(String str, int index) {
        int countBackspace = 0;

        while (index >= 0) {
            char c = str.charAt(index);

            if (c == backspace) {
                countBackspace++;
            } else if (countBackspace > 0)
                countBackspace--;
            else break;

            index--;
        }

        return index;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N + M) where N is the number of chars in str1 and M is the number of chars in str2
    Space complexity: O(1)
     */
    public static boolean compare2(String str1, String str2) {
        if (str1 == null || str2 == null)
            return false;

        if (str1.isEmpty() && str2.isEmpty())
            return true;

        str1 = parseString(str1);
        str2 = parseString(str2);

        return str1.equals(str2);
    }

    private static String parseString(String str) {
        StringBuilder resStr = new StringBuilder();
        int countConsBackSpace = 0;

        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);

            if (c == backspace) {
                countConsBackSpace += 1;
                continue;
            }

            while (countConsBackSpace > 0) {
                i--;
                countConsBackSpace--;
            }

            resStr.insert(0, str.charAt(i));
        }

        return resStr.toString();
    }
}
