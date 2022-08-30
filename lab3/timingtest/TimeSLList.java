package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int[] NsArray = new int[]{1, 2, 4, 8, 16, 32};
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int M = 10000; // M operations

        for(int n : NsArray){
            // 1. Create an SLList.
            SLList<Integer> sl = new SLList<>();

            // 2. Add N items to the SLList.
            doAddLastNTimes(sl, n);

            // 3. Start the timer.
            Stopwatch sw = new Stopwatch();

            // 4. Perform M getLast operations on the SLList.
            doGetLastNTimes(sl, M);

            // 5.Check the timer. This gives the total time to complete all M operations.
            double timeInSeconds = sw.elapsedTime();


            Ns.addLast(n);
            times.addLast(timeInSeconds);
            opCounts.addLast(M);
        }

        printTimingTable(Ns, times,opCounts);
    }

    private static void doGetLastNTimes(SLList<Integer> sl, int ops){
        for(int i = 0 ; i < ops ; i--){
            sl.getLast();
        }
    }

    private static void doAddLastNTimes(SLList<Integer> sl, int n) {
        for (int i = 0; i < n; i++) {
            sl.addLast(0);
        }
    }


}
