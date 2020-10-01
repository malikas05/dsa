package array;

import java.util.Arrays;

public class RemoveEvenNumber {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(removeEven(new int[]{1, 2, 4, 5, 10, 6, 3})));
    }

    private static int[] removeEven(int[] arr) {
        int currentEven = -1;
        int currentOdd = arr.length;
        int lastEven = 0;

        while (currentEven < currentOdd) {
            currentEven = getNextEvenIndex(currentEven + 1, arr);
            currentOdd = getNextOddIndex(currentOdd - 1, arr);

            if (currentEven < currentOdd) {
                arr[currentEven] = arr[currentOdd];
                lastEven = currentOdd;
            }
        }

        arr = Arrays.copyOf(arr, lastEven);

        return arr;
    }

    private static int getNextEvenIndex(int currentEven, int[] arr) {
        while (currentEven < arr.length) {
            if (arr[currentEven] % 2 == 0) {
                return currentEven;
            }
            currentEven++;
        }

        return -1;
    }

    private static int getNextOddIndex(int currentOdd, int[] arr) {
        while (currentOdd >= 0) {
            if (arr[currentOdd] % 2 != 0) {
                return currentOdd;
            }
            currentOdd--;
        }

        return -1;
    }
}
