import java.util.*;
import java.util.stream.Collectors;

/*
https://leetcode.com/problems/top-k-frequent-words/
OR
https://leetcode.com/discuss/interview-question/542597/
Note: This question is taken from Amazon coding interview. The leetcode question above resembles the underlying logic.
 */
public class MostFrequentFeature {

    public static void main(String[] args) {
        System.out.println(popularNFeatures(6, 3, Arrays.asList("storage", "battery", "hover", "alexa", "waterproof", "solar"), 7,
                Arrays.asList("I wish my kindle had even more storage storage battery", "I wish the battery life on my Kindle lasted 2 years", "I read in the bath and would enjoy a waterproof Kindle",
                        "Waterproof and increased battery are my top two features", "I want to take my Kindle into the shower. Waterproof please waterproof!", "It would be neat if my Kindle would hover on my desk when not in use.",
                        "How cool would it be if my Kindle charged in the sun via solar power?")));

        System.out.println(topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
    }

    /*
    - Complexity Analysis:
    Time complexity: O(N log (K)) - N is the length of numFeatureRequests or the number of iterations to count the occurrences of each feature.
    K is the number of possibleNumFeatures. Log(K) is used to build the heap.
    Space complexity: O(N)
     */
    public static ArrayList<String> popularNFeatures(int numFeatures,
                                                     int topFeatures,
                                                     List<String> possibleFeatures,
                                                     int numFeatureRequests,
                                                     List<String> featureRequests) {
        ArrayList<String> result = new ArrayList<>();
        Map<String, Integer> possibleFeaturesCount = new HashMap<>();
        Set<String> visitedFeatures = new HashSet<>();

        for (String possibleFeature : possibleFeatures) {
            possibleFeaturesCount.putIfAbsent(possibleFeature.toLowerCase(), 0);
        }

        for (String featureRequest : featureRequests) {
            String[] featureSplitByWords = featureRequest.split(" ");
            for (String featureSingleWord : featureSplitByWords) {
                if (!visitedFeatures.contains(featureSingleWord)) {
                    visitedFeatures.add(featureSingleWord);
                    possibleFeaturesCount.computeIfPresent(featureSingleWord.toLowerCase(), (key, val) -> ++val);
                }
            }
            visitedFeatures.clear();
        }

        if (topFeatures > numFeatures) {
            possibleFeaturesCount = possibleFeaturesCount.entrySet().stream().filter(entry -> entry.getValue() > 0).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            topFeatures = possibleFeaturesCount.size();
        }

        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>((a, b) -> a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue().compareTo(a.getValue()));
        heap.addAll(possibleFeaturesCount.entrySet());

        while (!heap.isEmpty() && topFeatures-- > 0) {
            result.add(heap.poll().getKey());
        }

        return result;
    }

    /*
    https://leetcode.com/problems/top-k-frequent-words/
    - Complexity Analysis:
    Time complexity: O(N log (K))
    Space complexity: O(N)
     */
    public static ArrayList<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordCounts = new HashMap<>();

        for (String word : words) {
            wordCounts.compute(word.toLowerCase(), (key, value) -> Objects.isNull(value) ? 1 : ++value);
        }

        wordCounts = wordCounts.entrySet()
                .stream()
                .sorted((a, b) -> a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue().compareTo(a.getValue()))
                .limit(k)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

//      OR

//        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>((a, b) -> a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue().compareTo(a.getValue()));
//        heap.addAll(wordCounts.entrySet());
//
//        while (!heap.isEmpty() && k-- > 0) {
//            result.add(heap.poll().getKey());
//        }

        return wordCounts.keySet().stream().collect(Collectors.toCollection(ArrayList::new));
    }
}
