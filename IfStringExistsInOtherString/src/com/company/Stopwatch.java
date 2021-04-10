package com.company;

public class Stopwatch {
    private static long start;

    private Stopwatch() {
    }

    public static void start() {
        start = System.currentTimeMillis();
    }

    public static void stop() {
        long now = System.currentTimeMillis();
        System.out.println("This stopwatch lasted " + (System.currentTimeMillis() - start) + " mills");
    }
}
