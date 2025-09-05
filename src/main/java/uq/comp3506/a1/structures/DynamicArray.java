// @edu:student-assignment

package uq.comp3506.a1.structures;

/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 *
 * NOTE: You should go and carefully read the documentation provided in the
 * ListInterface.java file - this explains some of the required functionality.
 */
public class DynamicArray<T extends Comparable<T>> implements ListInterface<T> {
    /**
     * Default capacity of the dynamic array
     */
    private static final int INIT_CAPACITY = 8;

    /**
     * size tracks the total number of slots being used in the data array
     */
    private int size = 0;

    /**
     * capacity tracks the total number of slots (used or unused) in the data array
     */
    private int capacity = 0;

    /**
     * data stores the raw objects
     */
    private T[] data;

    /**
     * The offset for index 0 to allow for O(1*) prepending
     * Default is half the initial capacity to allow for equal prepend and append
     */
    private int start;

    /**
     * Constructs an empty Dynamic Array
     */
    public DynamicArray() {
        clear(); // clear reinitialises the array to its default initial parameters
    }

    // See ListInterface
    @Override
    public int size() {
        return this.size;
    }

    // See ListInterface
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Has the size reached the current capacity?
     * Return true if so, false otherwise.
     * This is merely a convenience function for you. We will not be
     * testing it explicitly.
     */
    public boolean isFull() {
        return this.size == capacity;
    }

    /**
     * Get current capacity.
     * Again, this is merely a convenience function for you. We will not
     * be testing it explicitly.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Add an element to the end of the array. Returns true if successful,
     * false otherwise.
     * Time complexity for full marks: O(1*)
     * That is, O(1) *amortized*.
     */
    @Override
    public boolean append(T element) {
        if (isFull() || (this.size + start + 1 >= capacity)) {
            resize(capacity * 2);
        }
        data[start + this.size] = element;
        this.size++;
        return true;
    }

    /**
     * Add an element to the beginning of the array. Returns true if successful,
     * false otherwise.
     * Time complexity for full marks: O(1*)
     * Again, O(1*) means constant amortized time.
     */
    @Override
    public boolean prepend(T element) {
        if (isStartFull() || isFull()) {
            resize(capacity * 2);
        }
        data[start - 1] = element;
        --start;
        this.size++;
        return true;
    }

    /**
     * Add element to index ix.
     * Note: This does not overwrite the element at index ix - that is what
     * the set() method is for, see below. Instead, this function is similar
     * to append or prepend, but it adds the element at a desired index.
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Acceptable bounds are [0, size] where 0 will be prepend, size will
     * be append, and anything in between will need to shuffle elements around.
     * Time complexity for full marks: O(N)
     */
    @Override
    public boolean add(int ix, T element) {
        if (ix == 0) {
            prepend(element);
            return true;
        }
        if (ix == this.size) {
            append(element);
            return true;
        }
        checkBounds(ix, false);

        if (isFull()) {
            resize(capacity * 2);
        }
        int pos = ix + start;
        if (ix <= (this.size / 2)) {
            if (isStartFull()) {
                resize(capacity * 2);
                pos = ix + start;
            }
            for (int i = --start; i < pos; i++) {
                data[i] = data[i + 1];
            }
            data[pos - 1] = element;
        } else {
            if (isEndFull()) {
                resize(capacity * 2);
                pos = ix + start;
            }
            for (int i = size + start; i > pos; i--) {
                data[i] = data[i - 1];
            }
            data[pos] = element;
        }
        size++;
        return true;
    }

    /**
     * Return the element at index ix.
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Time complexity for full marks: O(1)
     */
    @Override
    public T get(int ix) {
        checkBounds(ix, true);
        return data[start + ix];
    }

    /**
     * Overwrite the "old" value at ix with element, and return the old value.
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Time complexity for full marks: O(1)
     */
    @Override
    public T set(int ix, T element) {
        checkBounds(ix, true);
        T old = data[start + ix];
        data[start + ix] = element;
        return old;
    }

    /**
     * Remove and return the value at index ix
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Time complexity for full marks: O(N)
     */
    @Override
    public T remove(int ix) {
        checkBounds(ix, true);
        int pos = ix + start;
        T value = data[pos];
        data[pos] = null;
        if (ix == 0 || ix == this.size) {
            this.size--;
            if (ix == 0) {
                this.start++;
            }
            return value;
        }
        if (ix >= (this.size / 2)) {
            for (int i = pos + 1; i < this.size + start; i++) {
                data[i - 1] = data[i];
            }
            data[this.size + start - 1] = null;
        } else {
            for (int i = pos; i > 0; i--) {
                data[i] = data[i - 1];
            }
            data[start++] = null;
        }
        size--;
        return value;
    }

    /**
     * Find and remove the first value in the array that equals t (the one
     * with the smallest index).
     * Return true if successful, false otherwise.
     * Time complexity for full marks: O(N)
     */
    @Override
    public boolean removeFirst(T t) {
        if (size == 0) {
            return false;
        }
        boolean found = false;
        int ix = 0;
        for (; ix < this.size; ix++) {
            if (t.equals(data[ix + start])) {
                found = true;
                break;
            }
        }
        if (!found) {
            return false;
        }
        remove(ix);
        return true;
    }

