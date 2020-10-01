package strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StringSegmentation {
    public static void main(String[] args) {
        Set<String> dictionary = Set.of("apple", "pear", "pier", "pie");
        String s = "applepie";
        boolean canSegmentString = canSegmentString(s, dictionary);
        System.out.println(canSegmentString);
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
    */
    public static boolean canSegmentString(String s, Set<String> dictionary) {
        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }

        return trie.search(s);
    }

    public static class Trie {
        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            if (word == null || word.isEmpty()) {
                return;
            }

            char[] charArray = word.toCharArray();
            TrieNode cur = root;
            for (char c : charArray) {
                cur = cur.getChildren().computeIfAbsent(c, (k) -> new TrieNode());
            }

            cur.setEndWord(true);
        }

        public boolean search(String word) {
            if (word == null || word.isEmpty()) {
                return false;
            }

            char[] charArray = word.toCharArray();
            boolean[] dp = new boolean[charArray.length];
            TrieNode cur = root;
            int i = 0;

            while (i < charArray.length) {
                if (cur.getChildren().containsKey(charArray[i])) {
                    cur = cur.getChildren().get(charArray[i]);

                    if (cur.isEndWord()) {
                        dp[i] = true;
                    }
                    i++;
                } else if (root.getChildren().containsKey(charArray[i])) {
                    cur = root;
                } else {
                    return false;
                }
            }

            return dp[dp.length - 1];
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

            public boolean isEndWord() {
                return endWord;
            }

            public void setEndWord(boolean endWord) {
                this.endWord = endWord;
            }
        }
    }
}
