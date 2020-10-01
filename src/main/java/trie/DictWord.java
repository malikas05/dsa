package trie;

public class DictWord {

    public static void main(String[] args) {
        String dict[] = {"the", "hello", "there", "answer", "any", "Dragon",
                "world", "their", "inc"};
        System.out.println(isFormationPossible2(dict, "helloworld"));
    }

    /*
    Info: this would only work when we have startWith() method available. This approach is faster than the second one as
    we only traverse once
    - Complexity Analysis:
    Time complexity: O(N)
     */
    public static boolean isFormationPossible(String[] dict, String word) {
        if (dict.length < 2 || word.length() < 2) {
            return false;
        }

        Trie trie = new Trie();
        trie.insert(word);

        String first = "";
        for (String w : dict) {
            if (first.isEmpty() && trie.startsWith(w)) {
                first = w;
            } else if (!first.isEmpty()) {
                if (trie.search(first + w)) {
                    return true;
                }
            }
        }

        return false;
    }

    /*
    - Complexity Analysis:
    Time complexity: O(m + n^2) where m is the number of words in dict and n is the size of word
     */
    public static boolean isFormationPossible2(String[] dict, String word) {
        if (dict.length < 2 || word.length() < 2) {
            return false;
        }

        Trie trie = new Trie();
        for (String w : dict) {
            trie.insert(w);
        }

        for (int i = 0; i < word.length(); i ++) {
            String first = word.substring(0, i);
            String second = word.substring(i);

            if (trie.search(first) && trie.search(second))
                return true;
        }

        return false;
    }
}
