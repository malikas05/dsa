package strings;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {
    public static void main(String[] args) {
        char[] str = "abbabcddbabcdeedebc".toCharArray();
        removeDuplicates(str);
        System.out.println(String.valueOf(str));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    static void removeDuplicates(char[] str){
        if (str == null || str.length < 2) {
            return;
        }

        Set<Character> set = new HashSet<>();
        int strNewIndex = 0;

        for (int i = 0; i < str.length; i++) {
            if (!set.contains(str[i])) {
                str[strNewIndex++] = str[i];
            }

            set.add(str[i]);
        }

        for (int i = strNewIndex; i < str.length; i++) {
            str[i] = ' ';
        }
    }
}
