package array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
https://leetcode.com/discuss/interview-question/268604/Google-interview-Number-of-subsets
 */
public class NumSubsets {

    public static void main(String[] args) {
        System.out.println(minMaxKSubset(Arrays.asList(2, 4, 5, 7), 8));
    }

    /*
    [2, 4, 5, 7]
    [1, 4, 3, 2]
     */

    /*
    - Complexity Analysis:
    Time complexity: O(N log(N))
    Space complexity: O(1)
    */
    private static int minMaxKSubset(List<Integer> nums, int k) {
        Collections.sort(nums);
        int countSubset = 0;
        int leftIndex = 0, rightIndex = nums.size() - 1;

        while (leftIndex <= rightIndex) {
            if (nums.get(leftIndex) + nums.get(rightIndex) > k) {
                rightIndex--;
            } else {
//                countSubset += (1 << (j - i)); // equivalent to 2^(j - i)
                countSubset += Math.pow(2, rightIndex - leftIndex);
                leftIndex++;
            }
        }

        return countSubset;
    }

}