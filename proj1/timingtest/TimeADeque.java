package timingtest;

import deque.ArrayDeque;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class TimeADeque {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        int[] NsArray = new int[]{1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 256000};
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        for (int n : NsArray) {
            ArrayDeque<Integer> ad = new ArrayDeque<>();

            Stopwatch sw = new Stopwatch();
            randomized(ad, n);
            double timeInSeconds = sw.elapsedTime();

            Ns.addLast(n);
            times.addLast(timeInSeconds);
            opCounts.addLast(n);
        }

        printTimingTable(Ns, times, opCounts);
    }

    private static void randomized(ArrayDeque<Integer> ad, int n) {
        for (int i = 0; i < n; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addFirst
                ad.addFirst(i);
            } else if (operationNumber == 1) {
                // addLast
                ad.addLast(i);
            } else if (operationNumber == 2) {
                // removeFirst
                ad.removeFirst();
            } else if (operationNumber == 3) {
                // removeLast
                ad.removeLast();
            }
        }
    }
}