    @Override
    public void clear() {
        this.size = 0;
        capacity = INIT_CAPACITY;
        data = (T[]) new Comparable[capacity];
        start = capacity / 2;
    }

    /**
     * Sort all of the elements inside the array.
     *
     * Time complexity for full marks: O(NlogN).
     * That is, we expect you to implement a sorting algorithm that runs in
     * "n log n" time. This may be in expectation, or guaranteed worst case.
     *
     * A note on comparisons:
     *
     * You may assume that any type stored inside the DynamicArray already
     * implements Comparable<T> which means you can just use compareTo()
     * in order to sort elements.
     *
     * We will assume sorting in ascending, so you will want to do something
     * like: if (data[i].compareTo(data[j]) < 0) { // data[i] < data[j] }
     */
    public void sort() {
        quickSort(start, start + size - 1);
    }

    private boolean resize(int newCapacity) {
        if (newCapacity <= this.size) {
            return false; // do not remove data
        }
        capacity = newCapacity;
        int newStart = capacity / 2;
        if (start >= 2) {
            newStart = start;
        }
        T[] newData = (T[]) new Comparable[capacity];

        for (int i = 0; i < this.size; i++) {
            newData[newStart + i] = data[start + i];
        }
        data = newData;
        start = newStart;
        return true;
    }

    /**
     * Checks to see if the index provided is within bounds, and if not, throws IndexOutOfBoundsException
     * @param ix the index to check if within bounds
     * @param exclusive whether the upper bound is exclusive or not, ix \in [0, size) {true} || ix \in [0, size] {false}
     * @throws IndexOutOfBoundsException if ix < 0 or ix > size or (ix == size and exclusive == true)
     */
    private void checkBounds(int ix, boolean exclusive) throws IndexOutOfBoundsException {
        if (ix < 0 || ix >= (this.size + ((exclusive) ? 0 : 1))) {
            throw new IndexOutOfBoundsException("Index " + ix + " is out of bounds.");
        }
    }

    public void print() {
        System.out.print("[");
        for (int i = 0; i < this.size; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(data[start + i]);
        }
        System.out.print("]\n");
    }

    public static void main(String[] args) {
        DynamicArray<Integer> arr = new DynamicArray<>();
        for (int i = 0; i < 20; i++) {
            arr.append(i * 3 - 1);
        }
        arr.print();
        for (int i = 0; i < 10; i++) {
            System.out.println(arr.remove(0));
            arr.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(arr.remove(arr.size() - 1));
            arr.print();
        }
        arr.print();
    }

    private boolean isStartFull() {
        return start == 0;
    }

    private boolean isEndFull() {
        return this.size + start == capacity;
    }

    private void quickSort(int low, int high) {
        if (high <= low) {
            return;
        }
        int pivot = (low + high) / 2; // not random pivot but should work

        T x = data[pivot];
        int l = low;
        int h = high;
        do {
            while (data[low].compareTo(x) < 0) {
                low++;
            }
            while (data[high].compareTo(x) > 0) {
                high--;
            }
            if (low <= high) {
                T temp = data[low];
                data[low] = data[high];
                data[high] = temp;
                low++;
                high--;
            }
        } while (low <= high);

        if (l < high) {
            quickSort(l, high);
        }
        if (low < h) {
            quickSort(low, h);
        }
    }

    public boolean isSorted() {

        if (isEmpty()) {
            return true;
        }
        T prev = data[start];
        for (int i = start + 1; i < size + start; i++) {
            if (data[i].compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the index of the first occurrence. Returns -1 if not in array
     * @param t the value to search for
     * @return the index of the first occurrence of the value or -1 if not in array
     */
    public int getFirst(T t) {
        if (isEmpty()) {
            return -1;
        }
        for (int ix = start; ix < start + size; ix++) {
            if (data[ix].compareTo(t) == 0) {
                return ix - start;
            }
        }
        return -1;
    }

    /**
     * Swaps the values at index ixa and ixb
     * @param ixa first index to swap
     * @param ixb second index to swap
     */
    public void swap(int ixa, int ixb) {
        checkBounds(ixa, true);
        checkBounds(ixb, true);
        if (ixa == ixb) {
            return;
        }
        ixa += start;
        ixb += start;
        T temp = data[ixa];
        data[ixa] = data[ixb];
        data[ixb] = temp;
    }

    @Override
    public String toString() {
        String[] strs = new String[size];
        for (int i = 0; i < size; i++) {
            strs[i] = data[start + i].toString();
        }
        return String.join("", strs);
    }

    /**
     * Converts the array to a string, separated by a delimiter
     * @param delim the delimiter
     * @return The string of all elements as strings separated by delim
     */
    public String toString(String delim) {
        String[] strs = new String[size];
        for (int i = 0; i < size; i++) {
            strs[i] = data[start + i].toString();
        }
        return String.join(delim, strs);
    }
}
