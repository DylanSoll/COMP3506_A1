/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

import uq.comp3506.a1.Problems;

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

}
