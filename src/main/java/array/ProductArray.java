package array;

import java.util.Arrays;

/*
Given an array nums of n integers where n > 1, return an array output such that
output[i] is equal to the product of all the elements of nums except nums[i].
https://leetcode.com/articles/product-of-array-except-self/
 */
public class ProductArray {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        System.out.println("Array after product: " + Arrays.toString(findProduct2(arr)));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(n)
    Space complexity: O(n)
     */
    public static int[] findProduct(int[] arr) {
        int length = arr.length;
        int[] left = new int[length];
        int[] right = new int[length];

        left[0] = 1;
        for (int i = 1; i < length; i++) {
            left[i] = left[i - 1] * arr[i - 1];
        }

        right[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * arr[i + 1];
        }

        for (int i = 0; i < length; i++) {
            arr[i] = left[i] * right[i];
        }

        return arr;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(n)
    Space complexity: O(1)
     */
    public static int[] findProduct2(int[] arr) {
        int length = arr.length;
        int[] res = new int[length];

        res[0] = 1;
        for (int i = 1; i < length; i++) {
            res[i] = res[i - 1] * arr[i - 1];
        }

        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            res[i] *= R;
            R *= arr[i];
        }

        return res;
    }
}
