package dp;

public class LargestSum {
    public static void main(String[] args) {
        int[] v = new int[]{-4, 2, -5, 1, 2, 3, 6, -5, 1};
        System.out.println("Sum of largest subarray: " + findMaxSumSubArray(v));
    }

    private static int findMaxSumSubArray(int[] A) {
        if (A.length < 1) {
            return 0;
        }

        int currMax = A[0];
        int globalMax = A[0];

        for (int i = 1; i < A.length; ++i) {
            if (currMax < 0) {
                currMax = A[i];
            } else {
                currMax += A[i];
            }

            if (globalMax < currMax) {
                globalMax = currMax;
            }
        }

        return globalMax;
    }
}
