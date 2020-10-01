package trie;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

/*
https://leetcode.com/problems/add-and-search-word-data-structure-design/
 */
public class WordDictionary {

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();

//        wordDictionary.addWord("bad");
//        wordDictionary.addWord("dad");
//        wordDictionary.addWord("mad");
//        boolean result = wordDictionary.search("pad"); // false
//        result = wordDictionary.search("bad"); // true
//        result = wordDictionary.search(".ad"); // true
//        result = wordDictionary.search("b.."); // true

//        wordDictionary.addWord("a");
//        wordDictionary.addWord("a");
//        boolean result = wordDictionary.search(".");
//        result = wordDictionary.search("a");
//        result = wordDictionary.search("aa");
//        result = wordDictionary.search("a");
//        result = wordDictionary.search(".a");
//        result = wordDictionary.search("a.");

//        wordDictionary.addWord("at");
//        wordDictionary.addWord("and");
//        wordDictionary.addWord("an");
//        wordDictionary.addWord("add");
//        boolean result = wordDictionary.search("a"); // false
//        result = wordDictionary.search(".at"); // false
//        wordDictionary.addWord("bat");
//        result = wordDictionary.search(".at"); // true
//        result = wordDictionary.search("an."); // true
//        result = wordDictionary.search("a.d."); // false
//        result = wordDictionary.search("b."); // false
//        result = wordDictionary.search("a.d"); // true
//        result = wordDictionary.search("."); // false

        wordDictionary.addWord("ran");
        wordDictionary.addWord("rune");
        wordDictionary.addWord("runner");
        wordDictionary.addWord("runs");
        wordDictionary.addWord("add");
        wordDictionary.addWord("adds");
        wordDictionary.addWord("adder");
        wordDictionary.addWord("addee");
        boolean result = wordDictionary.search("r.n");
        result = wordDictionary.search("ru.n.e");
        result = wordDictionary.search("add");
        result = wordDictionary.search("adde.");
        result = wordDictionary.search(".an.");
        result = wordDictionary.search("...s"); // true
        result = wordDictionary.search("....e.");
        result = wordDictionary.search("......."); // false
        result = wordDictionary.search("..n.r");
    }

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        this.root = new TrieNode();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        if (Objects.isNull(word) || word.length() == 0) {
            return;
        }

        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(c, (k) -> new TrieNode());
        }

        current.setEndWord(true);
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        if (root.getChildren().isEmpty() || Objects.isNull(word) || word.length() == 0) {
            return false;
        }

        return checkPatternRecursive(word.toCharArray(), 0, root);
    }

    /*
    - Concept: Recursive approach.
    - Complexity Analysis:
    Time complexity: O(N)
    Space complexity: O(N)
     */
    private boolean checkPatternRecursive(char[] chs, int k, TrieNode node) {

        if (chs.length == k) { // base case when we reach the last char in string
            return node.isEndWord();
        }

        if (chs[k] == '.') {
            for (Character curr : node.getChildren().keySet()) {
                if (node.getChildren().get(curr) != null && checkPatternRecursive(chs, k + 1, node.getChildren().get(curr)))
                    return true;
            }
        } else {
            return node.getChildren().get(chs[k]) != null && checkPatternRecursive(chs, k + 1, node.getChildren().get(chs[k]));
        }

        return false;
    }

    private boolean checkPatternRecursive(String pattern, TrieNode node) {
        char curChar = pattern.substring(0, 1).charAt(0);

        if (pattern.length() == 1) { // base case when we reach the last char in string
            if (node.getChildren().containsKey(curChar)) { // when the char is in trie
                return node.getChildren().get(curChar).isEndWord();
            } else if (checkDot(curChar)) { // when the char is dot, we have to ensure that the trie at the . index is the end of word
                return !node.getChildren().values().isEmpty() && !node.getChildren().values().stream().filter(TrieNode::isEndWord).collect(Collectors.toList()).isEmpty();
            }
        } else if (!node.getChildren().containsKey(curChar)) { // case when the char is not in trie at this location
            if (checkDot(curChar)) { // when the char is dot, we have to traverse through the children nodes to check for any matched pattern
                for (TrieNode child : node.getChildren().values()) {
                    if (checkPatternRecursive(pattern.substring(1), child))
                        return true;
                }
            } else // is not dot => pattern is not found
                return false;
        } else // check next char in word
            return checkPatternRecursive(pattern.substring(1), node.getChildren().get(curChar));

        return false;
    }

    private boolean checkDot(char c) {
        return c == '.';
    }

    private static class TrieNode {

        private HashMap<Character, TrieNode> children;
        private boolean isEndWord;

        public TrieNode() {
            children = new HashMap<>();
        }

        public HashMap<Character, TrieNode> getChildren() {
            return children;
        }

        public boolean isEndWord() {
            return isEndWord;
        }

        public void setEndWord(boolean endWord) {
            isEndWord = endWord;
        }
    }

}
