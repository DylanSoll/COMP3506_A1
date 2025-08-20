// @edu:student-assignment

package uq.comp3506.a1.structures;

/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */
public class BitVector {

    // The number of bits per integer stored
    public static final int BitsPerElement = 64;

    /**
     * The number of "active" bits that can be stored in this bitvector
     */
    private final long size;

    /**
     * The total number of bits allocated in the data array
     */
    private final long capacity;

    /**
     * We use 'long' instead of 'int' to store elements because it can fit
     * 64 bits instead of 32
     */
    private long[] data;

    /**
     * Constructs a bitvector, pre-allocating enough memory to store `size` bits
     */
    public BitVector(long size) {
        this.size = -1;
        // XXX todo
    }

    /**
     * Returns the total number of bits that can be used
     */
    public long size() {
        // TODO: replace with the correct implementation
        return 0;
    }

    /**
     * Returns the total number of bits allocated in the data array
     */
    public long capacity() {
        // TODO: replace with the correct implementation
        return 0;
    }

    /**
     * Returns the value of the bit at index ix
     * If the index is out of bounds, you should throw an IndexOutOfBoundsException
     */
    public boolean get(long ix) {
        return false;
    }

    /**
     * Set the bit at index ix
     * If the index is out of bounds, you should throw an IndexOutOfBoundsException
     */
    public void set(long ix) {

    }

    /**
     * Unset the bit at index ix
     * If the index is out of bounds, you should throw an IndexOutOfBoundsException
     */
    public void unset(long ix) {

    }

    /**
     * Convert the BitVector to its complement
     * That means, all 1's become 0's and all 0's become 1's
     */
    public void complement() {

    }

    /**
     * Shift the bits `dist` positions
     * If dist is positive, this is a left shift, assuming the least significant
     * bit is the rightmost bit. So, consider you have a 4 element bitvector:
     * Indexes:  3 2 1 0
     * Elements: 1 1 0 1
     * Doing a shift(2) would yield:
     * Indexes:  3 2 1 0
     * Elements: 0 1 0 0
     *             ^--- This bit was previously at index 0
     *           ^----- This bit was previously at index 1
     *
     * Don't forget that you must also handle negative values of dist, and
     * these will invoke a right shift.
     *
     * The bits that "fall off" are always replaced with 0's.
     */
    public void shift(long dist) {

    }
 
    /**
     * Rotate the bits `dist` positions
     * If dist is positive, this is a left rotation, assuming the least significant
     * bit is the rightmost bit. So, consider you have a 5 element bitvector:
     * Indexes:  4 3 2 1 0
     * Elements: 1 1 1 0 1
     * Doing a rotate(2) would yield:
     * Indexes:  4 3 2 1 0
     * Elements: 1 0 1 1 1
     *                 ^This bit was previously at index 4
     *             ^--- This bit was previously at index 1
     *           ^----- This bit was previously at index 2
     * As you can see, it operates the same as the shift, but the bits that
     * are moved "off the end" of the vector wrap back around to the beginning.
     *
     * Don't forget that you must also handle negative values of dist, and
     * these will invoke a right shift.
     */
    public void rotate(long dist) {

    }

    /**
     * COMP7505 only (COMP3506 may do this for fun)
     * Returns the number of bits that are set to 1 across the entire bitvector
     */
    public long popcount() {

        return -1;
    }

    /**
     * COMP7505 only (COMP3506 may do this for fun)
     * Returns the length of the longest run of bits that are set (1) across
     * the entire bitvector
     */
    public long runcount() {

        return -1;
    }

}
