import java.util.HashMap;
import java.util.Map;

public class Question4_FirstUniqueProduct {
    
    /**
     * Finds the first product in an array that occurs only once.
     * 
     * Approach:
     * 1. First pass: Count occurrences of each product using HashMap
     * 2. Second pass: Find the first product with count == 1
     * 
     * Time Complexity: O(n) - we traverse the array twice
     * Space Complexity: O(n) - HashMap stores at most n unique products
     * 
     * @param products Array of product names
     * @return First unique product, or null if no unique products exist
     */
    public static String firstUniqueProduct(String[] products) {
        // Handle edge cases
        if (products == null || products.length == 0) {
            return null;
        }
        
        // Step 1: Count occurrences of each product
        // Using HashMap for O(1) average time complexity for insert and lookup
        Map<String, Integer> productCount = new HashMap<>();
        
        for (String product : products) {
            // Increment count for each product, defaulting to 0 if not present
            productCount.put(product, productCount.getOrDefault(product, 0) + 1);
        }
        
        // Step 2: Find the first product with count == 1
        // We need to iterate in the original order to find the "first" unique product
        for (String product : products) {
            // Check if this product appears exactly once
            if (productCount.get(product) == 1) {
                return product; // Found the first unique product
            }
        }
        
        // No unique products found
        return null;
    }
    
    /**
     * Test method to demonstrate the solution
     */
    public static void main(String[] args) {
        // Test case from the question
        String[] test1 = {"Apple", "Computer", "Apple", "Bag"};
        System.out.println("Test 1: " + firstUniqueProduct(test1)); // Expected: "Computer"
        
        // Additional test cases
        String[] test2 = {"A", "B", "C", "A", "B"};
        System.out.println("Test 2: " + firstUniqueProduct(test2)); // Expected: "C"
        
        String[] test3 = {"A", "A", "A"};
        System.out.println("Test 3: " + firstUniqueProduct(test3)); // Expected: null
        
        String[] test4 = {"Single"};
        System.out.println("Test 4: " + firstUniqueProduct(test4)); // Expected: "Single"
        
        String[] test5 = {};
        System.out.println("Test 5: " + firstUniqueProduct(test5)); // Expected: null
    }
}
