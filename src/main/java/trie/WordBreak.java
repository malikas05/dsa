package trie;

import java.util.*;

/*
https://leetcode.com/problems/word-break/
 */
public class WordBreak {

    public static void main(String[] args) {
        System.out.println(wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
        System.out.println(wordBreak("aaaaaaa", Arrays.asList("aaaa", "aaa")));
        System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(wordBreak("applepenapple", Arrays.asList("apple", "pen")));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        Trie trie = new Trie();

        for (String word : wordDict) {
            trie.insert(word);
        }

        return trie.search(s);
    }

    public static class Trie {

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            if (Objects.isNull(word) || word.isEmpty()) {
                return;
            }

            TrieNode current = root;
            for (char c : word.toCharArray()) {
                current = current.getChildren().computeIfAbsent(c, (k) -> new TrieNode());
            }

            current.setEndWord(true);
        }

        /*
        - Complexity Analysis:
        Time complexity: O(N)
        Space complexity: O(N)
        */
        public boolean search(String word) {
            if (Objects.isNull(word) || word.isEmpty()) {
                return false;
            }

            boolean[] dp = new boolean[word.length()];
            char[] chars = word.toCharArray();
            int j = 0;
            TrieNode current = root;

            while (j < word.length()) {
                if (current.getChildren().containsKey(chars[j])) {
                    current = current.getChildren().get(chars[j]);
                    if (current.isEndWord()) {
                        dp[j] = true;
                    }
                    j++;
                } else if (root.getChildren().containsKey(chars[j])) {
                    current = root;
                } else {
                    return false;
                }
            }

            return dp[word.length() - 1];
        }

//        public boolean search(String word) {
//            if (Objects.isNull(word) || word.isEmpty()) {
//                return false;
//            }
//
//            boolean[] dp = new boolean[word.length() + 1];
//            char[] chars = word.toCharArray();
//            dp[0] = true;
//
//            for (int i = 0; i < chars.length; i++) {
//                if (!dp[i])
//                    continue;
//
//                TrieNode current = root;
//                int j = i;
//                while (j < word.length() && current.getChildren().containsKey(chars[j])) {
//                    current = current.getChildren().get(chars[j++]);
//                    if (current.isEndWord()) {
//                        dp[j] = true;
//                    }
//                }
//            }
//
//            return dp[word.length()];
//        }
    }

    private static class TrieNode {
        private Map<Character, TrieNode> children;
        private boolean endWord;

        public TrieNode() {
            this.children = new HashMap<>();
        }

        public Map<Character, TrieNode> getChildren() {
            return children;
        }

        public void setChildren(Map<Character, TrieNode> children) {
            this.children = children;
        }

        public boolean isEndWord() {
            return endWord;
        }

        public void setEndWord(boolean endWord) {
            this.endWord = endWord;
        }
    }
}
