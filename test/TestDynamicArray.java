/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

import org.junit.Assert;
import org.junit.Test;
import uq.comp3506.a1.structures.DynamicArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class TestDynamicArray {
    private final Random rand = new Random();

    private static void areContentsEqual(List<?> testArr, DynamicArray<?> arr) {
        Assert.assertEquals(testArr.size(), arr.size());
        for (int i = 0; i < testArr.size(); i++) {
            Assert.assertTrue(arr.get(i).equals(testArr.get(i)));
        }
    }

    @Test
    public void TestSize() {
        int size = 150;
        DynamicArray<Integer> arr = new DynamicArray<>();
        ArrayList<Integer> testArr = new ArrayList<>(60);
        Stream.generate(rand::nextInt)
                .limit(size).forEach((x) -> {
                    arr.append(x);
                    testArr.add(x);
                });
        Assert.assertEquals(size, arr.size());
    }

    @Test
    public void TestAppend() {
        int size = 150;
        DynamicArray<Integer> arr = new DynamicArray<>();
        ArrayList<Integer> testArr = new ArrayList<>(60);
        Stream.generate(rand::nextInt)
                .limit(size).forEach((x) -> {
                    arr.append(x);
                    testArr.add(x);
        });
        areContentsEqual(testArr, arr);
    }

    @Test
    public void TestPrepend() {
        int size = 150;
        DynamicArray<Integer> arr = new DynamicArray<>();
        ArrayList<Integer> testArr = new ArrayList<>(60);
        Stream.generate(rand::nextInt)
                .limit(size).forEach((x) -> {
                    arr.prepend(x);
                    testArr.addFirst(x);
                });
        areContentsEqual(testArr, arr);
    }

    @Test
    public void TestPrependAndAppend() {
        int size = 150;
        DynamicArray<Integer> arr = new DynamicArray<>();
        ArrayList<Integer> testArr = new ArrayList<>();
        for (int i = 5; i > 0; i--) {
            Stream.generate(rand::nextInt)
                    .limit(size / i).forEach((x) -> {
                        arr.prepend(x);
                        testArr.addFirst(x);
                    });
            Stream.generate(rand::nextInt)
                    .limit(size / i).forEach((x) -> {
                        arr.append(x);
                        testArr.add(x);
                    });
        }
        areContentsEqual(testArr, arr);
    }

    @Test
    public void TestGet() {
        int size = 150;
        DynamicArray<Integer> arr = new DynamicArray<>();
        ArrayList<Integer> testArr = new ArrayList<>();
        for (int i = 5; i > 0; i--) {
            Stream.generate(rand::nextInt)
                    .limit(size / i).forEach((x) -> {
                        arr.prepend(x);
                        testArr.addFirst(x);
                    });
        }
        // ensures get returns the correct element and have the same size
        areContentsEqual(testArr, arr);
        int currSize = testArr.size();
        int testIndices[] = {-1, currSize, currSize + 1, currSize * currSize};
        for (int ix: testIndices) {
            Assert.assertThrows(IndexOutOfBoundsException.class, () -> {arr.get(ix);});
        }
    }

    @Test
    public void TestRemove() {
        for (int i = 1; i <= 10; i++) {
            int size = 150 * i;
            DynamicArray<Integer> arr = new DynamicArray<>();
            ArrayList<Integer> testArr = new ArrayList<>();
            Stream.generate(rand::nextInt)
                    .limit(size).forEach((x) -> {
                        arr.append(x);
                        testArr.add(x);
                    });
            // ensures get returns the correct element and have the same size
            areContentsEqual(testArr, arr);
            int minSize = rand.nextInt(testArr.size() - 1);

            while (testArr.size() > minSize) {
                int ix = rand.nextInt(Math.max(testArr.size() - 1, 0));
                Assert.assertEquals(testArr.remove(ix), arr.remove(ix));
            }
            Assert.assertEquals(testArr.size(), arr.size());
        }
    }

    @Test
    public void TestAdd() {
        int size = 10_000;
        DynamicArray<Integer> arr = new DynamicArray<>();
        ArrayList<Integer> testArr = new ArrayList<>();

        Stream.generate(() -> {return rand.nextInt(50);})
                .limit(size).forEach((x) -> {
                    Stream<Integer> ixGen = Stream.generate(()-> {
                        return rand.nextInt(0, testArr.size() + 1);
                    });
                    int ix = ixGen.iterator().next();

                    testArr.add(ix, x);
                    arr.add(ix, x);

                });
        areContentsEqual(testArr, arr);
        //arr.print();
    }

    @Test
    public void TestRemoveFirst() {
        for (int i = 1; i <= 10; i++) {
            int size = 100 * i;
            DynamicArray<Integer> arr = new DynamicArray<>();
            ArrayList<Integer> testArr = new ArrayList<>();
            Stream.generate(() -> {return rand.nextInt(50);})
                    .limit(size).forEach((x) -> {
                        arr.append(x);
                        testArr.add(x);
                    });
            // ensures get returns the correct element and have the same size
            areContentsEqual(testArr, arr);
            int minSize = rand.nextInt(0, testArr.size() / 2);

            while (testArr.size() > minSize) {
                int ix = rand.nextInt(0, testArr.size());
                int pos = testArr.indexOf(testArr.get(ix));
                Assert.assertTrue(arr.removeFirst(testArr.remove(pos)));
                areContentsEqual(testArr, arr);
            }
            Assert.assertEquals(testArr.size(), arr.size());
        }
    }

    @Test
    public void testSort() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        ArrayList<Integer> tarr = new ArrayList<>();
        for (int x : List.of(0, 1, 5, 7, 12, 1, 5, 2, 1, 6, 2, 6, 3, 6)) {
            arr.append(x);
            tarr.add(x);
        }
        tarr.sort(Integer::compareTo);
        arr.sort();
        areContentsEqual(tarr, arr);
    }

    @Test
    public void testSortSimple() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        ArrayList<Integer> tarr = new ArrayList<>();
        for (int x : List.of(4,5,-1, -2, 0,1,2,3,6,7,12,11,9,8,10,13)) {
            arr.append(x);
            tarr.add(x);
        }
        areContentsEqual(tarr, arr);
    }

    @Test
    public void testSortRand() {
        DynamicArray<Integer> arr = new DynamicArray<>();
        ArrayList<Integer> tarr = new ArrayList<>();
        Stream.generate(rand::nextInt).limit(200).forEach((x) -> {
            arr.append(x);
            tarr.add(x);
        });
        areContentsEqual(tarr, arr);
    }
}
