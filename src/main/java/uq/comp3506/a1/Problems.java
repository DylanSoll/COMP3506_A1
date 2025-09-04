// @edu:student-assignment

package uq.comp3506.a1;

// This is part of COMP3506 Assignment 1. Students must implement their own solutions.

import uq.comp3506.a1.structures.DoublyLinkedList;
import uq.comp3506.a1.structures.DynamicArray;

/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */
public class Problems {

    private static class RLEchar {

        private final char ch;
        private int count;

        public RLEchar(char ch) {
            this.ch = ch;
            this.count = 1; // counts from 1 as one instance would have to be found
        }

        public int getCount() {
            return this.count;
        }

        public boolean increment() {
            if (this.count == 9) {
                return false;
            }
            this.count++;
            return true;
        }

        public boolean equals(char o) {
            return this.ch == o;
        }

        @Override
        public String toString() {
            return "" + this.ch + this.count;
        }
    }

    public static double abs(double val) {
        if (val >= 0) {
            return val;
        }
        return -1 * val;
    }

    public static int floorLog(long num) {
        return 63 - Long.numberOfLeadingZeros(num);
    }


    /**
     * Return a string representing the RLE of input
     * <p>
     * Bounds:
     * - Basic tests
     * input will have up to 10 characters
     * - Advanced tests
     * input will have up to 1000 characters
     * - Welcome to COMP3506
     * input will have up to 100'000 characters
     */
    public static String shortRuns(String input) {
        DoublyLinkedList<RLEchar> li = new DoublyLinkedList<>();
        if (input.isEmpty()) {
            return "";
        }
        RLEchar cur = null;
        for (char let : input.toCharArray()) {
            if (cur == null) {
                cur = new RLEchar(let);
                continue;
            }
            if (cur.equals(let)) {
                if (cur.increment()) {
                    continue;
                }
            }
            li.append(cur);
            cur = new RLEchar(let);

        }
        li.append(cur);
        return li.toString();
    }

    /**
     * Return the maximum score that can be achieved within exactly
     * "turns" turns
     * - values in array are guaranteed to be >= 0
     * <p>
     * Bounds:
     * - Basic tests
     * array will consist of up to 100 elements
     * Each element will be up to 100
     * There will be up to 10 turns
     * - Advanced tests
     * array will consist of up to 10'000 elements
     * Each element will be up to 10'000
     * There will be up to 10'000 turns
     * - Welcome to COMP3506
     * array will consist of up to 100'000 elements
     * Each element will be up to 10'000'000
     * There will be up to 10'000'000 turns
     */
    public static long arithmeticRules(Long[] array, long turns) {
        // simplifications
        // non-negative integers, largest possible element is 10_000_000 (before any turns)
        // so it cannot wrap around in 10_000_000 turns
        // the sum from 10_000_000 to 20_000_000 does not cause overflow of long
        if (turns == 0) {
            return 0;
        }
        long max = 0;
        int maxIx = 0;
        for (int ix = 0; ix < array.length; ix++) {
            if (array[ix] > max) {
                max = array[ix];
                maxIx = ix;
                if (max == 10_000_000) {
                    break; // cannot exceed this value before any turns so stop searching
                }
            }
        }
        long score = 0;
        for (int i = 0; i < turns; i++) {
            score += array[maxIx]++;
        }
        return score;
    }

    /**
     * Return the epsilon-approximate square root of "number"
     * - epsilon will be in [0.00001, 1]
     * <p>
     * Bounds:
     * - Basic tests
     * number will be up to 1000
     * - Advanced tests
     * number will be p to 1'000'000
     * - Welcome to COMP3506
     * number will be up to 10**16 (ten to the power 16)
     */
    public static double sqrtHappens(long number, double epsilon) {
        if (number == 1L || number == 0L) {
            return number; // assuming 0 is not tested but check anyway
        }
        double guess = (double) estimateSqrtFloor(number, epsilon);
        double num = (double) number;
        while (abs(number - guess * guess) > epsilon) {
            guess = (guess + num / guess) / 2;
        }
        return guess;
    }

