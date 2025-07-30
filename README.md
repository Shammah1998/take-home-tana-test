# Take-Home Test Solutions

## Programming Language
**Java 17** - I chose Java because it's what I'm most comfortable with for algorithmic problems, and it has great built-in data structures that made these solutions cleaner.

## Solution Overview

### Question 1: Get Longest String
I iterated through each string, validated them using a HashSet for character checking and a simple loop for consecutive character detection, then tracked the longest valid one. The key insight was using HashSet for O(1) character validation instead of regex.

### Question 2: Unique Sums
Implemented a recursive backtracking approach with sorting to handle duplicates. I also added a dynamic programming version after the recursive solution was too slow for larger inputs. The trick was sorting first to treat [1,2,3,4] and [4,3,2,1] as the same combination.

### Question 3: Make Array Zero
This was tricky! I initially tried simulating all operations but that was too slow. The breakthrough came from understanding the mathematical pattern - each element must be >= the previous element for the decrement operations to work. I also added a simulation approach as backup verification.

### Question 4: First Unique Product
Used a two-pass approach: first to count occurrences with HashMap, then to find the first product with count == 1. The key was realizing "first" means first in original array order, not alphabetically. Tried TreeMap initially but HashMap was faster.

### Question 5: Closest Minimums
Found the minimum value first, then tracked all its positions and calculated the minimum distance between consecutive positions. I also created an optimized single-pass version that finds minimum and tracks positions simultaneously. The insight was that I only need to check consecutive pairs, not all pairs.

### Question 6: Three Most Common Strings
Split the sentence, counted word frequencies with HashMap, then sorted by frequency (descending) and alphabetically (ascending) for ties. Also implemented a PriorityQueue version for better performance. Had to think about case sensitivity and extra spaces.

### Question 7: Rotate List
The key insight was that rotating by list length brings you back to the original position, so I could reduce n using modulo. Then I found the new tail position and reconnected the pointers. Had to be careful with edge cases like single nodes and empty lists.

## How to Run the Code

### Prerequisites
- Java 17 or higher installed
- Command line access

### Compilation and Execution

Each question can be compiled and run independently. Here are the commands:

```bash
# Question 1: Get Longest String
javac Question1_GetLongestString.java
java Question1_GetLongestString

# Question 2: Unique Sums
javac Question2_UniqueSums.java
java Question2_UniqueSums

# Question 3: Make Array Zero
javac Question3_MakeArrayZero.java
java Question3_MakeArrayZero

# Question 4: First Unique Product
javac Question4_FirstUniqueProduct.java
java Question4_FirstUniqueProduct

# Question 5: Closest Minimums
javac Question5_ClosestMinimums.java
java Question5_ClosestMinimums

# Question 6: Three Most Common Strings
javac Question6_ThreeMostCommonStrings.java
java Question6_ThreeMostCommonStrings

# Question 7: Rotate List
javac Question7_RotateList.java
java Question7_RotateList
```

### Alternative 2: Compile All at Once
```bash
# Compile all files
javac *.java

# Then run each one
java Question1_GetLongestString
java Question2_UniqueSums
# ... and so on
```

## Alternative 3: Install the 'Extension for Java by Microsoft' to compile and run files consecutively

## Notes
- Each file includes comprehensive test cases that demonstrate the solution
- I've included both basic and optimized approaches where applicable
- The code handles edge cases like null inputs, empty arrays, and single elements
- All solutions are well-documented with my learning process and insights
