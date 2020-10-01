package math;

import java.util.ArrayList;

public class SumCombinations {
    public static void main(String[] args) {
        printAllSum(4);
    }

    static ArrayList<ArrayList<Integer>> printAllSum(int target) {
        ArrayList<ArrayList<Integer>> output = new ArrayList<>();
        printAllSumRec(target, 0, 1, output, new ArrayList<>());
        return output;
    }

    private static void printAllSumRec(int target, int currentSum, int start,
                                       ArrayList<ArrayList<Integer>> output, ArrayList<Integer> result) {
        if (target == currentSum) {
            output.add((ArrayList<Integer>) result.clone());
        }

        for (int i = start; i < target; i++) {
            int tempSum = currentSum + i;

            if (tempSum <= target) {
                result.add(i);
                printAllSumRec(target, tempSum, i, output, result);
                result.remove(result.size() - 1);
            } else {
                return;
            }
        }
    }
}
