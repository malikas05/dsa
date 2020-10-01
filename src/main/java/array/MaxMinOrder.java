package array;

import org.junit.Test;

import java.util.Arrays;

/*
Given an array, can you re-arrange the elements such that the first position will have the largest number,
the second will have the smallest, the third will have the second-largest, and so on.
 */
public class MaxMinOrder {

    @Test
    public void testMaxMin() {
        int[] data = {1, 2, 3, 4, 5, 6};
        maxMin2(data);
        System.out.println(Arrays.toString(data));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(n)
    Space complexity: O(n)
     */
    public void maxMin(int[] arr) {
        int pointerSmall = 0;
        int pointerLarge = arr.length - 1;
        boolean reverse = true;
        int[] resultArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            resultArr[i] = reverse ? arr[pointerLarge--] : arr[pointerSmall++];
            reverse = !reverse;
        }

        System.arraycopy(resultArr, 0, arr, 0, resultArr.length);
    }

    /*
    - Concept:
    ***************************
    {1, 2, 3, 4, 5, 6}
    max = last + 1 = 6 + 1 = 7

    0: arr[i] = arr[i] + (6 % 7) * 7 = 1 + 42 = 43
    Old value: 43 % 7 = 1
    New value: 43 / 7 = 6

    1: arr[i] = arr[i] + (1 % 7) * 7 = 2 + 7 = 9
    Old value: 9 % 7 = 2
    New value: 9 / 7 = 1
    ***************************
    - Complexity Analysis:
    Time complexity: O(n)
    Space complexity: O(1)
     */
    public void maxMin2(int[] arr) {
        int maxValue = arr[arr.length - 1] + 1;
        int minIndex = 0;
        int maxIndex = arr.length - 1;

        for (int i = 0; i < arr.length; i++) {
            arr[i] += (i % 2 == 0)
                    ? ((arr[maxIndex--] % maxValue) * maxValue)
                    : ((arr[minIndex++] % maxValue) * maxValue);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] / maxValue;
        }
    }
}
