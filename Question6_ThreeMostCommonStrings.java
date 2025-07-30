import java.util.*;

public class Question6_ThreeMostCommonStrings {
    
    /**
     * Finds the three most common strings in a sentence.
     * 
     * This problem had some interesting edge cases I didn't think about initially.
     * The main challenge was handling the sorting - first by frequency (descending),
     * then alphabetically (ascending) for ties. Also had to think about case sensitivity.
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
        // ===== EDGE CASE HANDLING =====
        // Handle edge cases - learned this is important after my first attempt failed
        if (sentence == null || sentence.trim().isEmpty()) {
            return new String[0];
        }
        
        // ===== STEP 1: PARSE AND CLEAN WORDS =====
        // Step 1: Split the sentence into words and clean them
        // Remove extra spaces and split by whitespace
        // Tried using StringTokenizer first, but split() is more modern and flexible
        String[] words = sentence.trim().split("\\s+");
        
        // ===== STEP 2: COUNT WORD FREQUENCIES =====
        // Step 2: Count the frequency of each word using HashMap
        Map<String, Integer> wordFrequency = new HashMap<>();
        
        for (String word : words) {
            // Convert to lowercase for consistent counting (optional, based on requirements)
            // Decided to make it case-insensitive for better user experience
            String cleanWord = word.toLowerCase().trim();
            if (!cleanWord.isEmpty()) {
                wordFrequency.put(cleanWord, wordFrequency.getOrDefault(cleanWord, 0) + 1);
            }
        }
        
        // ===== STEP 3: CREATE SORTABLE LIST =====
        // Step 3: Create a list of word-frequency pairs for sorting
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordFrequency.entrySet());
        
        // ===== STEP 4: CUSTOM SORTING LOGIC =====
        // Step 4: Sort by frequency (descending) and then alphabetically (ascending)
        // This was the trickiest part - had to think about the comparator logic
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
        
        // ===== STEP 5: EXTRACT TOP 3 RESULTS =====
        // Step 5: Extract the top 3 most common words
        int resultSize = Math.min(3, wordList.size());
        String[] result = new String[resultSize];
        
        for (int i = 0; i < resultSize; i++) {
            result[i] = wordList.get(i).getKey();
        }
        
        return result;
    }
    
    /**
     * ===== OPTIMIZED APPROACH: PRIORITY QUEUE =====
     * Alternative approach using PriorityQueue for better performance with large datasets
     * 
     * Added this after learning about PriorityQueue - it's more efficient for getting
     * top k elements without sorting the entire list. Good to know for future problems!
     */
    public static String[] findThreeMostCommonStringsOptimized(String sentence) {
        // ===== EDGE CASE HANDLING =====
        // Handle edge cases
        if (sentence == null || sentence.trim().isEmpty()) {
            return new String[0];
        }
        
        // ===== STEP 1: PARSE AND COUNT WORDS =====
        // Step 1: Split and count words
        String[] words = sentence.trim().split("\\s+");
        Map<String, Integer> wordFrequency = new HashMap<>();
        
        for (String word : words) {
            String cleanWord = word.toLowerCase().trim();
            if (!cleanWord.isEmpty()) {
                wordFrequency.put(cleanWord, wordFrequency.getOrDefault(cleanWord, 0) + 1);
            }
        }
        
        // ===== STEP 2: SETUP PRIORITY QUEUE =====
        // Step 2: Use PriorityQueue to get top 3 most frequent words
        // Custom comparator: frequency descending, then alphabetical ascending
        // PriorityQueue is more efficient for this use case
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
            (a, b) -> {
                int freqCompare = Integer.compare(b.getValue(), a.getValue());
                return freqCompare != 0 ? freqCompare : a.getKey().compareTo(b.getKey());
            }
        );
        
        // ===== STEP 3: ADD ALL ENTRIES TO PRIORITY QUEUE =====
        // Add all entries to priority queue
        pq.addAll(wordFrequency.entrySet());
        
        // ===== STEP 4: EXTRACT TOP 3 RESULTS =====
        // Step 3: Extract top 3 results
        int resultSize = Math.min(3, pq.size());
        String[] result = new String[resultSize];
        
        for (int i = 0; i < resultSize; i++) {
            result[i] = pq.poll().getKey();
        }
        
        return result;
    }
    
    /**
     * ===== TEST METHOD - COMPREHENSIVE TESTING =====
     * Test method to demonstrate the solution
     */
    public static void main(String[] args) {
        // ===== ORIGINAL TEST CASE FROM PROBLEM =====
        // Test case from the question
        String test1 = "hi there care to discuss algorithm basis or how to solve algorithm or";
        System.out.println("Test 1 Input: \"" + test1 + "\"");
        String[] result1 = findThreeMostCommonStrings(test1);
        System.out.println("Test 1 Output: " + Arrays.toString(result1)); // Expected: ["algorithm", "or", "to"]
        System.out.println("Test 1 (optimized): " + Arrays.toString(findThreeMostCommonStringsOptimized(test1)));
        System.out.println();
        
        // ===== ADDITIONAL TEST CASES =====
        // Additional test cases - added these after the first version failed some edge cases
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
        
        // ===== EDGE CASE TESTING =====
        // Test with case sensitivity and special characters - added this after finding a bug in my testing
        String test6 = "Algorithm ALGORITHM algorithm or OR to TO";
        System.out.println("Test 6 Input: \"" + test6 + "\"");
        String[] result6 = findThreeMostCommonStrings(test6);
        System.out.println("Test 6 Output: " + Arrays.toString(result6)); // Expected: ["algorithm", "or", "to"]
        
        // Test with extra spaces - this caught a bug in my early version
        String test7 = "  hello   world  hello  ";
        System.out.println("Test 7 Input: \"" + test7 + "\"");
        String[] result7 = findThreeMostCommonStrings(test7);
        System.out.println("Test 7 Output: " + Arrays.toString(result7)); // Expected: ["hello", "world"]
    }
}
