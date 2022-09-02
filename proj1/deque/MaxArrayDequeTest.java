package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    @Test
    public void maxWithoutComparator() {

        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(getIntComparator());
        int intMaxValue = 10;
        for (int i = 0; i <= intMaxValue; i++) {
            mad.addFirst(i);
        }

        assertEquals(intMaxValue, (int) mad.max());
    }

    @Test
    public void maxWithComparator(){
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(getStringComparator());

        mad.addLast("apple");
        mad.addLast("cat");
        mad.addLast("banana");
        mad.addLast("watermelon");
        mad.addLast("zoo");

        assertEquals("watermelon", mad.max(getStringLengthComparator()));
        assertNotEquals("watermelon", mad.max());
    }

    @Test
    public void randomizedTest(){
        MaxArrayDeque<Integer> correct = new MaxArrayDeque<>(getIntComparator());
        MaxArrayDeque<Integer> broken = new MaxArrayDeque<>(getIntComparator());

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);

                assertEquals(correct.get(broken.size() - 1), broken.get(broken.size() - 1));
            } else if (operationNumber == 1) {
                // size
                assertEquals(correct.size(), broken.size());
            } else if (operationNumber == 2) {
                // getLast
                assertEquals(correct.get(broken.size() - 1), broken.get(broken.size() - 1));
            } else if (operationNumber == 3) {
                // removeLast
                assertEquals(correct.removeLast(), broken.removeLast());
            } else if (correct.size() == 0) {
                // isEmpty
                assertTrue(correct.isEmpty());
                assertTrue(broken.isEmpty());
            }
        }
    }

    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static Comparator<Integer> getIntComparator() {
        return new IntComparator();
    }

    private static class StringComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public static Comparator<String> getStringComparator() {
        return new StringComparator();
    }

    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }

    public static Comparator<String> getStringLengthComparator() {
        return new StringLengthComparator();
    }
}
