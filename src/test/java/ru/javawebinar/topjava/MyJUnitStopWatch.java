package ru.javawebinar.topjava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

public class MyJUnitStopWatch extends Stopwatch {
    private static List<String> testStatistics = new ArrayList<>();

    private static void logInfo(Description description, String status, long nanos) {
        String testName = description.getMethodName();
        String testStat = String.format("Test %s %s, spent %d microseconds",
                testName, status, TimeUnit.NANOSECONDS.toMillis(nanos));
        testStatistics.add(testStat);
        System.out.println(testStat);
    }

    public static List<String> getTestStatistics() {
        return testStatistics;
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, "finished", nanos);
    }
}