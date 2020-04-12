package edu.msudenver.mnewma12.algs.random.analyze.implementations;

import edu.msudenver.mnewma12.algs.cli.CLI;
import edu.msudenver.mnewma12.algs.random.analyze.StatReport;

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
        int setCount = bits.cardinality();
        int unsetCount = bytes.length - setCount;
        double percentSet = ((double) setCount) / bits.length();

        Map<String, String> headers = new HashMap<String, String>() {{
           put("set", "" + setCount);
           put("unset", "" + unsetCount);
           put("percentSet", "" + percentSet);
           put("n", "" + bits.length());
        }};

        report = "1: " + formatter.format(setCount)
                + " (" + CLI.formatPercent(percentSet) + ")";

//        CLI.echoLn(report);

        return new StatReport(name, description, report, headers);
    }
}
