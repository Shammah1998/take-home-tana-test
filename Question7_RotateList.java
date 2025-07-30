/**
 * Question 7: Rotate List
 * Rotates a linked list by moving each element n positions to the right.
 * 
 * Example:
 * Input: "ID_A01"->"ID_A02"->"ID_A03"->"ID_A04"->"ID_A05"->"ID_A06"->null, n=2
 * Output: "ID_A05"->"ID_A06"->"ID_A01"->"ID_A02"->"ID_A03"->"ID_A04"->null
 */

public class Question7_RotateList {
    
    // Node class for the linked list (moved inside the main class)
    // Kept it simple - didn't need a separate file for this small project
    static class ListNode {
        String data;
        ListNode next;
        
        // Constructor
        ListNode(String data) {
            this.data = data;
            this.next = null;
        }
    }
    
    /**
     * Rotates a linked list by n positions to the right
     * 
     * This was a fun problem! The key insight was understanding that rotating by
     * the list length brings me back to the original position. Also had to be careful
     * about handling edge cases like single nodes and empty lists.
     * 
     * @param head The head of the linked list
     * @param n Number of positions to rotate (can be larger than list length)
     * @return The new head of the rotated list
     */
    public static ListNode rotateList(ListNode head, int n) {
        // Handle edge cases - learned this is important after my first attempt failed
        if (head == null || head.next == null || n == 0) {
            return head;
        }
        
        // Count the number of nodes in the list
        int listLength = countNodes(head);
        
        // If n is larger than list length, I can reduce it
        // because rotating by list length brings me back to original position
        // This was the key insight that made the solution work for any n
        n = n % listLength;
        
        // If n is 0 after reduction, no rotation needed
        if (n == 0) {
            return head;
        }
        
        // Find the new tail (the node that will become the last node)
        // I need to go (listLength - n) steps from the beginning
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
        // This was the trickiest part to get right - had to think about the pointer manipulation
        ListNode originalTail = newHead;
        while (originalTail.next != null) {
            originalTail = originalTail.next;
        }
        originalTail.next = head;
        
        return newHead;
    }
    
    /**
     * Counts the number of nodes in the linked list
     * 
     * Simple utility method - could have done this inline but it's cleaner this way.
     * 
     * @param head The head of the linked list
     * @return Number of nodes in the list
     */
    private static int countNodes(ListNode head) {
        int count = 0;
        ListNode current = head;
        
        while (current != null) {
            count++;
            current = current.next;
        }
        
        return count;
    }
    
    /**
     * Creates a linked list from an array of strings
     * 
     * Helper method to make testing easier. Could have used a more generic approach
     * but kept it simple for this project.
     * 
     * @param values Array of string values
     * @return Head of the created linked list
     */
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
    
    /**
     * Prints the linked list in a readable format
     * 
     * Simple utility for debugging and testing. Could have used a more sophisticated
     * formatter but this works well enough for the project.
     * 
     * @param head The head of the linked list
     */
    public static void printList(ListNode head) {
        ListNode current = head;
        
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println(" -> null");
    }
    
    // Test method to demonstrate the function
    public static void main(String[] args) {
        System.out.println("=== Testing Question 7: Rotate List ===\n");
        
        // Test Case 1: Original example from the problem
        System.out.println("Test Case 1: Original Example");
        String[] values1 = {"ID_A01", "ID_A02", "ID_A03", "ID_A04", "ID_A05", "ID_A06"};
        ListNode list1 = createList(values1);
        
        System.out.print("Original list: ");
        printList(list1);
        
        int n1 = 2;
        System.out.println("Rotating by " + n1 + " positions to the right");
        
        ListNode rotated1 = rotateList(list1, n1);
        System.out.print("Rotated list:  ");
        printList(rotated1);
        System.out.println();
        
        // Test Case 2: Rotate by more than list length
        System.out.println("Test Case 2: Rotate by more than list length");
        String[] values2 = {"A", "B", "C"};
        ListNode list2 = createList(values2);
        
        System.out.print("Original list: ");
        printList(list2);
        
        int n2 = 5;  // More than list length (3)
        System.out.println("Rotating by " + n2 + " positions to the right");
        
        ListNode rotated2 = rotateList(list2, n2);
        System.out.print("Rotated list:  ");
        printList(rotated2);
        System.out.println();
        
        // Test Case 3: Rotate by list length (should return original)
        System.out.println("Test Case 3: Rotate by list length");
        String[] values3 = {"X", "Y", "Z"};
        ListNode list3 = createList(values3);
        
        System.out.print("Original list: ");
        printList(list3);
        
        int n3 = 3;  // Equal to list length
        System.out.println("Rotating by " + n3 + " positions to the right");
        
        ListNode rotated3 = rotateList(list3, n3);
        System.out.print("Rotated list:  ");
        printList(rotated3);
        System.out.println();
        
        // Test Case 4: Single node list - added this after finding a bug in my testing
        System.out.println("Test Case 4: Single node list");
        String[] values4 = {"Single"};
        ListNode list4 = createList(values4);
        
        System.out.print("Original list: ");
        printList(list4);
        
        int n4 = 1;
        System.out.println("Rotating by " + n4 + " position to the right");
        
        ListNode rotated4 = rotateList(list4, n4);
        System.out.print("Rotated list:  ");
        printList(rotated4);
        System.out.println();
        
        // Test Case 5: Empty list - this caught a null pointer exception in early version
        System.out.println("Test Case 5: Empty list");
        ListNode list5 = null;
        
        System.out.print("Original list: null");
        System.out.println();
        
        int n5 = 2;
        System.out.println("Rotating by " + n5 + " positions to the right");
        
        ListNode rotated5 = rotateList(list5, n5);
        System.out.print("Rotated list:  ");
        if (rotated5 == null) {
            System.out.println("null");
        } else {
            printList(rotated5);
        }
        System.out.println();
        
        System.out.println("=== All Tests Completed ===");
    }
}
