package algo;

import heap.Heap;

import java.util.Arrays;
import java.util.List;

public class HeapSort {

    public static void main(String[] args) {
        List<Integer> elements = Arrays.asList(3, 5, 1, 4, 2);
        elements = Heap.sort(elements);
        System.out.println(elements);
    }
}
