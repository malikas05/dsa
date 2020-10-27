package cyclicsort;

/*
Problem Statement #
We are given an unsorted array containing ‘n+1’ numbers taken from the range 1 to ‘n’.
The array has only one duplicate but it can be repeated multiple times.
Find that duplicate number without using any extra space. You are, however, allowed to modify the input array.

Example 1:
Input: [1, 4, 4, 3, 2]
Output: 4

Example 2:
Input: [2, 1, 3, 3, 5, 4]
Output: 3

Example 3:
Input: [2, 4, 1, 4, 4]
Output: 4
 */
public class FindDuplicate {
    public static void main(String[] args) {
        System.out.println(findNumber(new int[]{1, 4, 4, 3, 2}));
        System.out.println(findNumber(new int[]{2, 1, 3, 3, 5, 4}));
        System.out.println(findNumber(new int[]{2, 4, 1, 4, 4}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static int findNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] - 1 != i) {
                if (nums[i] == nums[nums[i] - 1]) {
                    return nums[i];
                }

                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }

        return -1;
    }
}
