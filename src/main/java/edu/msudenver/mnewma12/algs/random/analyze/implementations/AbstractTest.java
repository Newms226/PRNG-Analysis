package edu.msudenver.mnewma12.algs.random.analyze.implementations;

import edu.msudenver.mnewma12.algs.random.analyze.StatReport;

import java.text.NumberFormat;
import java.util.Map;

public abstract class AbstractTest {

    String name;

    String description;

    String report = "ANALYZE NOT CALLED";

    NumberFormat formatter = NumberFormat.getNumberInstance();

    AbstractTest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract StatReport analyze(byte[] bytes);

    StatReport build(Map<String, String> headers) {
        return new StatReport(name, description, report, headers);
    }
}
