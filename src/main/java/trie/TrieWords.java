package trie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

public class TrieWords {

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("a");
        trie.insert("abc");
        trie.insert("answer");
        trie.insert("any");
        trie.insert("by");
        trie.insert("bye");
        trie.insert("the");
        trie.insert("their");
        trie.insert("there");

        System.out.println(findWords(trie.getRoot()));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log(N)) - N comes from visiting each node, whereas N log(N) comes from built-in Merge Sort.
    Space complexity: O(1)
     */
    public static ArrayList<String> findWords(Trie.TrieNode root) {
        ArrayList<String> result = new ArrayList<>();

        if (Objects.isNull(root) || root.getChildren().isEmpty())
            return new ArrayList<>();

        result = getWords(root, result, "");

        result.sort(Comparator.naturalOrder());
        return result;
    }

    private static ArrayList<String> getWords(Trie.TrieNode node, ArrayList<String> result, String prevWord) {
        for (Map.Entry<Character, Trie.TrieNode> entry : node.getChildren().entrySet()) {
            getWords(entry.getValue(), result, prevWord + entry.getKey());
        }

        if (node.isEndOfWord()) {
            result.add(prevWord);
        }

        return result;
    }
}
