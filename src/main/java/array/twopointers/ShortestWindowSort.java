package array.twopointers;

/*
Minimum Window Sort (medium) #
Given an array, find the length of the smallest subarray in it which when sorted will sort the whole array.

Example 1:
Input: [1, 2, 5, 3, 7, 10, 9, 12]
Output: 5
Explanation: We need to sort only the subarray [5, 3, 7, 10, 9] to make the whole array sorted

Example 2:
Input: [1, 3, 2, 0, -1, 7, 10]
Output: 5
Explanation: We need to sort only the subarray [1, 3, 2, 0, -1] to make the whole array sorted

Example 3:
Input: [1, 2, 3]
Output: 0
Explanation: The array is already sorted

Example 4:
Input: [3, 2, 1]
Output: 3
Explanation: The whole array needs to be sorted.
 */
public class ShortestWindowSort {
    public static void main(String[] args) {
        System.out.println(ShortestWindowSort.sort(new int[]{1, 2, 5, 3, 7, 10, 9, 12}));
        System.out.println(ShortestWindowSort.sort(new int[]{1, 3, 2, 0, -1, 7, 10}));
        System.out.println(ShortestWindowSort.sort(new int[]{1, 2, 3}));
        System.out.println(ShortestWindowSort.sort(new int[]{3, 2, 1}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static int sort(int[] arr) {
        if (arr == null || arr.length == 0)
            return -1;

        int low = 0, high = arr.length - 1;
        while (low < arr.length - 1 && arr[low] < arr[low + 1])
            low++;

        if (low == arr.length - 1)
            return 0;

        while (high > 0 && arr[high] > arr[high - 1])
            high--;

        int subarrayMax = Integer.MIN_VALUE, subarrayMin = Integer.MAX_VALUE;
        for (int i = low; i <= high; i++) {
            subarrayMax = Math.max(subarrayMax, arr[i]);
            subarrayMin = Math.min(subarrayMin, arr[i]);
        }

        while (low > 0 && arr[low - 1] > subarrayMin)
            low--;
        while (high < arr.length - 1 && arr[high + 1] < subarrayMax)
            high++;

        return high - low + 1;
    }
}
