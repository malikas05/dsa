package stack;

import java.util.Arrays;

class NextGreaterChallenge {

    public static void main(String[] args) {
        int[] arr = {4, 6, 3, 2, 8, 1};
        int[] arr1 = {6, 8, 8, 8, -1, 2, -1};
        System.out.println(Arrays.toString(nextGreaterElement(arr1)));
    }

    /*
    Input data -> Array
    [4,6,3,2,8,1]
     */

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    public static int[] nextGreaterElement(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>(arr.length);

        for (int i = arr.length - 1; i >= 0; i--) {
            if (!stack.isEmpty()) {
                while (!stack.isEmpty() && stack.top() <= arr[i]) {
                    stack.pop();
                }
            }

            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.top();
            }
            stack.push(arr[i]);
        }
        return result;
    }
}
