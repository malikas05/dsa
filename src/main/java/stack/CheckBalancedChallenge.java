package stack;

class CheckBalancedChallenge {

    public static void main(String[] args) {
        System.out.println(isBalanced("{[()]"));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    public static boolean isBalanced(String exp) {
        Stack<Character> stack = new Stack<>(exp.length());

        for (int i = 0; i < exp.length(); i++) {
            char chr = exp.charAt(i);

            if (checkClosingChar(chr)) {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    if ((chr == '}' && stack.pop() != '{') || (chr == ')' && stack.pop() != '(') || (chr == ']' && stack.pop() != '[')) {
                        return false;
                    }
                }
            } else {
                stack.push(chr);
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }

        return true;
    }

    private static boolean checkClosingChar(char c) {
        boolean value = c == '}' || c == ')' || c == ']';
        return value;
    }
}
