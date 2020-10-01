package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:
Input: [3,2,3]
Output: 3

Example 2:
Input: [2,2,1,1,1,2,2]
Output: 2
 */
public class MajorityElement {

    @Test
    public void testMajorityElement() {
        Assert.assertEquals(1, majorityElement2(new int[]{1, 1, 1, 2, 2, 3, 1}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(n)
    Space complexity: O(n)
     */
    public int majorityElement(int[] nums) {
        int length = nums.length;
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.compute(num, (k, v) -> Objects.isNull(v) ? 1 : ++v);
            if (counts.get(num) > length / 2) {
                return num;
            }
        }

        return -1;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(n log n)
    Space complexity: O(1) in case it is allowed to sort using in-place array like quickSort() below, otherwise, it would be O(n)
     */
    public int majorityElement2(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums[nums.length / 2];
    }

    public void quickSort(int[] data, int left, int right) {
        int pivotValue = data[(left + right) / 2];
        int i = left;
        int j = right;

        while (i <= j) {
            while (data[i] < pivotValue) i++;
            while (data[j] > pivotValue) j--;

            if (i <= j) {
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
                i++;
                j--;
            }
        }

        if (left < j) {
            quickSort(data, left, j);
        }

        if (right > i) {
            quickSort(data, i, right);
        }
    }
}
