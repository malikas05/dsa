package array;

import java.util.HashSet;
import java.util.Set;

public class FindSum {

    public static void main(String[] args) {
        int[] data = {5, 7, 1, 2, 8, 4, 3};
        System.out.println(findSumOfTwo(data, 10));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    static boolean findSumOfTwo(int[] A, int val) {
        if (A == null || A.length < 2) {
            return false;
        }

        Set<Integer> set = new HashSet<>();

        for (int num : A) {
            if (set.contains(val - num)) {
                return true;
            }
            set.add(num);
        }

        return false;
    }
}
