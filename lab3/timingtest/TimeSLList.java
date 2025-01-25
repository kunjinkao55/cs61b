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

    public static Double returntime(SLList<Integer> test,int inx,int M){
        for(int j = 0;j<inx; j++)
        {
            test.addLast(0);
        }
        Stopwatch sw = new Stopwatch();
        for(int j = 0;j<M; j++)
        {test.getLast();}
        return sw.elapsedTime();
    }
    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<Integer>();
        AList<Double> times = new AList<Double>();
        AList<Integer> ops = new AList<Integer>();
        int M = 10000;
        for(int i = 1000;i<=128000; i *=2){
            SLList<Integer> test = new SLList<Integer>();
            Ns.addLast(i);
            ops.addLast(M);
            times.addLast(returntime(test,i,M));
        }
        printTimingTable( Ns,  times, ops);
        return;
    }

}
