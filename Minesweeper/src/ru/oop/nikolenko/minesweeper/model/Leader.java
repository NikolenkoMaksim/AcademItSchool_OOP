package ru.oop.nikolenko.minesweeper.model;

public class Leader {
    private final String name;
    private final int time;

    public Leader(String name, int time) {
        checkTime(time);

        this.name = name;
        this.time = time;
    }

    public Leader(int time) {
        checkTime(time);

        this.name = null;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    private void checkTime(int time) {
        if (time < 0) {
            throw new IllegalArgumentException("time = " + time + " can't be < 0");
        }
    }
}
