package strings;

import java.util.Stack;

public class ReverseWords {
    public static void main(String[] args) {
        char[] sentence = "World Hello".toCharArray();
        reverseWords(sentence);
        System.out.println(String.valueOf(sentence));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    public static void reverseWords(char[] sentence) {
        if (sentence == null || sentence.length == 0) {
            return;
        }

        boolean lastCharSpace = sentence[0] == ' ';
        int start = 0, end = sentence.length;
        strReverese(sentence, start, end);

        int current = 0;

        while (current < sentence.length) {
            if (sentence[current] == ' ') {
                start = strReverese(sentence, start, current);
            }

            current++;
        }

        if (!lastCharSpace)
            strReverese(sentence, start, current);
    }

    private static int strReverese(char[] sentence, int start, int current) {
        int end = current - 1;
        while (start < end) {
            char temp = sentence[start];
            sentence[start] = sentence[end];
            sentence[end] = temp;

            start++;
            end--;
        }

        start = current + 1;
        return start;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    public static void reverseWords2(char[] sentence) {
        Stack<Character> stack = new Stack<>();

        for (char c : sentence) {
            stack.push(c);
        }

        int i = 0;
        StringBuilder singleWord = new StringBuilder();
        while (!stack.isEmpty()) {
            char c = stack.pop();
            if (c != ' ') {
                singleWord.insert(0, c);
            } else {
                i = fillSentenceArray(sentence, i, singleWord);
                sentence[i++] = ' ';
            }
        }

        if (!singleWord.toString().isEmpty()) {
            fillSentenceArray(sentence, i, singleWord);
        }
    }

    private static int fillSentenceArray(char[] sentence, int i, StringBuilder singleWord) {
        char[] singleWordChars = singleWord.toString().toCharArray();
        for (int j = 0; j < singleWord.toString().length(); j++) {
            sentence[i++] = singleWordChars[j];
        }
        singleWord.delete(0, singleWordChars.length);
        return i;
    }
}
