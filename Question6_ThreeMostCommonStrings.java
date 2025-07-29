import java.util.*;

public class Question6_ThreeMostCommonStrings {
    
    /**
     * Finds the three most common strings in a sentence.
     * 
     * Approach:
     * 1. Split the sentence into individual words
     * 2. Count the frequency of each word using HashMap
     * 3. Sort words by frequency (descending) and then alphabetically (ascending)
     * 4. Return the top 3 most common words
     * 
     * Time Complexity: O(n log n) - dominated by sorting
     * Space Complexity: O(n) - HashMap stores all unique words
     * 
     * @param sentence Input sentence as a string
     * @return Array of the three most common strings in ascending alphabetical order
     */
    public static String[] findThreeMostCommonStrings(String sentence) {
        // Handle edge cases
        if (sentence == null || sentence.trim().isEmpty()) {
            return new String[0];
        }
        
        // Step 1: Split the sentence into words and clean them
        // Remove extra spaces and split by whitespace
        String[] words = sentence.trim().split("\\s+");
        
        // Step 2: Count the frequency of each word using HashMap
        Map<String, Integer> wordFrequency = new HashMap<>();
        
        for (String word : words) {
            // Convert to lowercase for consistent counting (optional, based on requirements)
            String cleanWord = word.toLowerCase().trim();
            if (!cleanWord.isEmpty()) {
                wordFrequency.put(cleanWord, wordFrequency.getOrDefault(cleanWord, 0) + 1);
            }
        }
        
        // Step 3: Create a list of word-frequency pairs for sorting
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordFrequency.entrySet());
        
        // Step 4: Sort by frequency (descending) and then alphabetically (ascending)
        Collections.sort(wordList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                // First compare by frequency (descending order)
                int freqCompare = Integer.compare(b.getValue(), a.getValue());
                if (freqCompare != 0) {
                    return freqCompare;
                }
                // If frequencies are equal, sort alphabetically (ascending order)
                return a.getKey().compareTo(b.getKey());
            }
        });
        
        // Step 5: Extract the top 3 most common words
        int resultSize = Math.min(3, wordList.size());
        String[] result = new String[resultSize];
        
        for (int i = 0; i < resultSize; i++) {
            result[i] = wordList.get(i).getKey();
        }
        
        return result;
    }
    
    /**
     * Alternative approach using PriorityQueue for better performance with large datasets
     */
    public static String[] findThreeMostCommonStringsOptimized(String sentence) {
        // Handle edge cases
        if (sentence == null || sentence.trim().isEmpty()) {
            return new String[0];
        }
        
        // Step 1: Split and count words
        String[] words = sentence.trim().split("\\s+");
        Map<String, Integer> wordFrequency = new HashMap<>();
        
        for (String word : words) {
            String cleanWord = word.toLowerCase().trim();
            if (!cleanWord.isEmpty()) {
                wordFrequency.put(cleanWord, wordFrequency.getOrDefault(cleanWord, 0) + 1);
            }
        }
        
        // Step 2: Use PriorityQueue to get top 3 most frequent words
        // Custom comparator: frequency descending, then alphabetical ascending
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
            (a, b) -> {
                int freqCompare = Integer.compare(b.getValue(), a.getValue());
                return freqCompare != 0 ? freqCompare : a.getKey().compareTo(b.getKey());
            }
        );
        
        // Add all entries to priority queue
        pq.addAll(wordFrequency.entrySet());
        
        // Step 3: Extract top 3 results
        int resultSize = Math.min(3, pq.size());
        String[] result = new String[resultSize];
        
        for (int i = 0; i < resultSize; i++) {
            result[i] = pq.poll().getKey();
        }
        
        return result;
    }
    
    /**
     * Test method to demonstrate the solution
     */
    public static void main(String[] args) {
        // Test case from the question
        String test1 = "hi there care to discuss algorithm basis or how to solve algorithm or";
        System.out.println("Test 1 Input: \"" + test1 + "\"");
        String[] result1 = findThreeMostCommonStrings(test1);
        System.out.println("Test 1 Output: " + Arrays.toString(result1)); // Expected: ["algorithm", "or", "to"]
        System.out.println("Test 1 (optimized): " + Arrays.toString(findThreeMostCommonStringsOptimized(test1)));
        System.out.println();
        
        // Additional test cases
        String test2 = "the quick brown fox jumps over the lazy dog the fox";
        System.out.println("Test 2 Input: \"" + test2 + "\"");
        String[] result2 = findThreeMostCommonStrings(test2);
        System.out.println("Test 2 Output: " + Arrays.toString(result2)); // Expected: ["the", "fox", "quick"]
        System.out.println();
        
        String test3 = "a a a b b c";
        System.out.println("Test 3 Input: \"" + test3 + "\"");
        String[] result3 = findThreeMostCommonStrings(test3);
        System.out.println("Test 3 Output: " + Arrays.toString(result3)); // Expected: ["a", "b", "c"]
        System.out.println();
        
        String test4 = "hello world";
        System.out.println("Test 4 Input: \"" + test4 + "\"");
        String[] result4 = findThreeMostCommonStrings(test4);
        System.out.println("Test 4 Output: " + Arrays.toString(result4)); // Expected: ["hello", "world"]
        System.out.println();
        
        String test5 = "";
        System.out.println("Test 5 Input: \"" + test5 + "\"");
        String[] result5 = findThreeMostCommonStrings(test5);
        System.out.println("Test 5 Output: " + Arrays.toString(result5)); // Expected: []
        System.out.println();
        
        // Test with case sensitivity and special characters
        String test6 = "Algorithm ALGORITHM algorithm or OR to TO";
        System.out.println("Test 6 Input: \"" + test6 + "\"");
        String[] result6 = findThreeMostCommonStrings(test6);
        System.out.println("Test 6 Output: " + Arrays.toString(result6)); // Expected: ["algorithm", "or", "to"]
    }
}
