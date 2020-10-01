import org.junit.Test;

import java.util.*;

/*
Write a program that outputs the string representation of numbers from 1 to n.
But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.
 */
public class FizzBuzz {

    @Test
    public void test() {
        System.out.println(fizzBuzz(15));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(n)
    Space complexity: O(1) - because the list we return is required by the method signature itself and is not counted for the Space Complexity
     */
    public List<String> fizzBuzz(int n) {
        List<String> values = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                values.add("FizzBuzz");
            } else if (i % 3 == 0) {
                values.add("Fizz");
            } else if (i % 5 == 0) {
                values.add("Buzz");
            } else {
                values.add(String.valueOf(i));
            }
        }

        return values;
    }

    /*
    - Concept:
    This approach is more efficient as it will avoid to check too many conditions in case any extra combination would be required to check, e.g. Jazz for multiplies of 7
    - Complexity Analysis:
    Time complexity: O(n)
    Space complexity: O(1) - because the list we return is required by the method signature itself and is not counted for the Space Complexity
     */
    public List<String> fizzBuzz2(int n) {
        List<String> values = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            boolean divisibleBy3 = i % 3 == 0;
            boolean divisibleBy5 = i % 5 == 0;

            String str = "";
            if (divisibleBy3) {
                str += "Fizz";
            }
            if (divisibleBy5) {
                str += "Buzz";
            }
            if (str.isEmpty()) {
                str += i;
            }

            values.add(str);
        }

        return values;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(n)
    Space complexity: O(1)
     */
    public List<String> fizzBuzz3(int n) {
        List<String> values = new ArrayList<>();

        Map<Integer, String> mappings = new LinkedHashMap<>() {{
            put(3, "Fizz");
            put(5, "Buzz");
        }};

        for (int i = 1; i <= n; i++) {
            String str = "";
            for (Integer key : mappings.keySet()) {
                if (i % key == 0) {
                    str += mappings.get(key);
                }
            }

            if (str.isEmpty()) {
                str += i;
            }

            values.add(str);
        }

        return values;
    }
}
