package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {

    @Test
    public void randomizedTest() {
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        String msg = "";

        int N = 5000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                solution.addFirst(randVal);
                student.addFirst(randVal);

                msg += "addFirst(" + randVal + ")\n";

                assertEquals(msg, solution.get(0), student.get(0));
            } else if (operationNumber == 1) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                solution.addLast(randVal);
                student.addLast(randVal);

                msg += "addLast(" + randVal + ")\n";

                assertEquals(msg, solution.get(solution.size() - 1), student.get(student.size() - 1));
            } else if (operationNumber == 2 && solution.size() > 0 && student.size() > 0) {
                // removeFirst
                Integer expected = solution.removeFirst();
                Integer actual = student.removeFirst();

                msg += "removeFirst()\n";

                assertEquals(msg, expected, actual);
            } else if (operationNumber == 3 && solution.size() > 0 && student.size() > 0) {
                // removeLast
                Integer expected = solution.removeLast();
                Integer actual = student.removeLast();

                msg += "removeLast()\n";

                assertEquals(msg, expected, actual);
            }
        }

    }
}
