import java.util.*;

public class Boggle {
    public static void main(String[] args) {
        char[][] grid = {{'c', 'a', 't'}, {'r', 'r', 'e'}, {'t', 'o', 'n'}};
        HashSet<String> dictionary = new HashSet<>();
        dictionary.add("cat");
        dictionary.add("cater");
        dictionary.add("cartoon");
        dictionary.add("toon");
        dictionary.add("moon");
        dictionary.add("not");
        dictionary.add("tone");
        dictionary.add("apple");
        dictionary.add("ton");
        dictionary.add("art");

        Boggle boggle = new Boggle(grid, dictionary);
        HashSet<String> result = boggle.findAllWords();
        System.out.println(result.toString());
    }

    private char[][] grid;
    private Set<String> dictionary;
    private boolean[][] state;

    Boggle(char[][] grid, HashSet<String> dictionary) {
        this.grid = grid;
        this.dictionary = dictionary;
        this.state = new boolean[grid.length][grid[0].length];
    }

    public HashSet<String> findAllWords() {
        if (grid == null || dictionary == null)
            return null;

        HashSet<String> result = new HashSet<>();
        StringBuilder currentWord = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                findAllWordsRec(i, j, currentWord, result);
            }
        }

        return result;
    }

    private List<Pair> findAllNeighbors(int row, int col) {
        List<Pair> neighbors = new ArrayList<>();

        int startX = Math.max(0, row - 1);
        int startY = Math.max(0, col - 1);
        int endX = Math.min(grid.length - 1, row + 1);
        int endY = Math.min(grid[0].length - 1, col + 1);

        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                if (state[i][j])
                    continue;

                neighbors.add(new Pair(i, j));
            }
        }

        return neighbors;
    }

    private void findAllWordsRec(int row, int col, StringBuilder currentWord, HashSet<String> result) {
        if (currentWord.length() > 0 && dictionary.contains(currentWord.toString()))
            result.add(currentWord.toString());

        List<Pair> neighbors = findAllNeighbors(row, col);
        for (Pair pair : neighbors) {
            currentWord.append(grid[pair.row][pair.col]);
            state[pair.row][pair.col] = true;
            findAllWordsRec(pair.row, pair.col, currentWord, result);
            currentWord.setLength(currentWord.length() - 1);
            state[pair.row][pair.col] = false;
        }
    }

    private static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
