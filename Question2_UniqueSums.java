import java.util.*;

public class Question2_UniqueSums {
    
    /**
     * Finds the number of unique ways to sum array elements to match the target result.
     * 
     * I started with a simple recursive approach but quickly realized it was generating
     * duplicates. Had to think about how to handle combinations like [1,2,3,4] vs [4,3,2,1].
     * Sorting the array first was the key insight that made this work.
     * 
     * Approach:
     * 1. Use backtracking/recursion to find all possible combinations
     * 2. For each combination, check if the sum equals the target
     * 3. Store unique combinations in a Set to avoid duplicates
     * 4. Return the count of unique combinations
     * 
     * Time Complexity: O(2^n) - exponential due to all possible combinations
     * Space Complexity: O(n) - recursion stack depth
     * 
     * @param result Target sum to achieve
     * @param numbers Array of numbers to use
     * @return Number of unique ways to sum to the target
     */
    public static int findUniqueSums(int result, int[] numbers) {
        // Handle edge cases - learned this is crucial after my first attempt crashed
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        
        // Step 1: Sort the array to help with duplicate detection
        // This ensures that combinations like [1,2,3,4] and [4,3,2,1] are treated as the same
        // Tried using a custom comparator first, but sorting was much simpler
        Arrays.sort(numbers);
        
        // Step 2: Use a Set to store unique combinations
        // I'll store combinations as sorted lists to avoid duplicates
        Set<List<Integer>> uniqueCombinations = new HashSet<>();
        
        // Step 3: Use backtracking to find all valid combinations
        findCombinations(numbers, result, 0, new ArrayList<>(), 0, uniqueCombinations);
        
        // Step 4: Return the count of unique combinations
        return uniqueCombinations.size();
    }
    
    /**
     * Recursive helper method to find all valid combinations that sum to target
     * 
     * Originally tried to do this with a single method, but it got too complex.
     * Breaking it into a helper method made the logic much clearer.
     * 
     * @param numbers Original array of numbers
     * @param target Target sum to achieve
     * @param index Current index in the array
     * @param currentCombination Current combination being built
     * @param currentSum Current sum of the combination
     * @param uniqueCombinations Set to store unique combinations
     */
    private static void findCombinations(int[] numbers, int target, int index, 
                                       List<Integer> currentCombination, int currentSum,
                                       Set<List<Integer>> uniqueCombinations) {
        
        // Base case 1: If current sum equals target, I found a valid combination
        if (currentSum == target) {
            // Create a new sorted list to avoid reference issues
            // Had a bug here once where I was adding the same list reference multiple times
            List<Integer> validCombination = new ArrayList<>(currentCombination);
            Collections.sort(validCombination);
            uniqueCombinations.add(validCombination);
            return;
        }
        
        // Base case 2: If current sum exceeds target, backtrack
        if (currentSum > target) {
            return;
        }
        
        // Base case 3: If I've processed all numbers, backtrack
        if (index >= numbers.length) {
            return;
        }
        
        // Recursive case: Try including the current number
        currentCombination.add(numbers[index]);
        findCombinations(numbers, target, index + 1, currentCombination, 
                        currentSum + numbers[index], uniqueCombinations);
        
        // Backtrack: Remove the current number and try without it
        // This was the trickiest part to get right - had to remember to remove the last element
        currentCombination.remove(currentCombination.size() - 1);
        findCombinations(numbers, target, index + 1, currentCombination, 
                        currentSum, uniqueCombinations);
    }
    
    /**
     * Alternative approach using dynamic programming for better performance
     * This approach is more efficient for larger arrays and smaller target values
     * 
     * Learned about this approach after the recursive solution was too slow for larger inputs.
     * DP is definitely more complex to understand but much faster in practice.
     */
    public static int findUniqueSumsDP(int result, int[] numbers) {
        // Handle edge cases
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        
        // Step 1: Sort the array
        Arrays.sort(numbers);
        
        // Step 2: Use dynamic programming approach
        // dp[i][j] represents number of ways to sum to j using first i elements
        // Took me a while to understand this DP table structure
        int n = numbers.length;
        int[][] dp = new int[n + 1][result + 1];
        
        // Initialize: one way to sum to 0 (empty subset)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        
        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= result; j++) {
                // Don't include current element
                dp[i][j] = dp[i - 1][j];
                
                // Include current element if it doesn't exceed target
                if (numbers[i - 1] <= j) {
                    dp[i][j] += dp[i - 1][j - numbers[i - 1]];
                }
            }
        }
        
        return dp[n][result];
    }
    
    /**
     * Test method to demonstrate the solution
     */
    public static void main(String[] args) {
        // Test case 1 from the question
        int result1 = 10;
        int[] numbers1 = {1, 2, 3, 4, 5};
        System.out.println("Test 1: result = " + result1 + ", numbers = " + Arrays.toString(numbers1));
        System.out.println("Expected: 3 (1+2+3+4=10, 1+4+5=10, 2+3+5=10)");
        System.out.println("Actual: " + findUniqueSums(result1, numbers1));
        System.out.println("DP approach: " + findUniqueSumsDP(result1, numbers1));
        System.out.println();
        
        // Test case 2 from the question
        int result2 = 17;
        int[] numbers2 = {1, 2, 4, 7, 5};
        System.out.println("Test 2: result = " + result2 + ", numbers = " + Arrays.toString(numbers2));
        System.out.println("Expected: 1 (1+4+5+7=17)");
        System.out.println("Actual: " + findUniqueSums(result2, numbers2));
        System.out.println("DP approach: " + findUniqueSumsDP(result2, numbers2));
        System.out.println();
        
        // Additional test cases - added these after finding edge cases that broke my first version
        int result3 = 5;
        int[] numbers3 = {1, 2, 3};
        System.out.println("Test 3: result = " + result3 + ", numbers = " + Arrays.toString(numbers3));
        System.out.println("Expected: 2 (2+3=5, 1+4=5 but 4 not in array, so just 2+3)");
        System.out.println("Actual: " + findUniqueSums(result3, numbers3));
        System.out.println("DP approach: " + findUniqueSumsDP(result3, numbers3));
        System.out.println();
        
        int result4 = 0;
        int[] numbers4 = {1, 2, 3};
        System.out.println("Test 4: result = " + result4 + ", numbers = " + Arrays.toString(numbers4));
        System.out.println("Expected: 1 (empty subset sums to 0)");
        System.out.println("Actual: " + findUniqueSums(result4, numbers4));
        System.out.println("DP approach: " + findUniqueSumsDP(result4, numbers4));
        System.out.println();
        
        int result5 = 10;
        int[] numbers5 = {5, 5, 5};
        System.out.println("Test 5: result = " + result5 + ", numbers = " + Arrays.toString(numbers5));
        System.out.println("Expected: 1 (5+5=10)");
        System.out.println("Actual: " + findUniqueSums(result5, numbers5));
        System.out.println("DP approach: " + findUniqueSumsDP(result5, numbers5));
        System.out.println();
        
        // Test with empty array - this caught a null pointer exception in early version
        int result6 = 5;
        int[] numbers6 = {};
        System.out.println("Test 6: result = " + result6 + ", numbers = " + Arrays.toString(numbers6));
        System.out.println("Expected: 0 (no numbers to use)");
        System.out.println("Actual: " + findUniqueSums(result6, numbers6));
        System.out.println("DP approach: " + findUniqueSumsDP(result6, numbers6));
    }
}
