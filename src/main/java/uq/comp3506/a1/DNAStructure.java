// @edu:student-assignment

package uq.comp3506.a1;

/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */
public class DNAStructure {

    /**
     * Construct at empty DNAStructure object that can store w chars.
     */
    public DNAStructure(int w) {

    }

    /**
     * Return true if the structure is full, false otherwise
     */
    public boolean isFull() {

        return false;
    }

    /**
     * Remove the oldest character, and add the newest one
     * Full marks: O(1) or O(1*) worst-case
     */
    public void slide(char c) {

    }

    /**
     * Return the number of times c appears in the current window
     * Full marks: O(1) worst-case
     */
    public int count(char c) {

        return 0;
    }

    /**
     * Return the number of unique sequences of length k currently stored
     * in the window that repeat at least once in the window.
     * For example, consider the window contains: gtcgtcgtc and k=4
     * We would return 3 because:
     * 'gtcg' and 'tcgt' and 'cgtc' all repeat in the window.
     * Full marks: O(w) worst-case
     * Partial marks: O(wk) worst-case
     * Note: k will be in the range [2, 13], and 2 <= k <= w
     */
    public int countRepeats(int k) {

        return 0;
    }

    /**
     * Return true if the window contains a palindrome of length k. Remember
     * that DNA palindromes are different to typical English word palindromes.
     * Full marks: O(w) best-case, and O(wk) worst-case.
     * Again, k will be in the range [2, 100], and 2 <= k <= w
     */
    public boolean hasPalindrome(int k) {

        return false;
    }

    /**
     * Return a string representation of the data structure. It should just be
     * a single string containing the oldest to newest character in that order.
     * If the window is not full, just return the populated characters.
     */
    public String stringify() {

        return null;
    }
}
