package strings;

public class RemoveSpaces {
    public static void main(String[] args) {
        char[] str = "All greek\t\tto me.".toCharArray();
        removeWhiteSpaces(str);
        System.out.println(String.valueOf(str));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    static void removeWhiteSpaces(char[] s) {
        if (s == null || s.length == 0 || s[0] == '\0') {
            return;
        }

        int writePointer = 0;

        for (int i = 0; i < s.length; i++) {
            if (s[i] != ' ' && s[i] != '\t') {
                s[writePointer++] = s[i];
            }
        }

        while (writePointer < s.length) {
            s[writePointer++] = ' ';
        }
    }
}
