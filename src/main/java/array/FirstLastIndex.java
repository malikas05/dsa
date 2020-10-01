package array;

import java.util.Arrays;

/*
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class FirstLastIndex {

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange(nums, 8)));
        System.out.println(Arrays.toString(searchRange(nums, 6)));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(log n)
    Space complexity: O(1)
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};

        int leftIdx = binarySearch(nums, target);
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return result;
        } else {
            result[0] = leftIdx;
            result[1] = leftIdx;
        }
        int rightIdx = binarySearch(nums, target + 1) - 1;
        if (rightIdx != -1) {
            result[1] = rightIdx;
        }

        return result;
    }

    private static int binarySearch(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target)
                lo = mid + 1;
            else
                hi = mid;
        }

        return lo;
    }
}
