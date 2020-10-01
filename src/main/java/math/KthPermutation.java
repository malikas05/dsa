package math;

import java.util.ArrayList;
import java.util.List;

public class KthPermutation {
    public static void main(String[] args) {
        List<Character> input = new ArrayList<>();
        input.add('1');
        input.add('2');
        input.add('3');
        input.add('4');
        StringBuilder result = new StringBuilder();
        findKthPermutation(input, 8, result);
        System.out.println(result.toString());
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    static void findKthPermutation(List<Character> v, int k, StringBuilder result) {
        if (v.isEmpty()) {
            return;
        }

        int n = v.size();
        int count = factorial(n - 1);
        int selected = (k - 1) / count;

        result.append(v.get(selected));
        v.remove(selected);

        k = k - count * selected;
        findKthPermutation(v, k, result);
    }

    private static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }
}
