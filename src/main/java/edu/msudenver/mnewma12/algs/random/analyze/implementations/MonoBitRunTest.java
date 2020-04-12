package edu.msudenver.mnewma12.algs.random.analyze.implementations;

import edu.msudenver.mnewma12.algs.random.analyze.StatReport;

import java.util.*;

public class MonoBitRunTest extends AbstractTest {

    public static final String NAME = "2.3 Runs Test";

    public static final String DESCRIPTION = "Examines the amount of sequential, " +
            "equal bits to determine the oscillation between 1's and 0s.";

    private BitSet bits;

    private int MAX;

    private double p;

    public MonoBitRunTest() { super(NAME, DESCRIPTION); }

    @Override
    public StatReport analyze(byte[] bytes) {
        bits = BitSet.valueOf(bytes);
        MAX = bits.length() - 2;

        int v_obs = 1;
        for (int i = 1; i < bits.length(); i++) {
            if (bits.get(i) == bits.get(i + 1)) v_obs++;
        }

        int n = bits.length();
        double oneProportion = ((double) bits.cardinality()) / n;

        double num = Math.abs(
                v_obs - (2 * n * oneProportion *  (1 - oneProportion)));

        double denom = 2 * Math.sqrt(2 * n) * oneProportion * (1 - oneProportion);

        double p = num / denom;

        boolean isRandom = p >= 0.01;

        Map<String, String> headers = new HashMap<String, String>() {{
           put("n", "" + n);
           put("set", "" + oneProportion);
           put("p", "" + p);
           put("isRandom", "" + isRandom);
        }};

        report = "p = " + formatter.format(p)
                + " (" + (isRandom ? "random" : "non-random") + ")";

        // TODO this is common in all implementations, abstract
        return new StatReport(name, description, report, headers);
    }

    public double getP() {
        return p;
    }

    //    private void iterate(int i, int runCount) {
//        assert i != 0; // should ALWAYS start at 1
//
//        if (i >= bits.length()) return;
//
//        if (bits.get(i - 1) == bits.get(i)) {
//            iterate(i + 1, runCount + 1);
//        } else {
//            if (runCount != 0) {
//                runCounts.add(runCount + 1); // plus one because we dont count the first
//            }
//
//            iterate(i + 1, 0);
//        }
//    }

}
