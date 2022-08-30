package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        BuggyAList<Integer> correct = new BuggyAList<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        correct.addLast(5);
        correct.addLast(10);
        correct.addLast(15);

        broken.addLast(5);
        broken.addLast(10);
        broken.addLast(15);

        assertEquals(correct.size(), broken.size());

        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());

    }

    @Test
    public void randomizedTest(){
        BuggyAList<Integer> L = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);

                /**
                 *  PRINT STATEMENT CODE SHOULD BE REMOVED
                 */
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();

                /**
                 *  PRINT STATEMENT CODE SHOULD BE REMOVED
                 */
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                // getLast

                /**
                 *  PRINT STATEMENT CODE SHOULD BE REMOVED
                 */
                if(L.size() > 0) {
                    System.out.println("getLast(): " + L.getLast());
                }
                
            } else if (operationNumber == 3) {
                // removeLast

                /**
                 *  PRINT STATEMENT CODE SHOULD BE REMOVED
                 */
                if(L.size() > 0){
                    System.out.println("removeLast(): " + L.removeLast());
                }
            }
        }
    }
}
