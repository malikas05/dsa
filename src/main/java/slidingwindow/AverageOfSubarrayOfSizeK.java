package slidingwindow;

import java.util.Arrays;

public class AverageOfSubarrayOfSizeK {
    public static void main(String[] args) {
        double[] result = AverageOfSubarrayOfSizeK.findAverages(5, new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 });
        System.out.println("Averages of subarrays of size K: " + Arrays.toString(result));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
    */
    public static double[] findAverages(int K, int[] arr) {
        if (K <= 0 || arr == null || arr.length == 0)
            return null;

        double[] result = new double[arr.length - K + 1];
        double windowSum = 0;
        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            if (windowEnd >= K - 1) {
                result[windowStart++] = windowSum / K;
                windowSum -= arr[windowEnd];
            }
        }

        return result;
    }
}
