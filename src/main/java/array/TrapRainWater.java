package array;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it is able to trap after raining.
https://leetcode.com/problems/trapping-rain-water/
 */
public class TrapRainWater {

    public static void main(String[] args) {
        int[] data = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trapWater(data));

        int[] data2 = {3, 0, 2, 0, 4};
        System.out.println(trapWater2(data2));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N^2)
    Space complexity: O(1)
     */
    private static int trapWater(int[] arr) {
        int res = 0;

        for (int i = 1; i < arr.length - 1; i++) {
            int left = arr[i];
            for (int j = 0; j < i; j++) {
                left = Integer.max(left, arr[j]);
            }

            int right = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                right = Integer.max(right, arr[j]);
            }

            res += Integer.min(left, right) - arr[i];
        }

        return res;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    private static int trapWater2(int[] height) {
        int res = 0;
        int lo = 0, hi = height.length - 1;
        int leftMax = 0, rightMax = 0;

        while (lo <= hi) {
            if (height[lo] < height[hi]) {
                if (height[lo] > leftMax) {
                    leftMax = height[lo];
                } else {
                    res += leftMax - height[lo];
                }
                lo++;
            } else {
                if (height[hi] > rightMax) {
                    rightMax = height[hi];
                } else {
                    res += rightMax - height[hi];
                }
                hi--;
            }
        }

        return res;
    }
}
