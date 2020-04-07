package edu.msudenver.mnewma12.algs.random.analyze;


import java.util.Collections;
import java.util.Map;

public class StatReport {

    private final String name;

    private final String description;

    private final Map<String, String> headers;

    private final String report;

    public StatReport(String name, String description, String report) {
        this(name, description, report, Collections.emptyMap());
    }

    public StatReport(String name, String description, String report,
                      Map<String, String> headers)
    {
        this.name = name;
        this.description = description;
        this.headers = headers;
        this.report = report;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getReport() {
        return report;
    }

    @Override
    public String toString() {
        return "StatReport{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", headers=" + headers +
                ", report='" + report + '\'' +
                '}';
    }
}
