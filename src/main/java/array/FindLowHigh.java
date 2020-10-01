package array;

import java.util.Arrays;
import java.util.List;

public class FindLowHigh {

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 6);
        int key = 5;
        int low = findLowIndex(array, key);
        int high = findHighIndex(array, key);
        System.out.println("Low Index of " + key + ": " + low);
        System.out.println("High Index of " + key + ": " + high);

        key = -2;
        low = findLowIndex(array, key);
        high = findHighIndex(array, key);
        System.out.println("Low Index of " + key + ": " + low);
        System.out.println("High Index of " + key + ": " + high);
    }

    /*
    - Complexity Analysis:
    Time complexity: O(log (N)) - binary search for both functions
    Space complexity: O(1)
     */
    static int findLowIndex(List<Integer> arr, int key) {
        int low = 0, high = arr.size() - 1;
        int mid = low + (high - low) / 2;

        while (low <= high) {
            if (arr.get(mid) < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }

            mid = low + (high - low) / 2;
        }

        return (low < arr.size() && arr.get(low).equals(key)) ? low : -1;
    }

    static int findHighIndex(List<Integer> arr, int key) {
        int low = 0, high = arr.size() - 1;
        int mid = low + (high - low) / 2;

        while (low <= high) {
            if (arr.get(mid) <= key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }

            mid = low + (high - low) / 2;
        }

        return (high != -1 && high < arr.size() && arr.get(high).equals(key)) ? high : -1;
    }
}
