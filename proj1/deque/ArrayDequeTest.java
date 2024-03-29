package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void resizeTest() {
        Deque<Integer> ad = new ArrayDeque<>();

        for (int i = 0; i < 100; i++) {
            ad.addLast(i);
        }

        for (double i = 0; i < 50; i++) {
            assertEquals("Should have the same value", i, (double) ad.removeFirst(), 0.0);
        }
    }

    @Test
    public void differentDequeTypeNotEqualsTest() {
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(10);
        lld.addLast(20);
        lld.addLast(25);

        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(10);
        ad.addLast(15);
        ad.addLast(25);

        assertFalse(lld.equals(ad));
        assertFalse(ad.equals(lld));
    }

    @Test
    public void differentDequeTypeEqualsTest() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(10);
        lld.addLast(15);
        lld.addLast(25);

        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(10);
        ad.addLast(15);
        ad.addLast(25);

        assertTrue(ad.equals(lld));
        assertTrue(lld.equals(ad));


        LinkedListDeque<String> lld2 = new LinkedListDeque<>();
        lld2.addLast("a");
        lld2.addLast("b");
        lld2.addLast("c");

        ArrayDeque<String> ad2 = new ArrayDeque<>();
        ad2.addLast("a");
        ad2.addLast("b");
        ad2.addLast("c");

        assertTrue(ad2.equals(lld2));
        assertTrue(lld2.equals(ad2));
    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(10);
        ad1.addLast(15);
        ad1.addLast(25);

        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        ad2.addLast(10);
        ad2.addLast(15);
        ad2.addLast(25);

        assertTrue(ad1.equals(ad2));

    }

    @Test
    public void removeEmptyTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    public void bigADequeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        for (int i = 0; i < 10000; i++) {
            ad1.addLast(i);
        }

        for (double i = 0; i < 5000; i++) {
            assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
        }

        for (double i = 9999; i > 5000; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
        }

    }

    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> correct = new ArrayDeque<>();
        ArrayDeque<Integer> broken = new ArrayDeque<>();


        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 7);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);

                assertEquals(correct.get(broken.size() - 1), broken.get(broken.size() - 1));
            } else if (operationNumber == 1) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                correct.addFirst(randVal);
                broken.addFirst(randVal);

                assertEquals(correct.get(0), broken.get(0));
            } else if (operationNumber == 2) {
                // size
                assertEquals(correct.size(), broken.size());
            } else if (operationNumber == 3) {
                // getLast
                assertEquals(correct.get(broken.size() - 1), broken.get(broken.size() - 1));
            } else if (operationNumber == 4) {
                // removeLast
                assertEquals(correct.removeLast(), broken.removeLast());
            } else if (operationNumber == 5) {
                // removeFirst
                assertEquals(correct.removeFirst(), broken.removeFirst());
            } else if (operationNumber == 6) {
                // isEmpty
                if (correct.size() == 0) {
                    assertTrue(correct.isEmpty());
                    assertTrue(broken.isEmpty());
                } else {
                    assertFalse(correct.isEmpty());
                    assertFalse(broken.isEmpty());
                }
            }
        }
    }
}
