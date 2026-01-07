package com.example.projekt17_part1;

public class Training {
    private int id;
    private String name;
    private int reps;
    private int duration;
    private String date;
    private String difficulty;

    public Training(int id, String name, int reps, int duration, String date, String difficulty) {
        this.id = id;
        this.name = name;
        this.reps = reps;
        this.duration = duration;
        this.date = date;
        this.difficulty = difficulty;
    }

    public String getName() { return name; }
    public int getReps() { return reps; }
    public String getDate() { return date; }
}
