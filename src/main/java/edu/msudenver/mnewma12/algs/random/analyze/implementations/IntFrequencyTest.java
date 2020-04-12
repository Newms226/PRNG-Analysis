package edu.msudenver.mnewma12.algs.random.analyze.implementations;

import edu.msudenver.mnewma12.algs.cli.CLI;
import edu.msudenver.mnewma12.algs.random.analyze.StatReport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static edu.msudenver.mnewma12.algs.random.core.Runner.LOG_ROOT;

public class IntFrequencyTest extends AbstractTest {

    public static final String NAME = "Int Frequency Test";

    public static final String DESCRIPTION = "Converts bytes into unsigned ints " +
            "and generates a frequency histogram to analyze distribution of values";

    public IntFrequencyTest() {
        super(NAME, DESCRIPTION);
    }

    @Override
    public StatReport analyze(byte[] bytes) {
        Map<Integer, Integer> numToCount = new HashMap<>(256);

        List<Integer> ints = new ArrayList<Integer>(bytes.length);

        int converted;
        for (int i = 0; i < bytes.length; i++) {
            converted = Byte.toUnsignedInt(bytes[i]);
            ints.add(converted);

            // TODO I know there is an API method that does this. not sure which tho...
            if (numToCount.containsKey(converted)) {
                int old = numToCount.get(converted);
                numToCount.put(converted, old + 1);
            } else {
                numToCount.put(converted, 1);
            }
        }

        double n = bytes.length;

        double sum = ints.stream()
                .mapToInt(i -> i)
                .sum();

        double mean = sum / n;

        Collections.sort(ints);
        int median = ints.get((int) (n / 2));

        Map<String, String> headers = new HashMap<String, String>() {{
           put("n", "" + n);
           put("mean", "" + mean);
           put("median", "" + median);
        }};

        report = "mean: " + formatter.format(mean)
                + "\nmedian: " + formatter.format(median);

        save(ints, numToCount);

        return build(headers);
    }

    private void save(List<Integer> ints, Map<Integer, Integer> numToCount) {
        String intString = ints.stream()
                .map(i -> "" + i)
                .collect(Collectors.joining("\n"));

        String mapString = numToCount.entrySet().stream()
                .map(e -> e.getKey() + "," + e.getValue())
                .collect(Collectors.joining("\n"));

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime date = LocalDateTime.now();
        String timestamp = date.format(formatter);

        File intsFile = new File(LOG_ROOT + timestamp + "_raw.txt");
        try(FileWriter fw = new FileWriter(intsFile);
            BufferedWriter bw = new BufferedWriter(fw))
        {
            bw.write(intString);
            bw.flush();
        } catch (IOException e) {
            CLI.fail("Failed to save raw ints");
        }

        File mapFile = new File(LOG_ROOT + timestamp + "_dist.csv");
        try(FileWriter fw = new FileWriter(mapFile);
            BufferedWriter bw = new BufferedWriter(fw))
        {
            bw.write(mapString);
            bw.flush();
        } catch (IOException e) {
            CLI.fail("Failed to save distribution");
        }
    }
}
