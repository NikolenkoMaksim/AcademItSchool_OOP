package ru.oop.nikolenko.minesweeper.model;

public class Cell {
    private boolean isMine;
    private int minesAroundCount;
    private boolean isOpened;
    private boolean isMarked;
    private final int numberByWidth;
    private final int numberByHeight;

    public Cell(boolean isMine, int minesAroundCount, int numberByWidth, int numberByHeight) {
        if (numberByWidth < 0) {
            throw new IllegalArgumentException("numberByWidth = " + numberByWidth + " can't be < 0");
        }

        if (numberByHeight < 0) {
            throw new IllegalArgumentException("numberByHeight = " + numberByHeight + " can't be < 0");
        }

        checkMinesAroundCount(minesAroundCount);

        this.isMine = isMine;
        this.minesAroundCount = minesAroundCount;
        this.numberByWidth = numberByWidth;
        this.numberByHeight = numberByHeight;
        isOpened = false;
        isMarked = false;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public int getMinesAroundCount() {
        return minesAroundCount;
    }

    public void setMinesAroundCount(int minesAroundCount) {
        checkMinesAroundCount(minesAroundCount);

        this.minesAroundCount = minesAroundCount;
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

    private void checkMinesAroundCount(int minesAroundCount) {
        if(minesAroundCount < 0) {
            throw new IllegalArgumentException("minesAroundCount = " + minesAroundCount + " < 0");
        }

        if(minesAroundCount > 8) {
            throw new IllegalArgumentException("minesAroundCount = " + minesAroundCount + " > 8");
        }
    }
}
