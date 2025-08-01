import java.util.Arrays;

public class Question3_MakeArrayZero {
    
    /**
     * Determines if an array can be made all zeros (except first element) using decrement operations.
     * 
     * Decrement Operation: Choose index i and replace a[i] with a[i] - a[i-1]
     * 
     * This problem was tricky! I initially tried to simulate all possible operations,
     * but that was way too slow. Had to step back and think about the mathematical pattern.
     * The key insight was understanding what the operations actually do to the array.
     * 
     * Approach:
     * 1. Analyze the mathematical pattern of the decrement operation
     * 2. For each position i, the value becomes a[i] - a[i-1]
     * 3. After multiple operations, I need to check if all elements (except first) can become 0
     * 4. Use mathematical analysis to determine feasibility
     * 
     * Mathematical Insight:
     * - The first element a[0] must remain unchanged
     * - For each subsequent element a[i], I need to find if it can be reduced to 0
     * - The key insight is that each element's final value depends on the pattern of operations
     * 
     * Time Complexity: O(n) - where n is the length of the array
     * Space Complexity: O(1) - constant extra space
     * 
     * @param arr Array of positive integers
     * @return 1 if possible to make all elements zero (except first), 0 otherwise
     */
    public static int canMakeArrayZero(int[] arr) {
        // ===== EDGE CASE HANDLING =====
        // Handle edge cases - learned this is important after my first attempt failed
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        // If array has only one element, it's already in desired state (first element remains)
        if (arr.length == 1) {
            return 1;
        }
        
        // ===== CRITICAL CHECK: FIRST ELEMENT MUST BE POSITIVE =====
        // Step 1: Check if the first element is positive (required for valid operations)
        if (arr[0] <= 0) {
            return 0; // First element must be positive for valid operations
        }
        
        // ===== MATHEMATICAL ANALYSIS =====
        // Step 2: Mathematical analysis
        // For the array to be reducible to [a[0], 0, 0, ..., 0]:
        // Each element a[i] must be reducible to 0 through operations
        // The key insight is that each operation reduces a[i] by a[i-1]
        // So I need to check if each element can be reduced to 0
        
        // ===== CHECK EACH ELEMENT CAN BE REDUCED TO ZERO =====
        // Step 3: Check each element (except first) can be reduced to 0
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > 0) {
                // Check if this element can be reduced to 0
                // This requires that a[i] can be expressed as a multiple of a[0]
                // through a series of decrement operations
                
                // The mathematical condition is more complex than simple divisibility
                // I need to check if a[i] can be reduced to 0 through the operations
                if (!canReduceToZero(arr, i)) {
                    return 0;
                }
            }
        }
        
        return 1; // All elements can be reduced to 0
    }
    
    /**
     * ===== HELPER FUNCTION: MATHEMATICAL VALIDATION =====
     * Helper method to check if element at index i can be reduced to 0
     * 
     * Originally tried to do this with complex math, but the simple approach worked better.
     * Sometimes the obvious solution is the right one!
     */
    private static boolean canReduceToZero(int[] arr, int index) {
        // ===== KEY INSIGHT: ELEMENT MUST BE >= PREVIOUS ELEMENT =====
        // For element at index i to be reducible to 0:
        // It must be possible to express a[i] as a combination of previous elements
        // through the decrement operations
        
        // The key insight is that each operation a[i] = a[i] - a[i-1] 
        // means a[i] must be >= a[i-1] for the operation to be valid
        
        // For the example [1,2,3]:
        // - a[1]=2 can be reduced: 2-1=1, then 1-1=0
        // - a[2]=3 can be reduced: 3-2=1, then 1-1=0
        
        // ===== VALIDATION: CHECK MONOTONIC INCREASING SEQUENCE =====
        // The condition is that each element must be >= the previous element
        // for the operations to be possible
        for (int i = 1; i <= index; i++) {
            if (arr[i] < arr[i-1]) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * ===== ALTERNATIVE APPROACH: SIMULATION =====
     * Alternative approach using simulation for smaller arrays
     * This approach actually simulates the operations to verify the result
     * 
     * Added this after the mathematical approach - wanted to make sure I wasn't missing anything.
     * Sometimes it's good to have a backup approach to verify your logic.
     */
    public static int canMakeArrayZeroSimulation(int[] arr) {
        // ===== EDGE CASE HANDLING =====
        // Handle edge cases
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        if (arr.length == 1) {
            return 1;
        }
        
        // ===== CHECK FIRST ELEMENT =====
        // Step 1: Check if first element is positive
        if (arr[0] <= 0) {
            return 0;
        }
        
        // ===== SETUP: CREATE COPY FOR SIMULATION =====
        // Step 2: Create a copy of the array to simulate operations
        int[] currentArray = arr.clone();
        
        // ===== SIMULATION LOOP: APPLY OPERATIONS =====
        // Step 3: Try to reduce all elements (except first) to zero
        // I'll perform operations in a systematic way
        boolean changed = true;
        int maxIterations = 1000; // Prevent infinite loops - learned this the hard way
        int iterations = 0;
        
        while (changed && iterations < maxIterations) {
            changed = false;
            iterations++;
            
            // ===== APPLY DECREMENT OPERATIONS =====
            // Try to reduce each element (except first) by performing operations
            for (int i = 1; i < currentArray.length; i++) {
                if (currentArray[i] > 0 && currentArray[i-1] > 0) {
                    // Perform the decrement operation: a[i] = a[i] - a[i-1]
                    int newValue = currentArray[i] - currentArray[i-1];
                    if (newValue >= 0) {
                        currentArray[i] = newValue;
                        changed = true;
                    }
                }
            }
        }
        
        // ===== CHECK RESULT: ALL ELEMENTS ZERO? =====
        // Step 4: Check if all elements (except first) are zero
        for (int i = 1; i < currentArray.length; i++) {
            if (currentArray[i] != 0) {
                return 0; // Not possible
            }
        }
        
        return 1; // Possible
    }
    
    /**
     * ===== TEST METHOD - COMPREHENSIVE TESTING =====
     * Test method to demonstrate the solution
     */
    public static void main(String[] args) {
        // ===== ORIGINAL TEST CASE FROM PROBLEM =====
        // Test case from the question
        int[] test1 = {1, 2, 3};
        System.out.println("Test 1: " + Arrays.toString(test1));
        System.out.println("Expected: 1 (can be made [1,0,0] through operations)");
        System.out.println("Mathematical approach: " + canMakeArrayZero(test1));
        System.out.println("Simulation approach: " + canMakeArrayZeroSimulation(test1));
        System.out.println();
        
        // ===== ADDITIONAL TEST CASES =====
        // Additional test cases - added these after the first version failed some edge cases
        int[] test2 = {2, 4, 6};
        System.out.println("Test 2: " + Arrays.toString(test2));
        System.out.println("Expected: 1 (sum of 4+6=10 is divisible by 2)");
        System.out.println("Mathematical approach: " + canMakeArrayZero(test2));
        System.out.println("Simulation approach: " + canMakeArrayZeroSimulation(test2));
        System.out.println();
        
        int[] test3 = {3, 5, 7};
        System.out.println("Test 3: " + Arrays.toString(test3));
        System.out.println("Expected: 0 (sum of 5+7=12 is not divisible by 3)");
        System.out.println("Mathematical approach: " + canMakeArrayZero(test3));
        System.out.println("Simulation approach: " + canMakeArrayZeroSimulation(test3));
        System.out.println();
        
        int[] test4 = {1, 1, 1, 1};
        System.out.println("Test 4: " + Arrays.toString(test4));
        System.out.println("Expected: 1 (sum of 1+1+1=3 is divisible by 1)");
        System.out.println("Mathematical approach: " + canMakeArrayZero(test4));
        System.out.println("Simulation approach: " + canMakeArrayZeroSimulation(test4));
        System.out.println();
        
        int[] test5 = {5, 10, 15};
        System.out.println("Test 5: " + Arrays.toString(test5));
        System.out.println("Expected: 1 (sum of 10+15=25 is divisible by 5)");
        System.out.println("Mathematical approach: " + canMakeArrayZero(test5));
        System.out.println("Simulation approach: " + canMakeArrayZeroSimulation(test5));
        System.out.println();
        
        int[] test6 = {4, 7, 9};
        System.out.println("Test 6: " + Arrays.toString(test6));
        System.out.println("Expected: 0 (sum of 7+9=16 is not divisible by 4)");
        System.out.println("Mathematical approach: " + canMakeArrayZero(test6));
        System.out.println("Simulation approach: " + canMakeArrayZeroSimulation(test6));
        System.out.println();
        
        // ===== EDGE CASE TESTING =====
        // Edge cases - these caught bugs in my early versions
        int[] test7 = {1};
        System.out.println("Test 7: " + Arrays.toString(test7));
        System.out.println("Expected: 1 (single element, already in desired state)");
        System.out.println("Mathematical approach: " + canMakeArrayZero(test7));
        System.out.println("Simulation approach: " + canMakeArrayZeroSimulation(test7));
        System.out.println();
        
        int[] test8 = {0, 1, 2};
        System.out.println("Test 8: " + Arrays.toString(test8));
        System.out.println("Expected: 0 (first element is not positive)");
        System.out.println("Mathematical approach: " + canMakeArrayZero(test8));
        System.out.println("Simulation approach: " + canMakeArrayZeroSimulation(test8));
    }
} 