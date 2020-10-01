package trie;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class NumWords {

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

        System.out.println(totalWords(trie.getRoot()));
        System.out.println(traverseRecursive(trie.getRoot()));
    }

    /*
    - Concept: DFS approach
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    public static int totalWords(Trie.TrieNode root) {
        if (Objects.isNull(root) || root.getChildren().isEmpty())
            return 0;

        Deque<Trie.TrieNode> stack = new ArrayDeque<>();
        int total = 0;
        stack.push(root);

        while (!stack.isEmpty()) {
            Trie.TrieNode current = stack.pop();
            total += current.isEndOfWord() ? 1 : 0;

            for (Trie.TrieNode node : current.getChildren().values()) {
                stack.push(node);
            }
        }

        return total;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(1)
     */
    private static int traverseRecursive(Trie.TrieNode node) {
        int total = node.isEndOfWord() ? 1 : 0;
        for (Trie.TrieNode curNode : node.getChildren().values()) {
            total += traverseRecursive(curNode);
        }

        return total;
    }

}
