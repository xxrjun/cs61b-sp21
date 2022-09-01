package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    @Test
    public void maxWithoutComparator() {

        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(MaxArrayDeque.getIntComparator());
        int intMaxValue = 10;
        for (int i = 0; i <= intMaxValue; i++) {
            mad.addFirst(i);
        }

        assertEquals(intMaxValue, (int) mad.max());
    }

    @Test
    public void maxWithComparator(){
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(MaxArrayDeque.getStringComparator());

        mad.addLast("apple");
        mad.addLast("cat");
        mad.addLast("banana");
        mad.addLast("watermelon");
        mad.addLast("zoo");

        assertEquals("watermelon", mad.max(MaxArrayDeque.getStringLengthComparator()));
        assertNotEquals("watermelon", mad.max());
    }

    @Test
    public void randomizedTest(){
        MaxArrayDeque<Integer> correct = new MaxArrayDeque<>(MaxArrayDeque.getIntComparator());
        MaxArrayDeque<Integer> broken = new MaxArrayDeque<>(MaxArrayDeque.getIntComparator());

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
}
