package queue;

import java.util.Arrays;

public class FindBinaryChallenge {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findBinary2(15)));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N) but we have some overload here due to the memory allocated for recursion
    and extra loop through small queues which contain the binary digits which are being merged.
    !!! This approach is less efficient than findBin2()
     */
    public static String[] findBinary(int number) {
        String[] result = new String[number];
        int counter = 0;

        for (int i = 1; i <= number; i++) {
            Queue<String> queue = new Queue<>(number + 1);
            queue = createBinaryQueue(i, queue);

            if (!queue.isEmpty()) {
                String binaryStr = "";
                while (!queue.isEmpty()) {
                    binaryStr += queue.dequeue();
                }
                result[counter++] = binaryStr;
            }
        }
        return result; //For number = 3 , result = {"1","10","11"};
    }

    private static Queue<String> createBinaryQueue(int number, Queue<String> queue) {
        if (number > 1) {
            createBinaryQueue(number / 2, queue);
        }
        queue.enqueue(String.valueOf(number % 2));
        return queue;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N) - traverse only once
    Space complexity: O(N)
     */
    public static String[] findBinary2(int number) {
        String[] result = new String[number];
        Queue<String> queue = new Queue<>(number + 1);

        queue.enqueue("1");

        for (int i = 0; i < number; i++) {
            result[i] = queue.dequeue();
            queue.enqueue(result[i] + "0");
            queue.enqueue(result[i] + "1");
        }

        return result;
    }
}
