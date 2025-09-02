/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

// you may like to use JUnit testing - please see the Ed Lessons on
// testing for more information.

// Import the DLL
import org.junit.Assert;
import org.junit.Test;
import uq.comp3506.a1.structures.DoublyLinkedList;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class TestDoublyLinkedList {
    public static int seed = 0;
    public static final Random rand = (seed == 0) ? new Random() : new Random(seed);
    public static void testCreateAndSize() {
        DoublyLinkedList<String> dll = new DoublyLinkedList<String>();
        assert(dll.size() == 0);
    }

    public static void testInsertion() {
        // You should implement many of your own tests!
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<Integer>();
        dll.append(10);
        assert(dll.size() == 1);
        Integer old = dll.set(0, 123);
        assert(old == 10);
        assert(dll.get(0) == 123);
        // You should do more stuff here!
    }

    public static void hints() {
        System.out.println("Look at the Ed Lesson on testing!");
        System.out.println("Consider using randomness to do large scale testing!");
    }

    public static void main(String[] args) {
        System.out.println("Testing DoublyLinkedList Class...");
        testCreateAndSize();
        testInsertion();
        System.out.println("Success!");
    }

    public static void areContentsEqual(LinkedList<?> tdll, DoublyLinkedList<?> dll) {
        Assert.assertEquals(tdll.size(), dll.size());

        for (int i = 0; i < tdll.size(); i++) {
            Assert.assertTrue(tdll.get(i).equals(dll.get(i)));
        }
    }

    @Test
    public void testMultipleAppend() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        LinkedList<Integer> tdll = new LinkedList<>();
        areContentsEqual(tdll, dll);
        for (int i = 0; i < 1000; i++) {
            dll.append(i);
            tdll.add(i);
        }
        areContentsEqual(tdll, dll);
    }

    @Test
    public void testIsEmptySimple() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        Assert.assertTrue(dll.isEmpty());
    }

    @Test
    public void testRemoveSingle() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        Assert.assertTrue(dll.isEmpty());
        dll.append(5);
        Assert.assertTrue(dll.get(0) == 5);
        Assert.assertFalse(dll.isEmpty());
        Assert.assertTrue(dll.remove(0) == 5);
        Assert.assertTrue(dll.isEmpty());
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> {dll.get(0);});
    }

    @Test
    public void testRemoveMultiple() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        Assert.assertTrue(dll.isEmpty());
        dll.append(5);
        dll.append(6);
        dll.append(4);
        dll.append(2);
        Assert.assertTrue(dll.get(0) == 5);
        Assert.assertTrue(dll.get(1) == 6);
        Assert.assertTrue(dll.get(2) == 4);
        Assert.assertTrue(dll.get(3) == 2); // 5, 6, 4, 2
        Assert.assertFalse(dll.isEmpty());
        Assert.assertTrue(dll.remove(0) == 5); // 6, 4, 2
        Assert.assertEquals(3, dll.size());
        Assert.assertTrue(dll.remove(0) == 6); // 4, 2
        Assert.assertEquals(2, dll.size());
        dll.append(3);
        dll.append(6);
        dll.append(7); // 4, 2, 3, 6, 7
        Assert.assertTrue(dll.remove(4) == 7); // 4, 2, 3, 6
        Assert.assertEquals(4, dll.size());
        Assert.assertTrue(dll.remove(2) == 3); // 4, 2, 3, 6
        Assert.assertEquals(3, dll.size());
    }

    @Test
    public void testRemoveRandom() {
        int size = 2000;
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        LinkedList<Integer> tdll = new LinkedList<>();
        Stream.generate(rand::nextInt)
                .limit(size).forEach((x) -> {
                    tdll.add(x);
                    dll.append(x);
                });
        areContentsEqual(tdll, dll);
        while (tdll.size() > 0) {
            int pos = rand.nextInt(tdll.size());
            Assert.assertEquals(tdll.remove(pos), dll.remove(pos));
        }
        areContentsEqual(tdll, dll);
    }

    @Test
    public void testRemoveFirstSimple() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        LinkedList<Integer> tdll = new LinkedList<>();
        for (int x : List.of(1,1,2,3,1,2,5)) {
            dll.append(x);
            tdll.add(x);
        }
        areContentsEqual(tdll, dll);
        for (int x : List.of(1,2, 1, 1, 5, 2, 3, 1)) {
            Assert.assertEquals(tdll.remove((Object) x), dll.removeFirst(x));
            areContentsEqual(tdll, dll);
        }
    }

    @Test
    public void testRemoveFirstRandom() {
        int size = 2000;
        int lowBound = 0;
        int highBound = 20;
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        LinkedList<Integer> tdll = new LinkedList<>();
        Stream.generate(() -> rand.nextInt(lowBound, highBound))
                .limit(size).forEach((x) -> {
                    tdll.add(x);
                    dll.append(x);
                });
        areContentsEqual(tdll, dll);
        while (tdll.size() > 0) {
            Integer target = tdll.get(rand.nextInt(tdll.size()));
            Assert.assertEquals(tdll.remove(target), dll.removeFirst(target));
            areContentsEqual(tdll, dll);
        }
    }

    @Test
    public void testGetFirstSimple() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        dll.append(0);
        Assert.assertEquals(0, (int) dll.getFirst());
    }

    @Test
    public void testGetFirstEmpty() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        Assert.assertNull(dll.getFirst());
    }

    @Test
    public void testGetFirstMultiple() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        dll.append(0);
        dll.append(1);
        Assert.assertEquals(0, (int) dll.getFirst());
        dll.remove(0);
        Assert.assertEquals(1, (int) dll.getFirst());
        dll.remove(0);
        Assert.assertNull(dll.getFirst());
    }

    @Test
    public void testGetLastSimple() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        dll.append(0);
        Assert.assertEquals(0, (int) dll.getLast());
    }

    @Test
    public void testGetLastEmpty() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        Assert.assertNull(dll.getLast());
    }

    @Test
    public void testGetLastMultiple() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        dll.append(1);
        dll.append(0);
        Assert.assertEquals(0, (int) dll.getLast());
        dll.remove(1);
        Assert.assertEquals(1, (int) dll.getLast());
        dll.remove(0);
        Assert.assertNull(dll.getLast());
    }


}
