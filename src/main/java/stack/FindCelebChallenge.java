package stack;

import java.util.stream.IntStream;

class FindCelebChallenge {

    public static void main(String[] args) {
        int[][] party = {
                {0,0,0,0},
                {1,0,0,1},
                {1,0,0,1},
                {1,1,1,0}
        };

        System.out.println(findCelebrity2(party, party.length));
    }

    /*
    Input data -> 2D Array or Matrix
    party = {
          {0,1,1,0},
          {1,0,1,1},
          {0,0,0,0},
          {0,1,1,0}
    }
    */

    private static boolean aqStatus(int[][] party, int x, int y) {
        if (party[x][y] == 1) return true;
        return false;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    public static int findCelebrity2(int[][] party, int numPeople) {
        int celebrity = -1;
        Stack<Integer> stack = new Stack<>(numPeople);

        //Push all people in stack
        IntStream.range(0, party.length).forEach(stack::push);

        while (!stack.isEmpty()) {
            //Take two people out of stack and check if they know each other
            //One who doesn't know the other, push it back in stack.
            int first = stack.pop();

            if (stack.isEmpty()) {
                celebrity = first;
                break;
            }

            int second = stack.pop();
            if (aqStatus(party, first, second)) {
                //first knows second, discard first and push second in stack
                stack.push(second);
            } else {
                stack.push(first);
            }
        }

        //At this point we will have last element of stack as celebrity. Check it to make sure it's the right celebrity
        for (int j = 0; j < numPeople; j++) {
            //Celebrity knows no one while everyone knows celebrity
            if (celebrity != j && (aqStatus(party, celebrity, j) || !aqStatus(party, j, celebrity))) {
                return -1;
            }
        }

        return celebrity;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N*M) where N is the number of rows and M is the number of columns. Since we have matrix,
    it could be claimed that the time complexity is actually O(N^2) in the worst case. This method actually doesn't require to use
    stack solely, it can use any other data structure to store elements.
    Space complexity: O(N)
     */
    public static int findCelebrity(int[][] party, int numPeople) {
        int celebrity = -1;

        for (int i = 0; i < numPeople; i++) {
            Stack<Integer> stack = new Stack<>(numPeople);
            for (int j = 0; j < numPeople; j++) {
                if (party[j][i] != 0) {
                    stack.push(party[j][i]);
                } else if (celebrity == -1) {
                    celebrity = j;
                }
            }

            if (stack.getCurrentSize() == numPeople - 1 && celebrity > -1) {
                return celebrity;
            } else {
                celebrity = -1;
            }
        }

        return celebrity;
    }
}
