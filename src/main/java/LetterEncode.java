import java.util.*;

public class LetterEncode {

    public static void main(String[] args) {
        System.out.println(encodeString("aabccc"));
        System.out.println(encodeString("abcd"));
        System.out.println(encodeString("aaaa"));
    }

    /*
    Testing:
    input:    aabccc
    prevChar: 0 a a b c c
    c:        a a b c c c
    count:    1 2 1 1 2 3
    2a1b3c

    input:    abcd
    prevChar: 0 a b c
    c:        a b c d
    count:    1 1 1 1
    1a2b1c1d

    input:    aaaa
    prevChar: 0 a a a
    c:        a a a a
    count:    1 2 3 4
    4a
     */

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static String encodeString(String input) {
        if (Objects.isNull(input) || input.length() == 0)
            return "";

        char[] characters = input.toCharArray();
        char prevChar = 0;
        int count = 1;
        StringBuilder sb = new StringBuilder();

        for (char c : characters) {
            if (c == prevChar) {
                count++;
            } else {
                if (prevChar != 0)
                    sb.append(count).append(prevChar);
                prevChar = c;
                count = 1;
            }
        }

        return sb.append(count).append(prevChar).toString();
    }
}
