package dp;

public class GameScoring {
    public static void main(String[] args) {
        System.out.println(scoringOptions(5));
    }

    public static int scoringOptions(int n) {
        if (n <= 0)
            return 0;

        int[] res = new int[4];
        res[3] = 1;
        for (int i = 1; i <= n; i++) {
            int sum = res[3] + res[2] + res[0];

            res[0] = res[1];
            res[1] = res[2];
            res[2] = res[3];
            res[3] = sum;
        }

        return res[3];
    }
}
