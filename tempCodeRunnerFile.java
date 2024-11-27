import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCountMapReduce {

    public static void main(String[] args) {
        String text = "Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua";

        // Split the text into words and create a list of words
        List<String> words = Arrays.asList(text.split(" "));

        // Step 1: Map - Convert the list of words into a map of word counts
        Map<String, Integer> wordCounts = words.stream()
                .map(word -> word.toLowerCase()) // Convert to lowercase for case-insensitive word count
                .collect(HashMap::new,
                        (map, word) -> map.put(word, map.getOrDefault(word, 0) + 1),
                        HashMap::putAll);

        // Step 2: Reduce - Combine the counts for each word
        Map<String, Integer> finalWordCounts = wordCounts.entrySet().stream()
                .collect(HashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        HashMap::putAll);

        // Print the word counts
        finalWordCounts.forEach((word, count) -> System.out.println(word + ": " + count));
    }
}
