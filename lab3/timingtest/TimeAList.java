package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        // TODO: YOUR CODE HERE
        int[] NsArray = new int[]{1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        for(int n : NsArray){
            AList<Integer> al = new AList<>();
            Stopwatch sw = new Stopwatch();
            doAddLastNTimes(al, n);
            double timeInSeconds = sw.elapsedTime();

            Ns.addLast(n);
            times.addLast(timeInSeconds);
            opCounts.addLast(n);
        }

        printTimingTable(Ns, times,opCounts);
    }

    private static void doAddLastNTimes(AList<Integer> al, int n) {
        for (int i = 0; i < n; i++) {
            al.addLast(0);
        }
    }

}
