package dp;

public class MaxSum {
    public static void main(String[] args) {
        int[] v = new int[]{1, -1, 6, -4, 2, 2};
        System.out.println("Max sum of nonadjacent subsequence: " + findMaxSumNonadjacent(v));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    private static int findMaxSumNonadjacent(int[] a) {
        if (a == null || a.length < 1)
            return 0;
        if (a.length == 1)
            return a[0];

        int[] result = new int[a.length];
        result[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            result[i] = Math.max(a[i], result[i - 1]);

            if (i >= 2) {
                result[i] = Math.max(result[i], a[i] + result[i - 2]);
            }
        }

        return result[result.length - 1];
    }
}
