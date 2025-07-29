import java.util.*;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("=== Testing Question 1: Get Longest String ===\n");
        
        // Test Case 1: Original example from the problem
        System.out.println("Test Case 1: Original Example");
        System.out.println("Valid characters: [A, B, C, D]");
        System.out.println("Strings to test: [AABCDA, ABCDZADC, ABCDBCA, ABCDABDCA]");
        
        List<Character> characters = Arrays.asList('A', 'B', 'C', 'D');
        String[] strings = {"AABCDA", "ABCDZADC", "ABCDBCA", "ABCDABDCA"};
        
        String result = Question1_GetLongestString.getLongestString(characters, strings);
        System.out.println("Expected result: ABCDABDCA");
        System.out.println("Actual result: " + result);
        System.out.println("Test passed: " + ("ABCDABDCA".equals(result)));
        System.out.println();
        
        // Test Case 2: No valid strings
        System.out.println("Test Case 2: No Valid Strings");
        String[] invalidStrings = {"AABCDA", "ABCDZADC"};
        String result2 = Question1_GetLongestString.getLongestString(characters, invalidStrings);
        System.out.println("Expected result: null");
        System.out.println("Actual result: " + result2);
        System.out.println("Test passed: " + (result2 == null));
        System.out.println();
        
        // Test Case 3: Empty array
        System.out.println("Test Case 3: Empty Array");
        String[] emptyArray = {};
        String result3 = Question1_GetLongestString.getLongestString(characters, emptyArray);
        System.out.println("Expected result: null");
        System.out.println("Actual result: " + result3);
        System.out.println("Test passed: " + (result3 == null));
        System.out.println();
        
        // Test Case 4: Single valid string
        System.out.println("Test Case 4: Single Valid String");
        String[] singleValid = {"ABCD"};
        String result4 = Question1_GetLongestString.getLongestString(characters, singleValid);
        System.out.println("Expected result: ABCD");
        System.out.println("Actual result: " + result4);
        System.out.println("Test passed: " + ("ABCD".equals(result4)));
        System.out.println();
        
        // Test Case 5: Multiple valid strings of same length
        System.out.println("Test Case 5: Multiple Valid Strings of Same Length");
        String[] sameLength = {"ABCD", "DCBA", "ACBD"};
        String result5 = Question1_GetLongestString.getLongestString(characters, sameLength);
        System.out.println("Expected result: Any of [ABCD, DCBA, ACBD] (first one found)");
        System.out.println("Actual result: " + result5);
        System.out.println("Test passed: " + (result5 != null && result5.length() == 4));
        System.out.println();
        
        // Test Case 6: Edge case with null string
        System.out.println("Test Case 6: Array with Null String");
        String[] withNull = {"ABCD", null, "DCBA"};
        String result6 = Question1_GetLongestString.getLongestString(characters, withNull);
        System.out.println("Expected result: DCBA (longest valid string)");
        System.out.println("Actual result: " + result6);
        System.out.println("Test passed: " + ("DCBA".equals(result6)));
        
        System.out.println("\n=== All Tests Completed ===");
    }
} 