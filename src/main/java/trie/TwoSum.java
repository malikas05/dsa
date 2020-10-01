package trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum2(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(twoSum(Arrays.asList(1, 10, 25, 35, 60), 90));
    }

    /*
    https://leetcode.com/problems/two-sum/
    - Complexity Analysis:
    Time complexity: O(N log(N)), where n log n - sorting
    Space complexity: O(1)
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] indices = {-1, -1};
        int i = 0, j = nums.length - 1;
        Arrays.sort(nums);

        while (i < j) {
            if (nums[i] + nums[j] == target) {
                indices[0] = i;
                indices[1] = j;
                return indices;
            } else if (nums[i] + nums[j] > target) {
                j--;
            } else {
                i++;
            }
        }

        return indices;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    public static int[] twoSum2(int[] nums, int target) {
        int[] indices = {-1, -1};
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                indices[0] = map.get(complement);
                indices[1] = i;
            }
            map.put(nums[i], i);
        }

        return indices;
    }

    /*
    https://leetcode.com/discuss/interview-question/356960
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    public static List<Integer> twoSum(List<Integer> nums, int target) {
        target -= 30;
        List<Integer> result = Arrays.asList(-1, -1);
        Map<Integer, Integer> map = new HashMap<>();
        int largest = 0;

        for (int i = 0; i < nums.size(); i++) {
            int complement = target - nums.get(i);
            if ((complement > largest || nums.get(i) > largest) && map.containsKey(complement)) {
                result.set(0, map.get(complement));
                result.set(1, i);
                largest = Math.max(complement, nums.get(i));
            }
            map.put(nums.get(i), i);
        }

        return result;
    }

}
