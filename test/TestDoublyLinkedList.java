/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

// you may like to use JUnit testing - please see the Ed Lessons on
// testing for more information.

// Import the DLL
import uq.comp3506.a1.structures.DoublyLinkedList;


public class TestDoublyLinkedList {

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
}
