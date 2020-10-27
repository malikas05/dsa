package leetcode_contests.weekly.weekly_211;

import java.util.*;

/*
1626. Best Team With No Conflicts - https://leetcode.com/problems/best-team-with-no-conflicts/
You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest overall score. The score of the team is the sum of scores of all the players in the team.
However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a strictly higher score than an older player. A conflict does not occur between players of the same age.
Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player, respectively, return the highest overall score of all possible basketball teams.

Example 1:
Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
Output: 34
Explanation: You can choose all the players.

Example 2:
Input: scores = [4,5,6,5], ages = [2,1,2,1]
Output: 16
Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.

Example 3:
Input: scores = [1,2,3,5], ages = [8,9,10,1]
Output: 6
Explanation: It is best to choose the first 3 players.

Constraints:
1 <= scores.length, ages.length <= 1000
scores.length == ages.length
1 <= scores[i] <= 106
1 <= ages[i] <= 1000
 */
public class BestTeamScore {
    public static void main(String[] args) {
        System.out.println(bestTeamScore(new int[]{1, 3, 5, 10, 15}, new int[]{1, 2, 3, 4, 5}));
        System.out.println(bestTeamScore(new int[]{4, 5, 6, 5}, new int[]{2, 1, 2, 1}));
        System.out.println(bestTeamScore(new int[]{1, 2, 3, 5}, new int[]{8, 9, 10, 1}));
        System.out.println(bestTeamScore(new int[]{1, 3, 7, 3, 2, 4, 10, 7, 5}, new int[]{4, 5, 2, 1, 1, 2, 4, 1, 4}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N^2)
    Space complexity: O(N)
     */
    public static int bestTeamScore(int[] scores, int[] ages) {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < scores.length; i++) {
            players.add(new Player(scores[i], ages[i]));
        }
        players.sort(Comparator.naturalOrder());

        int[] sequence = new int[players.size()];
        for (int i = 0; i < players.size(); i++) {
            sequence[i] = players.get(i).score;
        }

        int maxScore = 0;
        int[] dp = new int[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            dp[i] = sequence[i];
        }

        for (int i = 0; i < sequence.length; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence[j] <= sequence[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + sequence[i]);
                }
            }

            maxScore = Math.max(maxScore, dp[i]);
        }

        return maxScore;
    }

    private static class Player implements Comparable<Player> {
        private int score;
        private int age;

        public Player(int score, int age) {
            this.score = score;
            this.age = age;
        }

        @Override
        public int compareTo(Player o) {
            return this.age == o.age ? Integer.compare(this.score, o.score) : Integer.compare(this.age, o.age);
        }
    }
}
