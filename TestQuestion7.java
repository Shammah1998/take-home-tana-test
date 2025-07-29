/**
 * Test file for Question 7: Rotate List
 * This file tests the rotateList function with various scenarios
 */

public class TestQuestion7 {
    
    // Node class (same as in Question7_RotateList.java) - moved inside the class
    static class ListNode {
        String data;
        ListNode next;
        
        ListNode(String data) {
            this.data = data;
            this.next = null;
        }
    }
    
    // Copy the rotateList function from Question7_RotateList.java
    public static ListNode rotateList(ListNode head, int n) {
        // Handle edge cases
        if (head == null || head.next == null || n == 0) {
            return head;
        }
        
        // Count the number of nodes in the list
        int listLength = countNodes(head);
        
        // If n is larger than list length, we can reduce it
        n = n % listLength;
        
        // If n is 0 after reduction, no rotation needed
        if (n == 0) {
            return head;
        }
        
        // Find the new tail (the node that will become the last node)
        int stepsToNewTail = listLength - n;
        ListNode current = head;
        
        // Move to the new tail position
        for (int i = 1; i < stepsToNewTail; i++) {
            current = current.next;
        }
        
        // current now points to the new tail
        ListNode newHead = current.next;  // This will be the new head
        current.next = null;              // Break the list at new tail
        
        // Find the original tail and connect it to the original head
        ListNode originalTail = newHead;
        while (originalTail.next != null) {
            originalTail = originalTail.next;
        }
        originalTail.next = head;
        
        return newHead;
    }
    
    private static int countNodes(ListNode head) {
        int count = 0;
        ListNode current = head;
        
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
    public static ListNode createList(String[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        
        return head;
    }
    
    public static String listToString(ListNode head) {
        if (head == null) {
            return "null";
        }
        
        StringBuilder result = new StringBuilder();
        ListNode current = head;
        
        while (current != null) {
            result.append(current.data);
            if (current.next != null) {
                result.append(" -> ");
            }
            current = current.next;
        }
        result.append(" -> null");
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Testing Question 7: Rotate List ===\n");
        
        // Test Case 1: Original example from the problem
        System.out.println("Test Case 1: Original Example");
        String[] values1 = {"ID_A01", "ID_A02", "ID_A03", "ID_A04", "ID_A05", "ID_A06"};
        ListNode list1 = createList(values1);
        
        System.out.println("Original list: " + listToString(list1));
        
        int n1 = 2;
        System.out.println("Rotating by " + n1 + " positions to the right");
        
        ListNode rotated1 = rotateList(list1, n1);
        String expected1 = "ID_A05 -> ID_A06 -> ID_A01 -> ID_A02 -> ID_A03 -> ID_A04 -> null";
        String actual1 = listToString(rotated1);
        
        System.out.println("Expected: " + expected1);
        System.out.println("Actual:   " + actual1);
        System.out.println("Test passed: " + expected1.equals(actual1));
        System.out.println();
        
        // Test Case 2: Rotate by more than list length
        System.out.println("Test Case 2: Rotate by more than list length");
        String[] values2 = {"A", "B", "C"};
        ListNode list2 = createList(values2);
        
        System.out.println("Original list: " + listToString(list2));
        
        int n2 = 5;  // More than list length (3)
        System.out.println("Rotating by " + n2 + " positions to the right");
        
        ListNode rotated2 = rotateList(list2, n2);
        String expected2 = "B -> C -> A -> null";
        String actual2 = listToString(rotated2);
        
        System.out.println("Expected: " + expected2);
        System.out.println("Actual:   " + actual2);
        System.out.println("Test passed: " + expected2.equals(actual2));
        System.out.println();
        
        // Test Case 3: Rotate by list length (should return original)
        System.out.println("Test Case 3: Rotate by list length");
        String[] values3 = {"X", "Y", "Z"};
        ListNode list3 = createList(values3);
        
        System.out.println("Original list: " + listToString(list3));
        
        int n3 = 3;  // Equal to list length
        System.out.println("Rotating by " + n3 + " positions to the right");
        
        ListNode rotated3 = rotateList(list3, n3);
        String expected3 = "X -> Y -> Z -> null";
        String actual3 = listToString(rotated3);
        
        System.out.println("Expected: " + expected3);
        System.out.println("Actual:   " + actual3);
        System.out.println("Test passed: " + expected3.equals(actual3));
        System.out.println();
        
        // Test Case 4: Single node list
        System.out.println("Test Case 4: Single node list");
        String[] values4 = {"Single"};
        ListNode list4 = createList(values4);
        
        System.out.println("Original list: " + listToString(list4));
        
        int n4 = 1;
        System.out.println("Rotating by " + n4 + " position to the right");
        
        ListNode rotated4 = rotateList(list4, n4);
        String expected4 = "Single -> null";
        String actual4 = listToString(rotated4);
        
        System.out.println("Expected: " + expected4);
        System.out.println("Actual:   " + actual4);
        System.out.println("Test passed: " + expected4.equals(actual4));
        System.out.println();
        
        // Test Case 5: Empty list
        System.out.println("Test Case 5: Empty list");
        ListNode list5 = null;
        
        System.out.println("Original list: null");
        
        int n5 = 2;
        System.out.println("Rotating by " + n5 + " positions to the right");
        
        ListNode rotated5 = rotateList(list5, n5);
        String expected5 = "null";
        String actual5 = listToString(rotated5);
        
        System.out.println("Expected: " + expected5);
        System.out.println("Actual:   " + actual5);
        System.out.println("Test passed: " + expected5.equals(actual5));
        System.out.println();
        
        // Test Case 6: Rotate by 0
        System.out.println("Test Case 6: Rotate by 0");
        String[] values6 = {"A", "B", "C"};
        ListNode list6 = createList(values6);
        
        System.out.println("Original list: " + listToString(list6));
        
        int n6 = 0;
        System.out.println("Rotating by " + n6 + " positions to the right");
        
        ListNode rotated6 = rotateList(list6, n6);
        String expected6 = "A -> B -> C -> null";
        String actual6 = listToString(rotated6);
        
        System.out.println("Expected: " + expected6);
        System.out.println("Actual:   " + actual6);
        System.out.println("Test passed: " + expected6.equals(actual6));
        System.out.println();
        
        System.out.println("=== All Tests Completed ===");
    }
} 