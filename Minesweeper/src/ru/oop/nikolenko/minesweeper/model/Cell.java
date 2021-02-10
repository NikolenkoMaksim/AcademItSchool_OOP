package ru.oop.nikolenko.minesweeper.model;

public class Cell {
    private String type;
    private boolean isOpened;
    private boolean isMarked;
    private final int numberByWidth;
    private final int numberByHeight;

    public Cell(String type, int numberByWidth, int numberByHeight) {
        this.type = type;
        this.numberByWidth = numberByWidth;
        this.numberByHeight = numberByHeight;
        isOpened = false;
        isMarked = false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
