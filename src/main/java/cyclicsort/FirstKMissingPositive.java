package cyclicsort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Find the First K Missing Positive Numbers (hard) #
Given an unsorted array containing numbers and a number ‘k’, find the first ‘k’ missing positive numbers in the array.

Example 1:
Input: [3, -1, 4, 5, 5], k=3
Output: [1, 2, 6]
Explanation: The smallest missing positive numbers are 1, 2 and 6.

Example 2:
Input: [2, 3, 4], k=3
Output: [1, 5, 6]
Explanation: The smallest missing positive numbers are 1, 5 and 6.

Example 3:
Input: [-2, -3, 4], k=2
Output: [1, 2]
Explanation: The smallest missing positive numbers are 1 and 2.
 */
public class FirstKMissingPositive {
    public static void main(String[] args) {
        System.out.println(findNumbers(new int[]{3, -1, 4, 5, 10}, 8));
        System.out.println(findNumbers(new int[]{2, 3, 4}, 3));
        System.out.println(findNumbers(new int[]{-2, -3, 4}, 2));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N + K)
    Space complexity: O(K)
     */
    public static List<Integer> findNumbers(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            return null;
        }

        List<Integer> missingNumbers = new ArrayList<>();
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

        Set<Integer> candidatesInNums = new HashSet<>();
        for (i = 0; i < nums.length && missingNumbers.size() < k; i++) {
            if (nums[i] != i + 1) {
                missingNumbers.add(i + 1);
                candidatesInNums.add(nums[i]);
            }
        }

        int nextMissingNumber = nums.length + 1;
        while (missingNumbers.size() < k) {
            if (!candidatesInNums.contains(nextMissingNumber)) {
                missingNumbers.add(nextMissingNumber);
            }

            nextMissingNumber++;
        }
        return missingNumbers;
    }
}
