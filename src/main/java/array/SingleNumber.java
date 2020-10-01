package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
Given a non-empty array of integers, every element appears twice except for one. Find that single one.
Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class SingleNumber {

    @Test
    public void test() {
        Assert.assertEquals(4, singleNumber(new int[] {4,1,2,1,2}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(n)
    Space complexity: O(n) - to store in HashMap
     */
    public static int singleNumber(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.remove(num);
            } else {
                map.putIfAbsent(num, true);
            }
        }
        return map.keySet().stream().findFirst().get();
    }

    /*
    - Concept:
    If we take XOR of zero and some bit, it will return that bit: a ^ 0 = a
    If we take XOR of two same bits, it will return 0: a ^ a = 0
    If we take XOR of two different bits, it will return 1: a ^ b = 1
    a ^ b ^ a = 0 ^ b = b -> So we can XOR all bits together to find the unique number.
    - Complexity Analysis:
    Time complexity: O(n)
    Space complexity: O(1)
     */
    public static int singleNumber1(int[] nums) {
        int a = 0;
        for (int num : nums) {
            a ^= num;
        }
        return a;
    }

    /*
    - Concept: 2 * (a + b + c) - (a + a + b + b + c) = c
    - Complexity Analysis:
    Time complexity: O(n)
    Space complexity: O(n)
     */
    public static int singleNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        for (int num : nums) {
            set.add(num);
            sum += num;
        }

        return 2 * Arrays.stream(set.toArray(new Integer[set.size()])).mapToInt(Integer::intValue).sum() - sum;
    }

    /*
    - Info: this implementation would work when the array contains more than just two occurrences of the same number
    - Complexity Analysis:
    Time complexity: O(n)
    Space complexity: O(n) - to store in HashMap
     */
    public static int singleNumber3(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int num : nums) {
            map.compute(num, (k, v) -> v != null);
        }

        return map.entrySet().stream().filter(e -> !e.getValue()).findFirst().get().getKey();
    }
}
