package ru.oop.nikolenko.minesweeper.model;

public class Cell {
    private String value;
    private boolean isOpened;
    private boolean isMarked;
    private final int numberByWidth;
    private final int numberByHeight;

    public Cell(String value, int numberByWidth, int numberByHeight) {
        if (numberByWidth < 0) {
            throw new IllegalArgumentException("numberByWidth = " + numberByWidth + " can't be < 0");
        }

        if (numberByHeight < 0) {
            throw new IllegalArgumentException("numberByHeight = " + numberByHeight + " can't be < 0");
        }

        this.value = value;
        this.numberByWidth = numberByWidth;
        this.numberByHeight = numberByHeight;
        isOpened = false;
        isMarked = false;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public int getNumberByWidth() {
        return numberByWidth;
    }

    public int getNumberByHeight() {
        return numberByHeight;
    }
}
