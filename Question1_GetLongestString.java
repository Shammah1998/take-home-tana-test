import java.util.*;

public class Question1_GetLongestString {
    
    /**
     * Returns the longest string that meets the validation criteria:
     * 1. No consecutive identical characters
     * 2. Only contains characters from the valid characters list
     * 
     * @param characters List of valid characters
     * @param strings Array of strings to check
     * @return The longest valid string, or null if no valid strings found
     */
    public static String getLongestString(List<Character> characters, String[] strings) {
        // If no strings provided, return null
        if (strings == null || strings.length == 0) {
            return null;
        }
        
        // Convert characters list to a set for faster lookup
        Set<Character> validChars = new HashSet<>(characters);
        
        String longestValidString = null;
        int maxLength = 0;
        
        // Check each string in the array
        for (String currentString : strings) {
            // Skip null or empty strings
            if (currentString == null || currentString.isEmpty()) {
                continue;
            }
            
            // Check if this string is valid
            if (isValidString(currentString, validChars)) {
                // If it's longer than our current longest, update it
                if (currentString.length() > maxLength) {
                    maxLength = currentString.length();
                    longestValidString = currentString;
                }
            }
        }
        
        return longestValidString;
    }
    
    /**
     * Checks if a string is valid according to the rules:
     * 1. No consecutive identical characters
     * 2. Only contains characters from the valid characters set
     * 
     * @param str The string to validate
     * @param validChars Set of valid characters
     * @return true if the string is valid, false otherwise
     */
    private static boolean isValidString(String str, Set<Character> validChars) {
        // Check each character in the string
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            
            // Check if character is in the valid characters list
            if (!validChars.contains(currentChar)) {
                return false;
            }
            
            // Check for consecutive identical characters
            if (i > 0) {
                char previousChar = str.charAt(i - 1);
                if (currentChar == previousChar) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // Test method to demonstrate the function
    public static void main(String[] args) {
        // Example from the problem
        List<Character> characters = Arrays.asList('A', 'B', 'C', 'D');
        String[] strings = {"AABCDA", "ABCDZADC", "ABCDBCA", "ABCDABDCA"};
        
        String result = getLongestString(characters, strings);
        System.out.println("Longest valid string: " + result);
        
        // Additional test cases
        System.out.println("\n--- Additional Test Cases ---");
        
        // Test with no valid strings
        String[] invalidStrings = {"AABCDA", "ABCDZADC"};
        String result2 = getLongestString(characters, invalidStrings);
        System.out.println("Result with no valid strings: " + result2);
        
        // Test with empty array
        String[] emptyArray = {};
        String result3 = getLongestString(characters, emptyArray);
        System.out.println("Result with empty array: " + result3);
        
        // Test with single valid string
        String[] singleValid = {"ABCD"};
        String result4 = getLongestString(characters, singleValid);
        System.out.println("Result with single valid string: " + result4);
    }
}
