package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    /**
     *  Writing another test by yourself
     *
     *  Finding bugs: It would stop iterate when encounter prime number.
     *  If there are at least two prime numbers, the result would be wrong.
     */
    @Test
    public void testSquarePrimes() {
        IntList lst = IntList.of(17, 101);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("289 -> 10201", lst.toString());
        assertTrue(changed);
    }
}
