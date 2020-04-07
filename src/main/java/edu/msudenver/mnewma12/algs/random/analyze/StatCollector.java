package edu.msudenver.mnewma12.algs.random.analyze;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StatCollector {

    private final String name;

    List<StatReport> reports = new ArrayList<>();

    public StatCollector(String name) {
        this.name = name;
    }

    public void add(StatReport report) { reports.add(report); }

    public StatCollector combine(StatCollector o) {
        this.reports.addAll(o.reports);
        return this;
    }

    @Override
    public String toString() {
        return "GENERATOR: " + name.toUpperCase() + "\n\n" +
                reports.stream()
                .map(r -> "Test: " + r.getName() + "\n" + r.getReport()
                        + "\n-------------------------------------------\n")
                .collect(Collectors.joining());
    }
}
