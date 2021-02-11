package ru.oop.nikolenko.minesweeper.model;

public class Options {
    private final int cellsInWidthAmount;
    private final int cellsInHeightAmount;
    private final int minesAmount;
    private final int numberOfDefaultOptions;
    private final String name;

    public Options(int cellsInWidthAmount, int cellsInHeightAmount, int minesAmount) {
        if (cellsInWidthAmount < 2) {
            throw new IllegalArgumentException("cellsInWidthAmount = " + cellsInWidthAmount + "can't be < 2");
        }

        if (cellsInHeightAmount < 2) {
            throw new IllegalArgumentException("cellsInHeightAmount = " + cellsInHeightAmount + "can't be < 2");
        }

        if (minesAmount < 1) {
            throw new IllegalArgumentException("minesAmount = " + minesAmount + "can't be < 1");
        }

        if (cellsInWidthAmount * cellsInHeightAmount < minesAmount) {
            throw new IllegalArgumentException("The number of bombs (" + minesAmount + ") exceeds the number of cells (" +
                    cellsInWidthAmount * cellsInHeightAmount + ")");
        }

        this.cellsInWidthAmount = cellsInWidthAmount;
        this.cellsInHeightAmount = cellsInHeightAmount;
        this.minesAmount = minesAmount;
        this.numberOfDefaultOptions = -1;
        this.name = null;
    }

    public Options(int cellsInWidthAmount, int cellsInHeightAmount, int minesAmount, int numberOfDefaultOptions, String name) {
        if (cellsInWidthAmount * cellsInHeightAmount < minesAmount) {
            throw new IllegalArgumentException("The number of bombs (" + minesAmount + ") exceeds the number of cells (" +
                    cellsInWidthAmount * cellsInHeightAmount + ")");
        }

        if (numberOfDefaultOptions < 0) {
            throw new IllegalArgumentException("NumberOfDefaultOptions (" + numberOfDefaultOptions + ") can't be < 0");
        }

        this.cellsInWidthAmount = cellsInWidthAmount;
        this.cellsInHeightAmount = cellsInHeightAmount;
        this.minesAmount = minesAmount;
        this.numberOfDefaultOptions = numberOfDefaultOptions;
        this.name = name;
    }

    public int getCellsInWidthAmount() {
        return cellsInWidthAmount;
    }

    public int getCellsInHeightAmount() {
        return cellsInHeightAmount;
    }

    public int getMinesAmount() {
        return minesAmount;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfDefaultOptions() {
        return numberOfDefaultOptions;
    }
}
