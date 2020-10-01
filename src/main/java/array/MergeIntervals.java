package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MergeIntervals {

    public static void main(String[] args) {
        ArrayList<Pair> input = new ArrayList<>();
        input.add(new Pair(1, 5));
        input.add(new Pair(3, 7));
        input.add(new Pair(4, 6));
        input.add(new Pair(6, 8));

        ArrayList<Pair> result = mergeIntervals(input);
        result.forEach(pair -> System.out.println(pair.first + " " + pair.second));
    }

    // (1, 5), (3, 7), (4, 6), (6, 8)
    static ArrayList<Pair> mergeIntervals(ArrayList<Pair> v) {
        ArrayList<Pair> result = new ArrayList<Pair>();
        if (v == null || v.isEmpty()) {
            return result;
        }

        Iterator<Pair> iterator = v.iterator();
        Pair next = iterator.next();

        int curFirst = next.first;
        int curSecond = next.second;

        while (iterator.hasNext()) {
            next = iterator.next();

            if (next.first <= curSecond) {
                if (next.second >= curSecond) {
                    curSecond = next.second;
                }
            } else {
                result.add(new Pair(curFirst, curSecond));
                curFirst = next.first;
                curSecond = next.second;
            }
        }

        result.add(new Pair(curFirst, curSecond));

        return result;
    }

    static class Pair {
        public int first;
        public int second;

        public Pair(int x, int y) {
            this.first = x;
            this.second = y;
        }
    }
}
