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
     * Number of longs used to store the bits
     */
    private int len;

    private final long unsetMask;

    /**
     * We use 'long' instead of 'int' to store elements because it can fit
     * 64 bits instead of 32
     */
    private long[] data;

    /**
     * Constructs a bitvector, pre-allocating enough memory to store `size` bits
     */
    public BitVector(long size) {
        this.size = size;
        len = (int) size / BitVector.BitsPerElement;
        if (len * BitVector.BitsPerElement < size) {
            len++;
        }
        capacity = len * BitVector.BitsPerElement;
        this.data = new long[len];
        for (int i = 0; i < len; i++) {
            data[i] = 0;
        }
        unsetMask = getMask((int) (size) % BitVector.BitsPerElement);
    }

    /**
     * Returns the total number of bits that can be used
     */
    public long size() {
        return size;
    }

    /**
     * Returns the total number of bits allocated in the data array
     */
    public long capacity() {
        return capacity;
    }

    /**
     * Returns the value of the bit at index ix
     * If the index is out of bounds, you should throw an IndexOutOfBoundsException
     */
    public boolean get(long ix) {
        checkBounds(ix);
        return (data[getDataIndex(ix)] & (1L << (ix % BitVector.BitsPerElement))) != 0;
    }

    /**
     * Set the bit at index ix
     * If the index is out of bounds, you should throw an IndexOutOfBoundsException
     */
    public void set(long ix) {
        checkBounds(ix);
        data[getDataIndex(ix)] |= (1L << (ix % BitVector.BitsPerElement));
    }

    /**
     * Unset the bit at index ix
     * If the index is out of bounds, you should throw an IndexOutOfBoundsException
     */
    public void unset(long ix) {
        checkBounds(ix);
        data[getDataIndex(ix)] &= ~(1L << (ix % BitVector.BitsPerElement));
    }

    /**
     * Convert the BitVector to its complement
     * That means, all 1's become 0's and all 0's become 1's
     */
    public void complement() {
        for (int i = 0; i < len; i++) {
            data[i] = ~(data[i]);
        }
        data[len - 1] &= unsetMask;
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
        if (dist == 0) {
            return;
        }
        if (dist >= size || -1 * dist >= size) {
            this.clear();
            return;
        }
        if (dist > 0) {
            int offset = (int) (dist / BitsPerElement);
            for (int i = len - offset; i < len; i++) {
                data[i] = 0;
            }
            int shift = (int) (dist - ((long) offset * (long) BitsPerElement));
            for (int i = len - 1; i > offset; i--) {
                data[i] = data[i - offset] << shift;
                if (i == len - 1) {
                    data[i] &= unsetMask;
                    continue;
                }
                data[i] |= (data[i - offset] >>> (BitsPerElement - shift));
            }
            data[offset] = data[0] << shift;
            for (int i = 0; i < offset; i++) {
                data[i] = 0;
            }
            data[len - 1] &= unsetMask;
            return;
        }
        dist *= -1;
        int offset = (int) (dist / BitsPerElement);
        for (int i = 0; i < offset; i++) {
            data[i] = 0;
        }
        int shift = (int) (dist - ((long) offset * (long) BitsPerElement));
        for (int i = offset; i < len - 1; i++) {
            data[i - offset] = data[i] >>> shift;

            data[i - offset] |= (data[i] << (BitsPerElement - shift));
        }
        data[len - 1] &= unsetMask;

        data[0] = data[offset] >>> shift;
        for (int i = len - 1 - offset; i < len - 1; i++) {
            data[i] = 0;
        }
        data[len - 1] &= unsetMask;
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
        if (dist == 0) {
            return;
        }
        if (dist >= size || -1 * dist >= size) {
            dist = getMinRotDist(dist); // removes excessive rotation
        }
        long[] copy = new long[len];
        if (dist > 0) {
            long overflow;
            int offset = (int) (dist / BitsPerElement);
            int shift = (int) (dist - ((long) offset * (long) BitsPerElement));
            for (int i = 0; i < len; i++) {
                copy[(i + offset) % len] = data[i];
            }
            data = copy;
            int rem = shift % BitsPerElement;
            overflow = ((data[len - 1] >>> rem) << shift);
            for (int i = len - 1; i > 0; i--) {
                data[i] <<= shift;
                data[i] |= (data[i - 1] >>> (BitsPerElement - shift));
            }
            data[0] <<= shift;
            data[0] |= overflow;
            data[len - 1] &= unsetMask;
            return;
        }
        dist *= -1;
        long overflow;
        int offset = (int) (dist / BitsPerElement);
        int shift = (int) (dist - ((long) offset * (long) BitsPerElement));
        for (int i = len - 1; i > 0; i--) {
            copy[(i - offset) % len] = data[i];
        }
        data = copy;
        overflow = data[0] << (BitsPerElement - shift);
        for (int i = 0; i < len - 2; i++) {
            data[i] >>>= shift;
            data[i] |= (data[i + 1] << (BitsPerElement - shift));
        }
        data[len - 1] >>>= shift;
        data[len - 1] |= overflow;
        data[len - 1] &= unsetMask;
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

    private void checkBounds(long ix) {
        if (ix < 0 || ix >= size) {
            throw new IndexOutOfBoundsException("Index " + ix + " is out of bounds.");
        }
    }

    private int getDataIndex(long ix) {
        checkBounds(ix);
        return (int) (ix / BitsPerElement);
    }

    private long getMask(int relIx) {
        if (relIx < 0 || relIx >= BitsPerElement) {
            throw new IndexOutOfBoundsException("Index " + relIx + " is out of bounds.");
        }
        long val = 0;
        for (int i = 0; i < relIx; i++) {
            val <<= 1;
            val |= 1;
        }
        return val;
    }

    private void clear() {
        for (int i = 0; i < len; i++) {
            data[len] = 0;
        }
    }

    /**
     * Prints out the contents of the bitvector with each byte separated by a space
     */
    public void print() {
        for (int i = len - 1; i >= 0; i--) {
            int j = (i == len - 1) ? ((int) (size % BitsPerElement)) - 1 : BitsPerElement - 1;
            for (; j >= 0; j--) {
                if ((j % 8 == 0)) {
                    if ((j != 0 || i != 0)) {
                        System.out.print(" ");
                    }
                }
                System.out.print(((data[i] & (1L << j)) == 0) ? "0" : "1");
            }
        }
        System.out.println();
    }

    private long getMinRotDist(long dist) {
        if (dist == 0L) {
            return 0;
        }
        return dist - (size * (dist / size));
    }
}
