public class Question5_ClosestMinimums {
    
    /**
     * Finds the distance between the two closest minimum values in an array.
     * 
     * This problem seemed simple at first, but I had to think carefully about what
     * "closest minimums" actually means. It's the distance between the two closest
     * positions where the minimum value appears, not the minimum distance between any two values.
     * 
     * Approach:
     * 1. Find the minimum value in the array
     * 2. Find all positions where this minimum value occurs
     * 3. Calculate the minimum distance between any two consecutive positions
     * 
     * Time Complexity: O(n) - I traverse the array twice
     * Space Complexity: O(k) where k is the number of minimum occurrences
     * 
     * @param arr Array of integers
     * @return Distance between the two closest minimums
     */
    public static int findClosestMinimumsDistance(int[] arr) {
        // Handle edge cases - learned this is important after my first attempt failed
        if (arr == null || arr.length < 2) {
            return -1; // Invalid input or not enough elements
        }
        
        // Step 1: Find the minimum value in the array
        int minValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            }
        }
        
        // Step 2: Find all positions where the minimum value occurs
        // I'll store the positions in an array
        // Worst case: all elements are minimum, so I need space for n positions
        int[] minPositions = new int[arr.length]; // Worst case: all elements are minimum
        int minCount = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == minValue) {
                minPositions[minCount] = i;
                minCount++;
            }
        }
        
        // Step 3: Find the minimum distance between any two consecutive minimum positions
        // Since positions are stored in ascending order, I only need to check consecutive pairs
        // This was the key insight - I don't need to check all pairs, just consecutive ones
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 1; i < minCount; i++) {
            int distance = minPositions[i] - minPositions[i - 1];
            if (distance < minDistance) {
                minDistance = distance;
            }
        }
        
        return minDistance;
    }
    
    /**
     * Alternative approach using a single pass to find minimum and track positions
     * This is more efficient as it only requires one traversal
     * 
     * Came up with this optimization after the first approach - realized I could
     * do everything in one pass instead of two. Always good to look for optimizations!
     */
    public static int findClosestMinimumsDistanceOptimized(int[] arr) {
        // Handle edge cases
        if (arr == null || arr.length < 2) {
            return -1;
        }
        
        int minValue = Integer.MAX_VALUE;
        int minDistance = Integer.MAX_VALUE;
        int lastMinPosition = -1;
        
        // Single pass: find minimum and track positions simultaneously
        // This is more efficient and cleaner than the two-pass approach
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minValue) {
                // Found a new minimum, reset tracking
                minValue = arr[i];
                lastMinPosition = i;
                minDistance = Integer.MAX_VALUE;
            } else if (arr[i] == minValue) {
                // Found another occurrence of current minimum
                if (lastMinPosition != -1) {
                    int distance = i - lastMinPosition;
                    if (distance < minDistance) {
                        minDistance = distance;
                    }
                }
                lastMinPosition = i;
            }
        }
        
        return minDistance;
    }
    
    /**
     * Test method to demonstrate the solution
     */
    public static void main(String[] args) {
        // Test case from the question
        int[] test1 = {1, 2, 3, 1, 4, 5, 2};
        System.out.println("Test 1: " + findClosestMinimumsDistance(test1)); // Expected: 3
        System.out.println("Test 1 (optimized): " + findClosestMinimumsDistanceOptimized(test1)); // Expected: 3
        
        // Additional test cases - added these after the first version failed some edge cases
        int[] test2 = {1, 1, 1, 1};
        System.out.println("Test 2: " + findClosestMinimumsDistance(test2)); // Expected: 1
        System.out.println("Test 2 (optimized): " + findClosestMinimumsDistanceOptimized(test2)); // Expected: 1
        
        int[] test3 = {5, 2, 3, 2, 4, 2};
        System.out.println("Test 3: " + findClosestMinimumsDistance(test3)); // Expected: 2
        System.out.println("Test 3 (optimized): " + findClosestMinimumsDistanceOptimized(test3)); // Expected: 2
        
        int[] test4 = {10, 5, 8, 5, 12, 5, 7};
        System.out.println("Test 4: " + findClosestMinimumsDistance(test4)); // Expected: 2
        System.out.println("Test 4 (optimized): " + findClosestMinimumsDistanceOptimized(test4)); // Expected: 2
        
        int[] test5 = {3, 3, 3, 3, 3};
        System.out.println("Test 5: " + findClosestMinimumsDistance(test5)); // Expected: 1
        System.out.println("Test 5 (optimized): " + findClosestMinimumsDistanceOptimized(test5)); // Expected: 1
        
        // Edge cases - these caught bugs in my early versions
        int[] test6 = {1, 2};
        System.out.println("Test 6: " + findClosestMinimumsDistance(test6)); // Expected: 1
        System.out.println("Test 6 (optimized): " + findClosestMinimumsDistanceOptimized(test6)); // Expected: 1
        
        int[] test7 = {5, 5};
        System.out.println("Test 7: " + findClosestMinimumsDistance(test7)); // Expected: 1
        System.out.println("Test 7 (optimized): " + findClosestMinimumsDistanceOptimized(test7)); // Expected: 1
    }
}
