package leetcode_contests.weekly.weekly_209;

import java.util.Arrays;

/*
1608. Special Array With X Elements Greater Than or Equal X: https://leetcode.com/contest/weekly-contest-209/problems/special-array-with-x-elements-greater-than-or-equal-x/
You are given an array nums of non-negative integers. nums is considered special if there exists a number x such that there are exactly x numbers in nums that are greater than or equal to x.
Notice that x does not have to be an element in nums.
Return x if the array is special, otherwise, return -1. It can be proven that if nums is special, the value for x is unique.

Example 1:
Input: nums = [3,5]
Output: 2
Explanation: There are 2 values (3 and 5) that are greater than or equal to 2.

Example 2:
Input: nums = [0,0]
Output: -1
Explanation: No numbers fit the criteria for x.
If x = 0, there should be 0 numbers >= x, but there are 2.
If x = 1, there should be 1 number >= x, but there are 0.
If x = 2, there should be 2 numbers >= x, but there are 0.
x cannot be greater since there are only 2 numbers in nums.

Example 3:
Input: nums = [0,4,3,0,4]
Output: 3
Explanation: There are 3 values that are greater than or equal to 3.

Example 4:
Input: nums = [3,6,7,7,0]
Output: -1

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 1000
 */
public class SpecialArrayWithX {
    public static void main(String[] args) {
        System.out.println(specialArray2(new int[]{3, 5}));
        System.out.println(specialArray2(new int[]{0, 0}));
        System.out.println(specialArray2(new int[]{0, 4, 3, 0, 4}));
        System.out.println(specialArray2(new int[]{3, 6, 7, 7, 0}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log(N))
    Space complexity: O(N)
     */
    public static int specialArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;

        Arrays.sort(nums);
        int x = 1, i = 0;

        while (nums.length - i >= x) {
            if (x <= nums[i]) {
                if (nums.length - i == x)
                    return x;
                else
                    x += 1;
            } else {
                i += 1;
            }
        }

        return -1;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static int specialArray2(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;

        int[] arr = new int[1001];

        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]] += 1;
        }

        for (int i = arr.length - 2; i >= 0; i--) {
            arr[i] += arr[i + 1];
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == i)
                return i;
        }

        return -1;
    }
}
