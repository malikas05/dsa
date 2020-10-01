package strings;

import java.util.Stack;

public class ExpressionEvaluation {
    public static void main(String[] args) {
        System.out.println(evaluate("6+4/2*2"));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
    */
    private static double evaluate(String expr) {
        if (expr == null || expr.isEmpty())
            return 0;

        char[] chars = expr.toCharArray();
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operations = new Stack<>();

        int i = 0;
        while (i < chars.length) {
            if (isDivide(chars[i])) {
                String num = retrieveNum(chars, i + 1);
                i += num.length() + 1;
                double res = numbers.pop() / Double.parseDouble(num);
                numbers.push(res);
            } else if (isMultiply(chars[i])) {
                String num = retrieveNum(chars, i + 1);
                i += num.length() + 1;
                double res = numbers.pop() * Double.parseDouble(num);
                numbers.push(res);
            } else if (isDigit(chars[i])) {
                String num = retrieveNum(chars, i);
                i += num.length();
                numbers.push(Double.parseDouble(num));
            } else {
                operations.push(chars[i]);
                i++;
            }
        }

        double result = numbers.pop();
        while (!numbers.isEmpty()) {
            char oper = operations.pop();
            if (isAdd(oper)) {
                result += numbers.pop();
            } else if (isSubtract(oper)) {
                result = numbers.pop() - result;
            }
        }

        return result;
    }

    private static String retrieveNum(char[] chars, int startIndex) {
        int j = startIndex;
        StringBuilder strNum = new StringBuilder();
        while (j < chars.length && !isOperator(chars[j])) {
            strNum.append(chars[j]);
            j++;
        }

        return strNum.toString();
    }

    private static boolean isDivide(char c) {
        return c == '/';
    }

    private static boolean isMultiply(char c) {
        return c == '*';
    }

    private static boolean isAdd(char c) {
        return c == '+';
    }

    private static boolean isSubtract(char c) {
        return c == '-';
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
