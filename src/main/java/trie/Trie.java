package trie;

import java.util.HashMap;
import java.util.Objects;

public class Trie {

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String word) {
        if (Objects.isNull(word) || word.isEmpty()) {
            return;
        }

        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(c, (k) -> new TrieNode());
        }
        current.setEndOfWord(true);
    }

    public boolean search(String word) {
        if (Objects.isNull(root) || Objects.isNull(word) || word.isEmpty()) {
            return false;
        }

        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.getChildren().containsKey(c)) {
                return false;
            }
            current = current.getChildren().get(c);
        }

        return current.isEndOfWord();
    }

    public boolean startsWith(String prefix) {
        if (Objects.isNull(root) || Objects.isNull(prefix) || prefix.isEmpty()) {
            return false;
        }

        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            if (!current.getChildren().containsKey(c)) {
                return false;
            }
            current = current.getChildren().get(c);
        }

        return true;
    }

    public void delete(String word) {
        delete(root, word, 0);
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord())
                return false;

            current.setEndOfWord(false);
            return current.getChildren().isEmpty();
        }

        char ch = word.charAt(index);
        TrieNode trieNode = current.getChildren().get(ch);

        if (Objects.isNull(trieNode))
            return false;

        boolean shouldDeleteCurrentNode = delete(trieNode, word, ++index) && !trieNode.isEndOfWord();
        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }

        return false;
    }

    public static class TrieNode {
        private HashMap<Character, TrieNode> children;
        private boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
        }

        public HashMap<Character, TrieNode> getChildren() {
            return children;
        }

        public boolean isEndOfWord() {
            return isEndOfWord;
        }

        public void setChildren(HashMap<Character, TrieNode> children) {
            this.children = children;
        }

        public void setEndOfWord(boolean endOfWord) {
            isEndOfWord = endOfWord;
        }
    }
}
