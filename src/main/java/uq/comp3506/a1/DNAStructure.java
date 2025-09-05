// @edu:student-assignment

package uq.comp3506.a1;

import uq.comp3506.a1.structures.DynamicArray;

/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */
public class DNAStructure {

    private final int w;
    private final DynamicArray<Character> window;
    private final int[] counts = new int[4];

    private static final int ADENINE_CIX = 0;
    private static final int CYTOSINE_CIX = 1;
    private static final int GUANINE_CIX = 2;
    private static final int THYMINE_CIX = 3;
    private static final char ADENINE = 'A';
    private static final char CYTOSINE = 'C';
    private static final char GUANINE = 'G';
    private static final char THYMINE = 'T';

    /**
     * Construct at empty DNAStructure object that can store w chars.
     * @param w the size of the window
     */
    public DNAStructure(int w) {
        this.w = w;
        window = new DynamicArray<>();
    }

    /**
     * Return true if the structure is full, false otherwise
     */
    public boolean isFull() {
        return w == window.size();
    }

    /**
     * Remove the oldest character, and add the newest one
     * Full marks: O(1) or O(1*) worst-case
     */
    public void slide(char c) {
        if (isFull()) {
            adjustCount(c, window.remove(0));
        } else {
            adjustCount(c);
        }
        window.append(c);
    }

    /**
     * Increases the count for the given character added
     * @param added the character added to the window
     */
    private void adjustCount(char added) {
        switch (added) {
            case ADENINE -> counts[ADENINE_CIX]++;
            case CYTOSINE -> counts[CYTOSINE_CIX]++;
            case GUANINE -> counts[GUANINE_CIX]++;
            case THYMINE -> counts[THYMINE_CIX]++;
        }
    }

    /**
     * Increments the count of the letter added and decrements the letter that was removed
     * @param added the character added
     * @param removed the character removed
     */
    private void adjustCount(char added, char removed) {
        if (added == removed) {
            return;
        }
        switch (added) {
            case ADENINE -> counts[ADENINE_CIX]++;
            case CYTOSINE -> counts[CYTOSINE_CIX]++;
            case GUANINE -> counts[GUANINE_CIX]++;
            case THYMINE -> counts[THYMINE_CIX]++;
        }
        switch (removed) {
            case ADENINE -> counts[ADENINE_CIX]--;
            case CYTOSINE -> counts[CYTOSINE_CIX]--;
            case GUANINE -> counts[GUANINE_CIX]--;
            case THYMINE -> counts[THYMINE_CIX]--;
        }
    }

    /**
     * Return the number of times c appears in the current window
     * Full marks: O(1) worst-case
     */
    public int count(char c) {
        return switch (c) {
            case ADENINE -> counts[ADENINE_CIX];
            case CYTOSINE -> counts[CYTOSINE_CIX];
            case GUANINE -> counts[GUANINE_CIX];
            case THYMINE -> counts[THYMINE_CIX];
            default -> 0;
        };
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
        if (k <= 1 || k > 13 || k < window.size()) {
            return 0; // input is bounded
        }
        String s = stringify();
        int count = 0;


        return 0;
    }

    /**
     * Return true if the window contains a palindrome of length k. Remember
     * that DNA palindromes are different to typical English word palindromes.
     * Full marks: O(w) best-case, and O(wk) worst-case.
     * Again, k will be in the range [2, 100], and 2 <= k <= w
     */
    public boolean hasPalindrome(int k) {
        int last = 0;
        for (int i = 0; i < window.size() - k; i++) {

        }
        for (int startIx = 0; startIx < window.size() - k; startIx++) {
            for (int palIx = 0; palIx < k / 2; palIx++) {
                if (!isComplement(window.get(startIx + palIx),
                        window.get(startIx + k - palIx - 1))) {
                    break;
                }
                if (palIx + 1 >= k / 2) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Return a string representation of the data structure. It should just be
     * a single string containing the oldest to newest character in that order.
     * If the window is not full, just return the populated characters.
     */
    public String stringify() {
        return window.toString();
    }

    private static boolean isComplement(char ch, char chComp) {
        return switch (ch) {
            case ADENINE -> chComp == THYMINE;
            case CYTOSINE -> chComp == GUANINE;
            case GUANINE -> chComp == CYTOSINE;
            case THYMINE -> chComp == ADENINE;
            default -> false;
        };
    }
}
