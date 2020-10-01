/*
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
https://leetcode.com/problems/integer-to-english-words/
 */
public class IntsToWords {

    private static final int BILLION = 1_000_000_000;
    private static final int MILLION = 1_000_000;
    private static final int THOUSAND = 1_000;

    private static final String[] L0 = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] L1 = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] L2 = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public static void main(String[] args) {
        System.out.println(numberToWords(23001220));
    }
    /*
    - Complexity Analysis:
    Time complexity: O(log (N))
    Space complexity: O(1)
     */
    private static String numberToWords(int num) {
        if (num == 0)
            return "Zero";

        long l = 100l;

        return ntow(num);
    }

    private static String ntow(int num) {
        if (num >= BILLION)
            return concat(ntow(num / BILLION) + " Billion", ntow(num % BILLION));
        if (num >= MILLION)
            return concat(ntow(num / MILLION) + " Million", ntow(num % MILLION));
        if (num >= THOUSAND)
            return concat(ntow(num / THOUSAND) + " Thousand", ntow(num % THOUSAND));

        String ans = num < 100 ? "" : L0[num / 100] + " Hundred";
        num %= 100;
        return num >= 10 && num <= 19 ? concat(ans, L1[num % 10]) : concat(ans, concat(L2[num / 10], L0[num % 10]));
    }

    private static String concat(String s1, String s2) {
        return s1.isEmpty() ? s2 : s2.isEmpty() ? s1 : s1 + " " + s2;
    }

}