    /**
     * Estimates the lowest
     * @param number
     * @param epsilon
     * @return
     *
     */
    private static long estimateSqrtFloor(long number, double epsilon) {
        /*
        sqrt(x) = x^0.5 = 2^(log2(x^0.5)) = 2^(log2(x)/2)
        Using longs and bitwise operations, a lower bound for sqrt(x) can be achieved with 2^(log2(x)/2)
        An upper bound can be established (for number > 16):
        upper bound = 2^(log2(number)/2 + 1)
        For numbers less than 16, number / 2 can be used as an upper bound
         */
        long numBits = floorLog(number);
        long low = 1L << (numBits >>> 1);
        // can get a low bound for the estimate for sqrt using 2^(log_2(number)/2))
        long high = (numBits <= 4) ? (number >>> 1) : (1L << ((numBits >> 1) + 1)); // higher bound
        long sqr = square(low);
        if (low + 1 >= high) {
            return low;
        }
        long estimate = low;
        while (low + 1 < high) {
            estimate = (low + high) / 2;
            sqr = square(estimate);
            if (sqr == number) {
                return sqr;
            }
            if (sqr < number) {
                low = estimate;
                continue;
            }
            high = estimate;
        }
        return estimate;
    }

    private static long square(long num) {
        return num * num;
    }

    private static double square(double num) {
        return num * num;
    }

    /**
     * Return the largest integer in numbers repeated an odd number of times
     * - values in "numbers" will be in the range [0, 2^32 - 1]
     * <p>
     * Bounds:
     * - Basic tests
     * There will be up to 100 numbers in the array
     * - Advanced tests
     * There will be up to 10'000 numbers in the array
     * - Welcome to COMP3506
     * There will be up to 100'000 numbers in the array
     */
    public static long spaceOddity(Long[] numbers) {
        DynamicArray<Long> arr = new DynamicArray<Long>();
        int odds = 0;
        int index;
        for (Long num : numbers) {
            index = arr.getFirst(num);
            if (index == -1) {
                arr.append(num);
                continue;
            }
            if (index >= odds) {
                arr.swap(index, odds);
                odds++;
            } else {
                arr.swap(index, --odds);
            }
        }
        if (odds == arr.size()) {
            return -1;
        }
        for (int i = 0; i < odds - 1; i++) {
            arr.remove(0); // remove all the evens
        }
        arr.sort(); // then sort and take the largest
        return (long) arr.get(arr.size() - 1);
    }

    /**
     * Return the number of k-freaky numbers in the range [m, n]
     * <p>
     * Bounds:
     * - Basic tests
     * m and n will be up to 1000
     * k will be up to 10
     * - Advanced tests
     * m and n will be up to 1'000'000
     * k will be up to 100
     * - Welcome to COMP3506
     * m and n will be up to 10**15 (ten to the power 15)
     * k will be up to 10'000
     */
    public static long freakyNumbers(long m, long n, long k) {
        DynamicArray<Long> powers = new DynamicArray<>();
        long count = (m == 1) ? 1 : 0;
        if (m > n) {
            return 0;
        }
        long maxPower = (long) Math.floor(logk(k, n));
        long i = 0;

        return 0;
    }

    public static double logk(long k, long x) {
        return Math.log(x) / Math.log(k);
    }

    /**
     * Return the optimal (minimum) cost of breaking the chocolate
     * <p>
     * Bounds:
     * - Basic tests
     * m and n will be up to 5
     * k will be up to 25
     * - Advanced tests
     * m and n will be up to 5
     * k will be up to 25
     * (bounds are the same but test cases are more difficult)
     * - Welcome to COMP3506
     * m and n will be up to 10
     * k will be up to 100
     */
    public static long lifeIsSweet(int m, int n, int k) {

        return -1;
    }
}
