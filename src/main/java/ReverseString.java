import org.junit.Assert;
import org.junit.Test;

/*
Write a function that reverses a string. The input string is given as an array of characters char[].
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
You may assume all the characters consist of printable ascii characters.
 */
public class ReverseString {

    @Test
    public void test() {
        char[] str = reverseString(new char[]{'h', 'e', 'l', 'l', 'o'});
        Assert.assertEquals(String.valueOf(new char[]{'o', 'l', 'l', 'e', 'h'}), String.valueOf(str));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(n / 2) -> O(n)
    Space complexity: O(1)
     */
    public char[] reverseString(char[] s) {
        char temp;
        for (int i = 0; i < s.length / 2; i++) {
            temp = s[s.length - i - 1];
            s[s.length - i - 1] = s[i];
            s[i] = temp;
        }
        return s;
    }
}
