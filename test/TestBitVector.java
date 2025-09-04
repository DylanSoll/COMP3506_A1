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

    @Test
    public void testComp() {
        BitVector vec = new BitVector(30);
        vec.complement();
        for (int i = 0; i < 30; i++) {
            Assert.assertTrue(vec.get(i));
        }
        vec.print();
    }

    @Test
    public void testSetSimple() {
        BitVector vec = new BitVector(30);
        vec.set(0);
        vec.set(2);
        vec.set(4);
        Assert.assertTrue(vec.get(0));
        Assert.assertTrue(vec.get(2));
        Assert.assertTrue(vec.get(4));
        Assert.assertFalse(vec.get(1));
        Assert.assertFalse(vec.get(3));
        Assert.assertFalse(vec.get(5));
    }

    @Test
    public void testSetAboveBitSize() {
        long size = BitVector.BitsPerElement + BitVector.BitsPerElement / 2;
        BitVector vec = new BitVector(size);
        Assert.assertEquals(size, vec.size());
        Assert.assertEquals(BitVector.BitsPerElement *  ((long) Math.ceil(((double) (size) / BitVector.BitsPerElement))), vec.capacity());
        vec.set(0);
        vec.set(2);
        vec.set(4);
        vec.set(6 + BitVector.BitsPerElement);
        vec.set(8 + BitVector.BitsPerElement);
        vec.set(10 + BitVector.BitsPerElement);
        Assert.assertTrue(vec.get(0));
        Assert.assertTrue(vec.get(2));
        Assert.assertTrue(vec.get(4));
        Assert.assertFalse(vec.get(1));
        Assert.assertFalse(vec.get(3));
        for (int i = 5; i < 6 + BitVector.BitsPerElement; i++) {
            Assert.assertFalse(vec.get(i));
        }
        Assert.assertTrue(vec.get(6 + BitVector.BitsPerElement));
        Assert.assertTrue(vec.get(8 + BitVector.BitsPerElement));
        Assert.assertTrue(vec.get(10 + BitVector.BitsPerElement));
        Assert.assertFalse(vec.get(7 + BitVector.BitsPerElement));
        Assert.assertFalse(vec.get(9 + BitVector.BitsPerElement));
        Assert.assertFalse(vec.get(11 + BitVector.BitsPerElement));
    }

    @Test
    public void testUnsetSimple() {
        BitVector vec = new BitVector(30);
        vec.set(0);
        vec.set(2);
        vec.set(4);
        Assert.assertTrue(vec.get(0));
        Assert.assertTrue(vec.get(2));
        Assert.assertTrue(vec.get(4));
        Assert.assertFalse(vec.get(1));
        Assert.assertFalse(vec.get(3));
        Assert.assertFalse(vec.get(5));
        vec.unset(2);
        Assert.assertTrue(vec.get(0));
        Assert.assertTrue(vec.get(4));
        Assert.assertFalse(vec.get(1));
        Assert.assertFalse(vec.get(2));
        Assert.assertFalse(vec.get(3));
        Assert.assertFalse(vec.get(5));
    }

    @Test
    public void testUnsetAboveBitSize() {
        long size = BitVector.BitsPerElement + BitVector.BitsPerElement / 2;
        BitVector vec = new BitVector(size);
        Assert.assertEquals(size, vec.size());
        Assert.assertEquals(BitVector.BitsPerElement *  ((long) Math.ceil(((double) (size) / BitVector.BitsPerElement))), vec.capacity());
        vec.set(0);
        vec.set(2);
        vec.set(4);
        vec.set(6 + BitVector.BitsPerElement);
        vec.set(8 + BitVector.BitsPerElement);
        vec.set(10 + BitVector.BitsPerElement);
        Assert.assertTrue(vec.get(0));
        Assert.assertTrue(vec.get(2));
        Assert.assertTrue(vec.get(4));
        Assert.assertFalse(vec.get(1));
        Assert.assertFalse(vec.get(3));
        for (int i = 5; i < 6 + BitVector.BitsPerElement; i++) {
            Assert.assertFalse(vec.get(i));
        }
        Assert.assertTrue(vec.get(6 + BitVector.BitsPerElement));
        Assert.assertTrue(vec.get(8 + BitVector.BitsPerElement));
        Assert.assertTrue(vec.get(10 + BitVector.BitsPerElement));
        Assert.assertFalse(vec.get(7 + BitVector.BitsPerElement));
        Assert.assertFalse(vec.get(9 + BitVector.BitsPerElement));
        Assert.assertFalse(vec.get(11 + BitVector.BitsPerElement));
        vec.unset(4);
        vec.unset(6 + BitVector.BitsPerElement);
        Assert.assertTrue(vec.get(0));
        Assert.assertTrue(vec.get(2));
        for (int i = 3; i < 8 + BitVector.BitsPerElement; i++) {
            Assert.assertFalse(vec.get(i));
        }
        Assert.assertTrue(vec.get(8 + BitVector.BitsPerElement));
        Assert.assertTrue(vec.get(10 + BitVector.BitsPerElement));
        Assert.assertFalse(vec.get(9 + BitVector.BitsPerElement));
        Assert.assertFalse(vec.get(11 + BitVector.BitsPerElement));
    }

    @Test
    public void testLeftShiftSimple() {
        BitVector vec = new BitVector(24);
        vec.set(0);
        vec.set(2);
        vec.set(4);
        vec.set(5);
        vec.set(9);
        vec.print();
        vec.shift(1);
        vec.print();
        vec.shift(1);
        vec.print();
        vec.shift(5);
        vec.print();
        for (int i = 0; i < 24; i++) {
            vec.shift(1);
            vec.print();
        }
    }

    @Test
    public void testLeftShiftSmall() {
        BitVector vec = new BitVector(2);
        long val = 0b11;
        vec.set(0);
        vec.set(1);
        vec.print();
        vec.shift(1);
        vec.print();
        vec.shift(1);
        vec.print();
        vec.shift(1);
        vec.print();
        vec.shift(-1);
        vec.print();
        vec.shift(-1);
        vec.print();
    }

    @Test
    public void testLeftShiftSkip() {
        BitVector vec = new BitVector(100);
        vec.set(3);
        vec.set(5);
        vec.set(6);
        vec.set(7);
        vec.set(18);
        vec.set(19);
        vec.set(21);
        vec.print();
        vec.shift(1);
        vec.print();
        vec.shift(64);
        vec.print();
        vec.shift(2);
        vec.print();
        for (int i = 0; i < 24; i++) {
            vec.shift(1);
            vec.print();
        }
    }

    @Test
    public void testRightShiftSimple() {
        BitVector vec = new BitVector(24);
        vec.set(24 - 1);
        vec.set(24 - 2);
        vec.set(24 - 4);
        vec.set(24 - 6);
        vec.set(24 - 9);
        vec.set(24 - 10);
        vec.set(24 - 11);
        vec.print();
        vec.shift(-1);
        vec.print();
        vec.shift(-1);
        vec.print();
        vec.shift(-5);
        vec.print();
        for (int i = 0; i < 24; i++) {
            vec.shift(-1);
            vec.print();
        }
    }

    @Test
    public void testRightShiftSkip() {
        BitVector vec = new BitVector(24);
        vec.set(24 - 1);
        vec.set(24 - 2);
        vec.set(24 - 4);
        vec.set(24 - 6);
        vec.set(24 - 9);
        vec.set(24 - 10);
        vec.set(24 - 11);
        vec.print();
        vec.shift(-1);
        vec.print();
        vec.shift(-10);
        vec.print();
        vec.shift(-2);
        vec.print();
        for (int i = 0; i < 24; i++) {
            vec.shift(-1);
            vec.print();
        }
    }

    @Test
    public void testLeftRotateSimple() {
        BitVector vec = new BitVector(24);
        vec.set(23);
        vec.set(22);
        vec.set(20);
        vec.set(1);
        vec.set(2);
        vec.set(3);
        vec.set(5);
        vec.print();
        vec.rotate(1);
        vec.print();
        vec.rotate(1);
        vec.print();
        vec.rotate(1);
        vec.print();
        vec.rotate(1);
        vec.print();
    }
}
