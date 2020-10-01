package stack;

/*
Compute postfix mathematical expressions using stacks
 */
public class EvaluatePostfixChallenge {

    public static void main(String[] args) {
        int result = evaluatePostFix("921*-8-4+");
        System.out.println(result);
        int result2 = evaluatePostFix("42+351-*+");
        System.out.println(result2);
    }

    /*
    Input data -> String
    921*-8-4+
     */

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    public static int evaluatePostFix(String expression) {
        Stack<Integer> stack = new Stack<>(expression.length());

        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push(Character.getNumericValue(c));
            } else {
                int first = stack.pop();
                int second = stack.pop();
                stack.push(applyOperator(c, second, first));
            }
        }

        if (!stack.isEmpty())
            return stack.top();

        return Integer.MIN_VALUE;
    }

    private static int applyOperator(char operator, int first, int second) {
        switch (operator) {
            case '*':
                return first * second;
            case '/':
                return first / second;
            case '-':
                return first - second;
            case '+':
                return first + second;
        }

        return -1;
    }
}
