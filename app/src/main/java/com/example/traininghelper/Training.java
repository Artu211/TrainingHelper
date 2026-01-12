package com.example.traininghelper;

public class Training {
    private String name;
    private int reps;

    public Training(String name, int reps) {
        this.name = name;
        this.reps = reps;
    }

    public String getName() { return name; }
    public int getReps() { return reps; }
}
