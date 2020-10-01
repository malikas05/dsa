package queue;

import stack.Stack;

/*
Reverse K numbers in queue using stack solely
 */
public class ReverseKNumChallenge {

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>(5);
        queue.enqueue("3");
        queue.enqueue("5");
        queue.enqueue("7");
        queue.enqueue("9");
        queue.enqueue("1");
        reverseK(queue, 2);

        while (!queue.isEmpty()) {
            System.out.print(queue.dequeue() + " ");
        }
    }

    public static <V> void reverseK(Queue<V> queue, int k) {
        if (queue.isEmpty() || k < 0) {
            return;
        }
        Stack<V> stack = new Stack<>(k);

        while (!stack.isFull()) {
            stack.push(queue.dequeue());
        }

        while (!stack.isEmpty()) {
            queue.enqueue(stack.pop());
        }

        for (int i = 0; i < queue.getCurrentSize() - k; i++) {
            queue.enqueue(queue.dequeue());
        }
    }
}
