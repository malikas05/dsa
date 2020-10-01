package trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/*
https://leetcode.com/problems/longest-word-in-dictionary/
 */
public class LongestWord {

    public static void main(String[] args) {
        String[] words = {"w", "wo", "wor", "worl", "world"};
        System.out.println(longestWord(words));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
    */
    public static String longestWord(String[] words) {
        Trie trie = new Trie();
        trie.setWords(words);

        int index = 0;
        for (String word : words) {
            trie.insert(word, ++index);
        }

        return trie.searchLongestWord();
    }

    private static class Trie {

        private TrieNode root;
        private String[] words;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word, int index) {
            if (Objects.isNull(word) || word.isEmpty()) {
                return;
            }

            TrieNode curNode = root;
            for (char c : word.toCharArray()) {
                curNode = curNode.getChildren().computeIfAbsent(c, (k) -> new TrieNode());
            }

            curNode.setEnd(index);
        }

        public String searchLongestWord() {
            if (root.getChildren().isEmpty()) {
                return "";
            }

            String result = "";
            Stack<TrieNode> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                TrieNode curNode = stack.pop();
                if (curNode.getEnd() > 0 || curNode == root) {
                    if (curNode != root) {
                        String word = words[curNode.getEnd() - 1];
                        if (word.length() > result.length() || word.length() == result.length() && word.compareTo(result) < 0) {
                            result = word;
                        }
                    }
                    for (TrieNode trieNode : curNode.getChildren().values()) {
                        stack.push(trieNode);
                    }
                }
            }

            return result;
        }

        public TrieNode getRoot() {
            return root;
        }

        public String[] getWords() {
            return words;
        }

        public void setWords(String[] words) {
            this.words = words;
        }

        private static class TrieNode {
            private Map<Character, TrieNode> children;
            private int end;

            public TrieNode() {
                this.children = new HashMap<>();
            }

            public Map<Character, TrieNode> getChildren() {
                return children;
            }

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }
        }
    }

}
