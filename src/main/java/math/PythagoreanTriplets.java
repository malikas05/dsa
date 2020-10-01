package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PythagoreanTriplets {

    public static void main(String[] args) {
        List<int[]> result = findPythagoreanTriplets(new int[] {4, 16, 1, 2, 3, 5, 6, 8, 25, 10});
        System.out.println(result.stream().map(Arrays::toString).collect(Collectors.joining(",")));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N^2)
    Space complexity: O(N log (N)) - for sorting
     */
    static List<int[]> findPythagoreanTriplets(int[] arr) {
        List<int[]> triplets = new ArrayList<>();

        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0)
                continue;
            int c2 = arr[i] * arr[i];

            int j = 0;
            int k = arr.length - 1;

            while (j < k) {
                if (j == i || arr[j] == 0) {
                    j++;
                    continue;
                }

                if (k == i || arr[k] == 0) {
                    k--;
                    continue;
                }

                int a2 = arr[j] * arr[j];
                int b2 = arr[k] * arr[k];

                if (a2 + b2 + (-c2) == 0) {
                    triplets.add(new int[]{arr[j], arr[k], arr[i]});
                    break;
                } else if (a2 + b2 + (-c2) > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }

        return triplets;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N^3)
    Space complexity: O(1)
     */
    static List<int[]> findPythagoreanTriplets2(int[] arr) {
        List<int[]> triplets = new ArrayList<>();

        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (Math.pow(arr[i], 2) + Math.pow(arr[j], 2) == Math.pow(arr[k], 2)) {
                        triplets.add(new int[]{arr[i], arr[j]});
                    }
                }
            }
        }

        return triplets;
    }
}
