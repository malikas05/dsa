package strings;

public class RegxMatch {
    public static void main(String[] args) {
        String text = "aabbbbbcdda", pattern = "a*b*c*da";
        System.out.println(regxMatch(text, pattern));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
    */
    static boolean regxMatch(String text, String pattern) {
        if (text == null || text.isEmpty() || pattern == null || pattern.isEmpty()) {
            return false;
        } else if (text.equals(pattern)) {
            return true;
        }

        char cp, curChar, prevCharPattern = 0;

        while (!pattern.isEmpty() && !text.isEmpty()) {
            cp = pattern.charAt(0);
            curChar = text.charAt(0);

            if (cp == '.') {
                pattern = pattern.substring(1);
                text = text.substring(1);
                prevCharPattern = curChar;
            } else if (cp == '*') {
                if (prevCharPattern != 0) {
                    if (prevCharPattern == curChar) {
                        text = text.substring(1);
                    } else {
                        pattern = pattern.substring(1);
                    }
                } else {
                    pattern = pattern.substring(1);
                }
            } else {
                if (cp == curChar) {
                    prevCharPattern = cp;
                    pattern = pattern.substring(1);
                    text = text.substring(1);
                } else {
                    return false;
                }
            }
        }

        if (!pattern.isEmpty() || !text.isEmpty()) {
            if (!pattern.equals("*"))
                return false;
        }

        return true;
    }
}
