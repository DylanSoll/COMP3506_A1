/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

import org.junit.Assert;
import org.junit.Test;
import uq.comp3506.a1.structures.DynamicArray;

public class TestDynamicArray {
    public static void main(String[] args) {
        System.out.println("Testing DynamicArray Class...");
        System.out.println("Success!");
    }

    @Test
    public void TestSize() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        int iter = 20;
        for (int i = 0; i < iter; i++) {
            Assert.assertTrue(arr.append(i * 2));
        }
        Assert.assertEquals(iter, arr.size());
    }

    @Test
    public void TestAppend() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        int iter = 20;
        for (int i = 0; i < iter; i++) {
            Assert.assertTrue(arr.append(i * 2 - 1));
        }
        for (int i = 0; i < iter; i++) {
            Assert.assertEquals(i * 2 - 1, (int) arr.get(i));
        }
    }

    @Test
    public void TestPrepend() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        int iter = 20;
        for (int i = 0; i < iter; i++) {
            Assert.assertTrue(arr.prepend(i * 2 - 1));
        }
        for (int i = iter - 1; i >= 0; i--) {
            Assert.assertEquals(i * 2 - 1, (int) arr.get(iter - i - 1));
        }
    }

}
