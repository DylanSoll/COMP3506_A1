/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

import org.junit.Assert;
import org.junit.Test;
import uq.comp3506.a1.DNAStructure;
import uq.comp3506.a1.structures.DynamicArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class TestDNAStructure {
    public static int seed = 0;
    public static final Random rand = (seed == 0) ? new Random() : new Random(seed);
    private static final char[] letters = new char[] {'A', 'C', 'G', 'T'};
    public static void main(String[] args) {
        System.out.println("Testing DNAStructure Class...");
        System.out.println("Success!");
    }

    @Test
    public void testDNAStructureCreation() {
        DNAStructure dna = new DNAStructure(10);
    }

    @Test
    public void testDNAStructureSlideSimple() {
        DNAStructure dna = new DNAStructure(10);
        dna.slide('A');
        dna.slide('C');
        dna.slide('G');
        dna.slide('T');
        dna.slide('A');
        dna.slide('C');
        System.out.println(dna.stringify());
        Assert.assertEquals("ACGTAC", dna.stringify());
    }

    @Test
    public void testDNAStructureSlideOver() {
        DNAStructure dna = new DNAStructure(10);
        for (char ch : "ACGTACATC".toCharArray()) {
            dna.slide(ch);
        }
        Assert.assertEquals("ACGTACATC", dna.stringify());
        dna.slide('T');
        dna.slide('T');
        dna.slide('A');
        Assert.assertEquals("GTACATCTTA", dna.stringify());
    }

    @Test
    public void testDNAStructureCountSimple() {
        DNAStructure dna = new DNAStructure(10);
        for (char ch : "ACGTACATC".toCharArray()) {
            dna.slide(ch);
        }
        Assert.assertEquals("ACGTACATC", dna.stringify());
        Assert.assertEquals(3, dna.count('A'));
        Assert.assertEquals(3, dna.count('C'));
        Assert.assertEquals(1, dna.count('G'));
        Assert.assertEquals(2, dna.count('T'));
    }

    private char randomChar() {
        return letters[rand.nextInt(0, 4)];
    }

    @Test
    public void testDNAStructureCountRand() {
        DNAStructure dna = new DNAStructure(100);
        for (char ch : Stream.generate(this::randomChar).limit(2000).toList()) {
            dna.slide(ch);
        }
        int[] counts = new int[4];
        StringJoiner joiner = new StringJoiner("");
        for (char ch : Stream.generate(this::randomChar).limit(100).toList()) {
            dna.slide(ch);
            joiner.add(String.valueOf(ch));
            switch (ch) {
                case 'A' -> counts[0]++;
                case 'C' -> counts[1]++;
                case 'G' -> counts[2]++;
                case 'T' -> counts[3]++;
            }
        }

        Assert.assertEquals(joiner.toString(), dna.stringify());
        Assert.assertEquals(counts[0], dna.count('A'));
        Assert.assertEquals(counts[1], dna.count('C'));
        Assert.assertEquals(counts[2], dna.count('G'));
        Assert.assertEquals(counts[3], dna.count('T'));
    }

    @Test
    public void testDNAStructureHasPalindromeSimple() {
        DNAStructure dna = new DNAStructure(8);
        for (char ch : "AGTCA".toCharArray()) {
            dna.slide(ch);
        }
        Assert.assertTrue(dna.hasPalindrome(3));
        for (char ch : "AAAAAAAAAAA".toCharArray()) {
            dna.slide(ch);
        }
        Assert.assertFalse(dna.hasPalindrome(2));
        for (char ch : "CCTAGGT".toCharArray()) {
            dna.slide(ch);
        }
        Assert.assertTrue(dna.hasPalindrome(4));
    }
}
