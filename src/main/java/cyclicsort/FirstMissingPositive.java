package cyclicsort;

/*
Find the Smallest Missing Positive Number (medium) #
Given an unsorted array containing numbers, find the smallest missing positive number in it.

Example 1:
Input: [-3, 1, 5, 4, 2]
Output: 3
Explanation: The smallest missing positive number is '3'

Example 2:
Input: [3, -2, 0, 1, 2]
Output: 4

Example 3:
Input: [3, 2, 5, 1]
Output: 4
 */
public class FirstMissingPositive {
    public static void main(String[] args) {
        System.out.println(findNumber(new int[]{-3, 1, 5, 4, 2}));
        System.out.println(findNumber(new int[]{3, -2, 0, 1, 2}));
        System.out.println(findNumber(new int[]{3, 2, 5, 1}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static int findNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) {
                return j + 1;
            }
        }

        return -1;
    }
}
