package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class FindSubsets {
    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(2, 5);
        List<HashSet<Integer>> sets = new ArrayList<>();

        findAllSubsets(input, sets);
    }

    static int getBit(int num, int bit) {
        int temp = (1 << bit);
        temp = temp & num;
        if (temp == 0) {
            return 0;
        }
        return 1;
    }

    static void findAllSubsets(List<Integer> v, List<HashSet<Integer>> sets) {
        int subsetsCount = (int)(Math.pow(2, v.size()));
        for (int i = 0; i < subsetsCount; ++i) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < v.size(); ++j) {
                if (getBit(i, j) == 1) {
                    int x = v.get(j);
                    set.add(x);
                }
            }
            sets.add(set);
        }
    }
}
