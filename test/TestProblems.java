/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

import org.junit.Assert;
import org.junit.Test;
import uq.comp3506.a1.Problems;

import java.util.List;

public class TestProblems {

    // The series of tests that need to be implemented
    public static void testShortRuns() {
        System.out.println("Testing 'Short Runs'");
    }

    public static void testArithmeticRules() {
        System.out.println("Testing 'Arithmetic Rules'");
    }

    public static void testSqrtHappens() {
        System.out.println("Testing 'SQRT Happens'");
    }

    public static void testSpaceOddity() {
        System.out.println("Testing 'Space Oddity'");
    }

    public static void testFreakyNumbers() {
        System.out.println("Testing 'Freaky Numbers'");
    }

    public static void testLifeIsSweet() {
        System.out.println("Testing 'Life is Sweet'");
    }


    // Try to call the given test based on the input
    public static void dispatch(String str) {
        switch (str.toLowerCase()) {
            case "shortruns": 
                testShortRuns();
                return;
            case "arithmetic":
                testArithmeticRules();
                return;
            case "sqrt":
                testSqrtHappens();
                return;
            case "space":
                testSpaceOddity();
                return;
            case "freaky":
                testFreakyNumbers();
                return;
            case "sweet":
                testLifeIsSweet();
                return;
            default:
                throw new IllegalArgumentException("Unknown command: " + str);
        }
    }

    // Does what it says on the tin 
    private static void usage() {
        System.out.println("Usage: java TestProblems <commands>");
        System.out.println("Commands:");
        System.out.println("  shortruns");
        System.out.println("  arithmetic");
        System.out.println("  sqrt");
        System.out.println("  space");
        System.out.println("  freaky");
        System.out.println("  sweet");
    }

    public static void main(String[] args) {
        
        // Basic checking - make sure a command is provided
        if (args.length == 0) {
            usage();
            return;
        }

        // Walk the commands and try to dispatch them
        for (int i = 0; i < args.length; ++i) {
            dispatch(args[i]);
        }

        // profit??
    }

    @Test
    public void testShortRunsSimple() {
        Assert.assertEquals("H1E1L2O4", Problems.shortRuns("HELLOOOO"));
        Assert.assertEquals("A8", Problems.shortRuns("AAAAAAAA"));
        Assert.assertEquals("V1E1R1Y1S1A1D1", Problems.shortRuns("VERYSAD"));
        Assert.assertEquals("A9A3", Problems.shortRuns("AAAAAAAAAAAA"));
    }

    @Test
    public void testSqrtHappensSimple() {
        Problems.sqrtHappens(1L, 0.0001);
        Problems.sqrtHappens(12063L, 0.0001);
        Problems.sqrtHappens(623347L, 0.0001);
        Problems.sqrtHappens(730859195L, 0.0001);
    }

    @Test
    public void testSpaceOdditySimple() {
        Long[] arr = new Long[] {1L,5L,2L,4L,6L,5L,1L,5L,5L,2L,5L};
        Assert.assertEquals(6, Problems.spaceOddity(arr)); // 6
        arr = new Long[] {1L,1L,5L,5L,2L,3L,2L,3L};
        Assert.assertEquals(-1, Problems.spaceOddity(arr)); // 6
        arr = new Long[] {9L,9L,1L,5L,1L,9L,1L,9L};
        Assert.assertEquals(5, Problems.spaceOddity(arr)); // 6

    }

    @Test
    public void testFreakySimple() {
        Assert.assertEquals(0L, Problems.freakyNumbers(1, 1, 1));
        // 1
        Assert.assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).size(), Problems.freakyNumbers(1, 10, 2));
        // 0001, 0010, 0011, 0100, 0101, 0110, 0111, 1000, 1001, 1010
        // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        Assert.assertEquals(List.of(3, 4, 5, 6, 7, 8, 9, 10).size(), Problems.freakyNumbers(3, 10, 2));
        // 0001, 0010, 0011, 0100, 0101, 0110, 0111, 1000, 1001, 1010
        // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        Assert.assertEquals(5, Problems.freakyNumbers(1, 10, 3));
        // 3^0 [1], 3^1 [3], 3^0 + 3^1 [4], 3^2 [9], 3^2 + 3^0 [10]

    }

}
