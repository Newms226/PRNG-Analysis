package edu.msudenver.mnewma12.algs.random.analyze.implementations;

import edu.msudenver.mnewma12.algs.cli.CLI;
import edu.msudenver.mnewma12.algs.random.analyze.StatReport;
import org.apache.commons.math3.special.Erf;


import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * See NIST 2.1
 */
public class MonobitFreqTest extends AbstractTest {

    static String name = "2.1 Frequency (Monobit) Test";

    static String description = "Counts the proportions of set bits (1's) across" +
            " the entire array.";

    public MonobitFreqTest() {
        super(name, description);
    }

    public StatReport analyze(byte[] bytes) {
//        CLI.echoLn("2.1 Test running");

        BitSet bits = BitSet.valueOf(bytes);

        int n = bits.length();
        int setCount = bits.cardinality();
        int unsetCount = n - setCount;
        double percentSet = ((double) setCount) / bits.length();

        double s_n = Math.abs(setCount - unsetCount);

        double s_obs = s_n / Math.sqrt(n);

        double p = Erf.erfc(s_obs / Math.sqrt(2));

        boolean isRandom = p >= .01;

        Map<String, String> headers = new HashMap<String, String>() {{
           put("set", "" + setCount);
           put("unset", "" + unsetCount);
           put("percentSet", "" + percentSet);
           put("n", "" + n);
           put("p", "" + p);
           put("isRandom", "" + isRandom);
        }};


        report = "1: " + formatter.format(setCount)
                + " (" + CLI.formatPercent(percentSet) + ")"
                + "\np: " + p + " (" + (isRandom ? "random" : "non-random") + ")";

//        CLI.echoLn(report);

        return build(headers);
    }
}
