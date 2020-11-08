package leetcode_contests.weekly.weekly_213;

import java.util.HashMap;
import java.util.Map;

/*
1640. Check Array Formation Through Concatenation - https://leetcode.com/problems/check-array-formation-through-concatenation/
You are given an array of distinct integers arr and an array of integer arrays pieces, where the integers in pieces are distinct.
Your goal is to form arr by concatenating the arrays in pieces in any order. However, you are not allowed to reorder the integers in each array pieces[i].
Return true if it is possible to form the array arr from pieces. Otherwise, return false.

Example 1:
Input: arr = [85], pieces = [[85]]
Output: true

Example 2:
Input: arr = [15,88], pieces = [[88],[15]]
Output: true
Explanation: Concatenate [15] then [88]

Example 3:
Input: arr = [49,18,16], pieces = [[16,18,49]]
Output: false
Explanation: Even though the numbers match, we cannot reorder pieces[0].

Example 4:
Input: arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
Output: true
Explanation: Concatenate [91] then [4,64] then [78]

Example 5:
Input: arr = [1,3,5,7], pieces = [[2,4,6,8]]
Output: false

Constraints:
1 <= pieces.length <= arr.length <= 100
sum(pieces[i].length) == arr.length
1 <= pieces[i].length <= arr.length
1 <= arr[i], pieces[i][j] <= 100
The integers in arr are distinct.
The integers in pieces are distinct (i.e., If we flatten pieces in a 1D array, all the integers in this array are distinct).
 */
public class CheckArrayFormationThroughConcatenation {
    public static void main(String[] args) {
        System.out.println(canFormArray(new int[]{91, 4, 64, 78}, new int[][]{{78}, {4, 64}, {91}}));
        System.out.println(canFormArray(new int[]{97, 80, 56, 85, 60, 33, 26, 23, 99, 98, 19, 34, 30, 66}, new int[][]{{98, 19}, {23, 99}, {97, 80, 56, 85, 60}, {33, 26}, {34}, {30, 66}}));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    public static boolean canFormArray(int[] arr, int[][] pieces) {
        if (arr == null || arr.length == 0 || pieces == null || pieces.length == 0) {
            return false;
        }

        Map<Integer, int[]> mapPieces = new HashMap<>();
        for (int[] piece : pieces) {
            mapPieces.putIfAbsent(piece[0], piece);
        }

        int i = 0;
        while (i < arr.length) {
            if (!mapPieces.containsKey(arr[i])) {
                return false;
            }

            int[] pieceArr = mapPieces.get(arr[i]);
            if (pieceArr.length > 1) {
                int k = 1;
                i += 1;
                while (k < pieceArr.length && i < arr.length) {
                    if (arr[i] != pieceArr[k]) {
                        return false;
                    }

                    k++;
                    i++;
                }
            } else {
                i++;
            }
        }

        return true;
    }
}
