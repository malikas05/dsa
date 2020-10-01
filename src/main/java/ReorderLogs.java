import java.util.Arrays;

/*
https://leetcode.com/problems/reorder-data-in-log-files/
 */
public class ReorderLogs {

    public static void main(String[] args) {
        String[] logs = new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"};
        System.out.println(Arrays.toString(reorderLogFiles(logs)));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log (N)) - Merge Sort is used. For built-in sorting, java is using Quick Sort for primitive types
    and Merge Sort iterative approach for object types. Note: String is the object type.
    Space complexity: O(N) from Merge Sort
     */
    public static String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);

            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));

            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp == 0) {
                    return split1[0].compareTo(split2[0]);
                }
                return cmp;
            }

            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });

        return logs;
    }

}
