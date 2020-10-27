package leetcode_contests.biweekly.biweekly_37;

import java.util.ArrayList;
import java.util.List;

public class Fancy {
    public static void main(String[] args) {
        Fancy fancy = new Fancy();
        fancy.append(2); // fancy sequence: [2]
        fancy.addAll(3);   // fancy sequence: [2+3] -> [5]
        fancy.append(7);   // fancy sequence: [5, 7]
        fancy.multAll(2);  // fancy sequence: [5*2, 7*2] -> [10, 14]
        System.out.println(fancy.getIndex(0)); // return 10
        fancy.addAll(3);   // fancy sequence: [10+3, 14+3] -> [13, 17]
        fancy.append(10);  // fancy sequence: [13, 17, 10]
        fancy.multAll(2);  // fancy sequence: [13*2, 17*2, 10*2] -> [26, 34, 20]
        System.out.println(fancy.getIndex(0)); // return 26
        System.out.println(fancy.getIndex(1)); // return 34
        System.out.println(fancy.getIndex(2)); // return 20
    }

    private List<Integer> sequence;

    public Fancy() {
        sequence = new ArrayList<>();
    }

    public void append(int val) {
        sequence.add(val);
    }

    public void addAll(int inc) {
        for (int i = 0; i < sequence.size(); i++) {
            sequence.set(i, sequence.get(i) + inc);
        }
    }

    public void multAll(int m) {
        for (int i = 0; i < sequence.size(); i++) {
            sequence.set(i, sequence.get(i) * m);
        }
    }

    public int getIndex(int idx) {
        if (idx >= sequence.size())
            return -1;

        return (int) (sequence.get(idx) % ((10E+9) + 7));
    }
}
