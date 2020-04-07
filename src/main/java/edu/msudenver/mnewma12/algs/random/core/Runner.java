package edu.msudenver.mnewma12.algs.random.core;


import edu.msudenver.mnewma12.algs.cli.CLI;
import edu.msudenver.mnewma12.algs.random.analyze.ByteAnalyzer;
import edu.msudenver.mnewma12.algs.random.analyze.MonobitFreqTest;
import edu.msudenver.mnewma12.algs.random.analyze.StatCollector;
import edu.msudenver.mnewma12.algs.random.generate.LibraryGenerator;
import edu.msudenver.mnewma12.algs.random.generate.RandomGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Runner {

    private static final int BYTE_COUNT = 100_000_000;

    static List<ByteAnalyzer> analyzers = new ArrayList<ByteAnalyzer>(){{
        add(new MonobitFreqTest());
    }};

    static List<RandomGenerator> generators = new ArrayList<RandomGenerator>(){{
        add(new LibraryGenerator());
    }};

    public static void main(String[] args) {
        CLI.echoLn("Random Generator v1.0 ~ Michael Newman");

        String summaryReport = generators.parallelStream()
                .map(Runner::run)
                .map(StatCollector::toString)
                .collect(Collectors.joining("\n"));

        CLI.echoLn(summaryReport);

        String fileName = "firstRun";
        save(summaryReport, fileName);

        CLI.echoLn("File saved @ " + fileName + "\nGoodbye :)");
    }

    private static StatCollector run(RandomGenerator gen) {
        byte[] randomBytes = new byte[BYTE_COUNT];
        gen.nextBytes(randomBytes);

        return analyzers.stream()
                .map(a -> a.analyze(randomBytes))
                .collect(() -> new StatCollector(gen.getName()),
                         StatCollector::add,
                         StatCollector::combine);
    }

    private static void save(String summaryReport, String fileName) {
        File file = new File("/Users/michaelnewman/Library/Mobile Documents/com~apple~CloudDocs/_school/20SP/CS4050/algs/random/logs/" + fileName + ".txt");

        try(FileWriter base = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(base))
        {
            writer.write(summaryReport);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
