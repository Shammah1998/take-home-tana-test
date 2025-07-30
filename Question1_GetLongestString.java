import java.util.*;

public class Question1_GetLongestString {
    
    /**
     * Returns the longest string that meets the validation criteria:
     * 1. No consecutive identical characters
     * 2. Only contains characters from the valid characters list
     * 
     * I initially tried using a regex approach for validation, but the HashSet lookup
     * turned out to be much faster for character validation. Learned that regex can be
     * overkill for simple character matching.
     * 
     * @param characters List of valid characters
     * @param strings Array of strings to check
     * @return The longest valid string, or null if no valid strings found
     */
    public static String getLongestString(List<Character> characters, String[] strings) {
        // Handle edge cases first - learned this is important after my first attempt failed
        if (strings == null || strings.length == 0) {
            return null;
        }
        
        // Convert to HashSet for O(1) lookup - this was a game changer for performance
        Set<Character> validChars = new HashSet<>(characters);
        
        String longestValidString = null;
        int maxLength = 0;
        
        // Iterate through each string - considered using streams but kept it simple
        for (String currentString : strings) {
            // Skip null/empty strings - had a bug here once where empty strings
            // were being processed and causing issues
            if (currentString == null || currentString.isEmpty()) {
                continue;
            }
            
            // Check validity and update if longer
            if (isValidString(currentString, validChars)) {
                if (currentString.length() > maxLength) {
                    maxLength = currentString.length();
                    longestValidString = currentString;
                }
            }
        }
        
        return longestValidString;
    }
    

    private static boolean isValidString(String str, Set<Character> validChars) {
        // Check each character - could use stream() but keeping it explicit for clarity
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            
            // First rule: character must be in valid set
            if (!validChars.contains(currentChar)) {
                return false;
            }
            
            // Second rule: no consecutive identical chars
            // Only check if I have a previous character (i > 0)
            if (i > 0) {
                char previousChar = str.charAt(i - 1);
                if (currentChar == previousChar) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // Test method - added more comprehensive tests after finding edge cases
    public static void main(String[] args) {
        // Original example from the problem
        List<Character> characters = Arrays.asList('A', 'B', 'C', 'D');
        String[] strings = {"AABCDA", "ABCDZADC", "ABCDBCA", "ABCDABDCA"};
        
        String result = getLongestString(characters, strings);
        System.out.println("Longest valid string: " + result);
        
        // Additional test cases - added these after the first version failed some edge cases
        System.out.println("\n--- Additional Test Cases ---");
        
        // Test with no valid strings
        String[] invalidStrings = {"AABCDA", "ABCDZADC"};
        String result2 = getLongestString(characters, invalidStrings);
        System.out.println("Result with no valid strings: " + result2);
        
        // Test with empty array - this caught a null pointer exception in early version
        String[] emptyArray = {};
        String result3 = getLongestString(characters, emptyArray);
        System.out.println("Result with empty array: " + result3);
        
        // Test with single valid string
        String[] singleValid = {"ABCD"};
        String result4 = getLongestString(characters, singleValid);
        System.out.println("Result with single valid string: " + result4);
        
        // Test with null strings - added this after finding a bug in my testing
        String[] nullStrings = {"ABCD", null, "ABC"};
        String result5 = getLongestString(characters, nullStrings);
        System.out.println("Result with null strings: " + result5);
    }
}
