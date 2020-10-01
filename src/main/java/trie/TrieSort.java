package trie;

import java.util.ArrayList;
import java.util.Map;

public class TrieSort {

    public static void main(String[] args) {
        System.out.println(sortArray(new String[]{"a", "abc", "answer", "any", "by", "bye", "the", "their", "there"}));
    }

    /*
    Concept: Pre-order traversal
    - Complexity Analysis:
    Time complexity: O(N log(N)) - N since we need to add each word to trie first. log(N) comes from adding each character into TreeMap
    Space complexity: O(N)
     */
    public static ArrayList<String> sortArray(String[] arr) {
        ArrayList<String> result = new ArrayList<>();
        Trie trie = new Trie();

        for (String word : arr) {
            trie.insert(word);
        }

        getWords(trie.getRoot(), result, "");

        return result;
    }

    private static ArrayList<String> getWords(Trie.TrieNode node, ArrayList<String> result, String prevWord) {
        if (node.isEndOfWord()) {
            result.add(prevWord);
        }

        for (Map.Entry<Character, Trie.TrieNode> entry : node.getChildren().entrySet()) {
            getWords(entry.getValue(), result, prevWord + entry.getKey());
        }

        return result;
    }
}
