package stack;

/*
Sort the values in a stack
 */
public class SortStackChallenge {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>(7);
        stack.push(2);
        stack.push(97);
        stack.push(4);
        stack.push(42);
        stack.push(12);
        stack.push(60);
        stack.push(23);
        sortStack(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    /* Input data -> Stack
    2
    97
    4
    42
    12
    60
    23
    */

    /*
    - Complexity Analysis:
    Time complexity: O(N^2) - in the worst case
    Space complexity: O(1)
     */
    public static Stack<Integer> sortStack(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            int value = stack.pop();
            sortStack(stack);
            insert(stack, value);
        }
        return stack;
    }

    public static void insert(Stack<Integer> stack, int value) {
        if (stack.isEmpty() || stack.top() > value) {
            stack.push(value);
        } else {
            int temp = stack.pop();
            insert(stack, value);
            stack.push(temp);
        }
    }
}
