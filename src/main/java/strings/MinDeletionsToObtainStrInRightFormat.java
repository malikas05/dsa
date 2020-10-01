package strings;
/*
We are given a string S of length N consisting only of letters 'A and/or 'B. Our goal is to obtain a string in the format "A...AB...B"
(all letters 'A' occur before all letters 'B') by deleting some letters from S. In particular, strings consisting only of letters
'A' or only letters 'B' fit this format. Write a function that, given a string S, returns the minimum number of letters that need
to be deleted from S in order to obtain a string in the above format.
*/

public class MinDeletionsToObtainStrInRightFormat {
    public static void main(String[] args) {
        System.out.println(countDeletions("BAAABAB"));
        System.out.println(countDeletions("BBABABA"));
        System.out.println(countDeletions("AABBBB"));
        System.out.println(countDeletions("AAAAABBBB"));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
    */
    public static int solution(String S) {
        if (S == null || S.isEmpty())
            return 0;

        int countA = 0, countB = 0, countForRemoval = 0;

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            if (c == 'A') {
                countA++;

                if (countB > countForRemoval) {
                    countForRemoval++;
                }
            } else {
                countB++;

                if (countA == 0) {
                    countForRemoval++;
                }
            }
        }

        return Math.min(countA, Math.min(countB, countForRemoval));
    }

    public static int countDeletions(String S) {
        int countA = 0, countB = 0, countToRemove = 0;

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            if (c == 'A') {
                countA++;

                if (countB > countToRemove)
                    countToRemove++;
            } else {
                countB++;

                if (countA == 0)
                    countToRemove++;
            }
        }

        return Math.min(countA, countToRemove);
    }
}
