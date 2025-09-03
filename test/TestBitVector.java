/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

import org.junit.Assert;
import org.junit.Test;
import uq.comp3506.a1.structures.BitVector;

public class TestBitVector {

    public static void main(String[] args) {
        System.out.println("Testing BitVector Class...");
        System.out.println("Success!");
    }

    @Test
    public void testSize() {
        BitVector vec = new BitVector(10L);
        Assert.assertEquals(10L, vec.size());
        vec = new BitVector(64L);
        Assert.assertEquals(64L, vec.size());
        vec = new BitVector(15331L);
        Assert.assertEquals(15331L, vec.size());
    }


    @Test
    public void testCapacity() {
        BitVector vec = new BitVector(10L);
        Assert.assertEquals(BitVector.BitsPerElement *  ((long) Math.ceil(((double) (10L) / BitVector.BitsPerElement))), vec.capacity());
        vec = new BitVector(64L);
        Assert.assertEquals(BitVector.BitsPerElement * ((long) Math.ceil(((double) (64L) / BitVector.BitsPerElement))), vec.capacity());
        vec = new BitVector(15331L);
        Assert.assertEquals(BitVector.BitsPerElement * ((long) Math.ceil(((double) (15331L) / BitVector.BitsPerElement))), vec.capacity());
    }
}
