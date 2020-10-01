package array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Eight houses, represented as cells, are arranged in a straight line. Each day every cell
competes with its adjacent cells (neighbors). An integer value of 1 represents an active
cell and a value of 0 represents an inactive cell. If the neighbors on both the side of a
cell are either active or inactive, the cell becomes inactive on the next day; otherwise,
the cell becomes active. The two cells on each end have a single adjacent cell, so
assume that the unoccupied space on the opposite side is an inactive cell. Even after
updating the cell state, consider its previous state when updating the state of other
cells. The state information of all cells should be updated simultaneously.
 */
public class ActiveInactiveCells {

    public static void main(String[] args) {
        System.out.println(cellCompete(new int[]{1, 0, 0, 0, 0, 1, 0, 0}, 1));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(K * N)
    Space complexity: O(1)
     */
    public static List<Integer> cellCompete(int[] states, int days) {
        int curDay = 0;
        int oldValue = 0;
        int newValue;

        while (curDay < days) {
            for (int i = 0; i < states.length; i++) {
                if (i == states.length - 1) {
                    newValue = oldValue ^ 0;
                    oldValue = states[i];
                    states[i] = newValue;
                } else {
                    newValue = oldValue ^ states[i + 1];
                    oldValue = states[i];
                    states[i] = newValue;
                }
            }
            oldValue = 0;
            curDay++;
        }

        return Arrays
                .stream(states)
                .boxed()
                .collect(Collectors.toList());
    }
}
