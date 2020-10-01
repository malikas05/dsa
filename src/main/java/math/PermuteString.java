package math;

import java.util.ArrayList;
import java.util.List;

public class PermuteString {
    public static void main(String[] args) {
        System.out.println(permuteString("bad"));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N!)
    Space complexity: O(N!)
     */
    static List<String> permuteString(String input) {
        List<String> result = new ArrayList<>();
        permuteString(input.toCharArray(), 0, result);

        return result;
    }

    private static void permuteString(char[] input, int currentIndex, List<String> result) {
        if (currentIndex == input.length) {
            result.add(String.valueOf(input));
        }

        for (int i = currentIndex; i < input.length; i++) {
            swap(input, currentIndex, i);
            permuteString(input, currentIndex + 1, result);
            swap(input, currentIndex, i);
        }
    }

    private static void swap(char[] input, int i, int j) {
        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
