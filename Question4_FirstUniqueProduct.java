import java.util.HashMap;
import java.util.Map;

public class Question4_FirstUniqueProduct {
    
    /**
     * Finds the first product in an array that occurs only once.
     * 
     * This one was actually pretty straightforward once I understood the requirements.
     * The key was realizing that "first" means the first occurrence in the original array order,
     * not the first alphabetically or by any other criteria.
     * 
     * Approach:
     * 1. First pass: Count occurrences of each product using HashMap
     * 2. Second pass: Find the first product with count == 1
     * 
     * Time Complexity: O(n) - I traverse the array twice
     * Space Complexity: O(n) - HashMap stores at most n unique products
     * 
     * @param products Array of product names
     * @return First unique product, or null if no unique products exist
     */
    public static String firstUniqueProduct(String[] products) {
        // ===== EDGE CASE HANDLING =====
        // Handle edge cases - learned this is important after my first attempt failed
        if (products == null || products.length == 0) {
            return null;
        }
        
        // ===== FIRST PASS: COUNT OCCURRENCES =====
        // Step 1: Count occurrences of each product
        // Using HashMap for O(1) average time complexity for insert and lookup
        // Tried using a TreeMap first, but HashMap is faster
        Map<String, Integer> productCount = new HashMap<>();
        
        for (String product : products) {
            // Increment count for each product, defaulting to 0 if not present
            // getOrDefault is cleaner than checking containsKey first
            productCount.put(product, productCount.getOrDefault(product, 0) + 1);
        }
        
        // ===== SECOND PASS: FIND FIRST UNIQUE =====
        // Step 2: Find the first product with count == 1
        // I need to iterate in the original order to find the "first" unique product
        // This is the key insight - order matters for "first"
        for (String product : products) {
            // Check if this product appears exactly once
            if (productCount.get(product) == 1) {
                return product; // Found the first unique product
            }
        }
        
        // ===== NO UNIQUE PRODUCTS FOUND =====
        // No unique products found
        return null;
    }
    
    /**
     * ===== TEST METHOD - COMPREHENSIVE TESTING =====
     * Test method to demonstrate the solution
     */
    public static void main(String[] args) {
        // ===== ORIGINAL TEST CASE FROM PROBLEM =====
        // Test case from the question
        String[] test1 = {"Apple", "Computer", "Apple", "Bag"};
        System.out.println("Test 1: " + firstUniqueProduct(test1)); // Expected: "Computer"
        
        // ===== EDGE CASE TESTING =====
        // Additional test cases - added these after the first version failed some edge cases
        String[] test2 = {"A", "B", "C", "A", "B"};
        System.out.println("Test 2: " + firstUniqueProduct(test2)); // Expected: "C"
        
        String[] test3 = {"A", "A", "A"};
        System.out.println("Test 3: " + firstUniqueProduct(test3)); // Expected: null
        
        String[] test4 = {"Single"};
        System.out.println("Test 4: " + firstUniqueProduct(test4)); // Expected: "Single"
        
        String[] test5 = {};
        System.out.println("Test 5: " + firstUniqueProduct(test5)); // Expected: null
        
        // Test with null values - added this after finding a bug in my testing
        String[] test6 = {"A", null, "B", "A"};
        System.out.println("Test 6: " + firstUniqueProduct(test6)); // Expected: "B"
    }
}
