package edu.msudenver.mnewma12.algs.random.analyze.implementations;

import edu.msudenver.mnewma12.algs.cli.CLI;
import edu.msudenver.mnewma12.algs.random.analyze.StatReport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static edu.msudenver.mnewma12.algs.random.core.Runner.LOG_ROOT;

public class PairGenerator extends AbstractTest {


    public static final String NAME = "Pair Generator";

    public static final String DESCRIPTION = "Creates coordinate pairs by " +
            "first converting bytes into signed integers (-127, 127) and then" +
            " combining sequential bytes into pairs.";

    public PairGenerator() {
        super(NAME, DESCRIPTION);
    }

    @Override
    public StatReport analyze(byte[] bytes) {
        List<Integer> ints = new ArrayList<Integer>(bytes.length);

        int x, y;
        StringBuffer buf = new StringBuffer("x,y\n");
        for (int i = 0; i < bytes.length; i += 2) {
            x = bytes[i];
            y = bytes[i + 1];
            buf.append(x + "," + y + "\n");
        }

        save(buf.toString());

        report = "See exported points";

        Map<String, String> headers = Collections.emptyMap();

        return build(headers);
    }

    private void save(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime date = LocalDateTime.now();
        String timestamp = date.format(formatter);

        File pointsFile = new File(LOG_ROOT + "mygen_test4.csv");
        try(FileWriter fw = new FileWriter(pointsFile);
            BufferedWriter bw = new BufferedWriter(fw))
        {
            bw.write(str);
            bw.flush();
        } catch (IOException e) {
            CLI.fail("Failed to save points");
        }
    }
}
