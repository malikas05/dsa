package array;

/*
The greatest common divisor (array.GCD), also called highest common factor (HCF) of N numbers is the largest
positive integer which divides all numbers without giving a remainder. Write an algorithm to determine
the array.GCD of N positive integers.
 */
public class GCD {

    public static void main(String[] args) {
        int arr[] = {2, 4, 6, 8, 16};
        System.out.println(generalizedGCD(arr.length, arr));

        int arr2[] = {2, 3, 4, 5, 66};
        System.out.println(generalizedGCD(arr2.length, arr2));
    }

    /*
    - Concept:
    https://www.geeksforgeeks.org/euclidean-algorithms-basic-and-extended/
    https://www.geeksforgeeks.org/java-program-for-gcd-of-more-than-two-or-array-numbers/
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    private static int generalizedGCD(int num, int[] arr) {
        int result = arr[0];
        for (int i = 1; i < num; i++)
            result = gcd(arr[i], result);

        return result;
    }

    private static int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    private static int gcd2(int a, int b) {
        if (a == b)
            return a;
        return a > b ? gcd(a - b, b) : gcd(b - a, a);
    }
}
