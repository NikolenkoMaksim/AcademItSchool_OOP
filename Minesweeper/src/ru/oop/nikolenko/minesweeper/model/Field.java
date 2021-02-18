package ru.oop.nikolenko.minesweeper.model;

import java.util.*;

public class Field implements MinesweeperField {
    private Cell[][] field;
    private int cellsInWidthAmount;
    private int cellsInHeightAmount;
    private int minesAmount;
    private int remainingMinesCount;
    private int openedCellsCount;
    private boolean isFirstCellOpened;

    @Override
    public int getRemainingMinesCount() {
        return remainingMinesCount;
    }

    @Override
    public void createField(Options currentOptions) {
        cellsInWidthAmount = currentOptions.getCellsInWidthAmount();
        cellsInHeightAmount = currentOptions.getCellsInHeightAmount();
        minesAmount = currentOptions.getMinesAmount();
        remainingMinesCount = minesAmount;
        openedCellsCount = 0;
        isFirstCellOpened = false;

        field = new Cell[cellsInHeightAmount][cellsInWidthAmount];

        Random random = new Random();
        int minesCount = 0;

        while (minesCount < minesAmount) {
            int cellNumberByWidth = random.nextInt(cellsInWidthAmount);
            int cellNumberByHeight = random.nextInt(cellsInHeightAmount);

            if (field[cellNumberByHeight][cellNumberByWidth] == null) {
                field[cellNumberByHeight][cellNumberByWidth] = new Cell(true, 0, cellNumberByWidth, cellNumberByHeight);
                minesCount++;
            }
        }

        setTypeForNotMineCells();
    }

    private void setTypeForNotMineCells() {
        for (int i = 0; i < cellsInHeightAmount; i++) {
            for (int j = 0; j < cellsInWidthAmount; j++) {
                if (field[i][j] == null || !field[i][j].isMine()) {
                    int minesAroundCount = 0;

                    for (int k = Math.max(0, i - 1); k <= Math.min(cellsInHeightAmount - 1, i + 1); k++) {
                        for (int m = Math.max(0, j - 1); m <= Math.min(cellsInWidthAmount - 1, j + 1); m++) {
                            if (k != i || m != j) {
                                if (field[k][m] != null && field[k][m].isMine()) {
                                    minesAroundCount++;
                                }
                            }
                        }
                    }

                    if (field[i][j] == null) {
                        field[i][j] = new Cell(false, minesAroundCount, j, i);
                    } else {
                        field[i][j].setMinesAroundCount(minesAroundCount);
                    }
                }
            }
        }
    }

    @Override
    public EndGameParameters handleFieldEvent(FieldEvent fieldEvent, int cellNumberByWidth, int cellNumberByHeight) {
        checkCellCoordinates(cellNumberByWidth, cellNumberByHeight);

        if (fieldEvent == FieldEvent.openClosedCell && !field[cellNumberByHeight][cellNumberByWidth].isMarked()) {
            openedCellsCount++;

            if (!isFirstCellOpened && field[cellNumberByHeight][cellNumberByWidth].isMine()) {
                isFirstCellOpened = true;
                return recreateFieldWithoutMineInCell(cellNumberByWidth, cellNumberByHeight);
            }

            return openCellAndCheckEndOfGame(cellNumberByWidth, cellNumberByHeight);
        }

        if (fieldEvent == FieldEvent.openCellsAroundOpenedCell && field[cellNumberByHeight][cellNumberByWidth].isOpened()) {
            return openCellsAndCheckEndOfGame(cellNumberByWidth, cellNumberByHeight);
        }

        if (fieldEvent == FieldEvent.markedCell) {
            if (!field[cellNumberByHeight][cellNumberByWidth].isMarked()) {
                field[cellNumberByHeight][cellNumberByWidth].setMarked(true);
                remainingMinesCount--;
            } else {
                field[cellNumberByHeight][cellNumberByWidth].setMarked(false);
                remainingMinesCount++;
            }
        }

        return new EndGameParameters(false, false);
    }

    private void checkCellCoordinates(int cellNumberByWidth, int cellNumberByHeight) {
        if (cellNumberByWidth < 0) {
            throw new IllegalArgumentException("cellNumberByWidth = " + cellNumberByWidth + " can't be < 0");
        }

        if (cellNumberByWidth >= cellsInWidthAmount) {
            throw new IllegalArgumentException("cellNumberByWidth = " + cellNumberByWidth + " can't be >= cellsInWidthAmount = " + cellsInWidthAmount);
        }

        if (cellNumberByHeight < 0) {
            throw new IllegalArgumentException("cellNumberByHeight = " + cellNumberByHeight + " can't be < 0");
        }

        if (cellNumberByHeight >= cellsInHeightAmount) {
            throw new IllegalArgumentException("cellNumberByHeight = " + cellNumberByHeight + " can't be >= cellsInHeightAmount = " + cellsInHeightAmount);
        }
    }

    private EndGameParameters openCellAndCheckEndOfGame(int openedCellNumberByWidth, int openedCellNumberByHeight) {
        field[openedCellNumberByHeight][openedCellNumberByWidth].setOpened(true);

        if (field[openedCellNumberByHeight][openedCellNumberByWidth].isMine()) {
            return new EndGameParameters(true, false);
        }

        if (field[openedCellNumberByHeight][openedCellNumberByWidth].getMinesAroundCount() == 0) {
            return openCellsAndCheckEndOfGame(openedCellNumberByWidth, openedCellNumberByHeight);
        }

        return checkWin();
    }

    private EndGameParameters openCellsAndCheckEndOfGame(int openedCellNumberByWidth, int openedCellNumberByHeight) {
        field[openedCellNumberByHeight][openedCellNumberByWidth].setOpened(true);

        if (field[openedCellNumberByHeight][openedCellNumberByWidth].isMine()) {
            return new EndGameParameters(true, false);
        }

        boolean isEndOfGame = false;

        if (field[openedCellNumberByHeight][openedCellNumberByWidth].getMinesAroundCount() != 0) {
            int markedCellsCount = 0;

            for (int i = Math.max(0, openedCellNumberByHeight - 1); i <= Math.min(cellsInHeightAmount - 1, openedCellNumberByHeight + 1); i++) {
                for (int j = Math.max(0, openedCellNumberByWidth - 1); j <= Math.min(cellsInWidthAmount - 1, openedCellNumberByWidth + 1); j++) {
                    if (field[i][j].isMarked()) {
                        markedCellsCount++;

                        if (!field[i][j].isMine()) {
                            isEndOfGame = true;
                        }
                    }
                }
            }

            if (markedCellsCount != field[openedCellNumberByHeight][openedCellNumberByWidth].getMinesAroundCount()) {
                return new EndGameParameters(false, false);
            }
        }

        if (isEndOfGame) {
            return new EndGameParameters(true, false);
        }

        Queue<Cell> queue = new LinkedList<>();
        queue.add(field[openedCellNumberByHeight][openedCellNumberByWidth]);

        while (!queue.isEmpty()) {
            Cell checkCell = queue.remove();

            for (int i = Math.max(0, checkCell.getNumberByHeight() - 1); i <= Math.min(cellsInHeightAmount - 1, checkCell.getNumberByHeight() + 1); i++) {
                for (int j = Math.max(0, checkCell.getNumberByWidth() - 1); j <= Math.min(cellsInWidthAmount - 1, checkCell.getNumberByWidth() + 1); j++) {
                    if (!field[i][j].isMarked() && !field[i][j].isOpened()) {
                        field[i][j].setOpened(true);
                        openedCellsCount++;

                        if (field[i][j].getMinesAroundCount() == 0) {
                            queue.add(field[i][j]);
                        }
                    }
                }
            }
        }

        return checkWin();
    }

    private EndGameParameters checkWin() {
        if (cellsInWidthAmount * cellsInHeightAmount - openedCellsCount == minesAmount) {
            return new EndGameParameters(true, true);
        }

        return new EndGameParameters(false, false);
    }

    @Override
    public boolean isMine(int cellNumberByWidth, int cellNumberByHeight) {
        return field[cellNumberByHeight][cellNumberByWidth].isMine();
    }

    @Override
    public int getMinesAroundCount(int cellNumberByWidth, int cellNumberByHeight) {
        return field[cellNumberByHeight][cellNumberByWidth].getMinesAroundCount();
    }

    @Override
    public EndGameParameters recreateFieldWithoutMineInCell(int notMineCellNumberByWidth, int notMineCellNumberByHeight) {
        if (notMineCellNumberByWidth < 0) {
            throw new IllegalArgumentException("notMineCellNumberByWidth = " + notMineCellNumberByWidth + " can't be < 0");
        }

        if (notMineCellNumberByWidth >= cellsInWidthAmount) {
            throw new IllegalArgumentException("notMineCellNumberByWidth = " + notMineCellNumberByWidth + " can't be >= cellsInWidthAmount = " + cellsInWidthAmount);
        }

        if (notMineCellNumberByHeight < 0) {
            throw new IllegalArgumentException("notMineCellNumberByHeight = " + notMineCellNumberByHeight + " can't be < 0");
        }

        if (notMineCellNumberByHeight >= cellsInHeightAmount) {
            throw new IllegalArgumentException("notMineCellNumberByHeight = " + notMineCellNumberByHeight + " can't be >= cellsInHeightAmount = " + cellsInHeightAmount);
        }

        Random random = new Random();

        field[notMineCellNumberByHeight][notMineCellNumberByWidth].setMine(false);
        field[notMineCellNumberByHeight][notMineCellNumberByWidth].setOpened(true);
        openedCellsCount = 1;

        while (true) {
            int cellNumberByWidth = random.nextInt(cellsInWidthAmount);
            int cellNumberByHeight = random.nextInt(cellsInHeightAmount);

            if ((cellNumberByWidth != notMineCellNumberByWidth || cellNumberByHeight != notMineCellNumberByHeight) && !field[cellNumberByHeight][cellNumberByWidth].isMine()) {
                field[cellNumberByHeight][cellNumberByWidth] = new Cell(true, 0, cellNumberByWidth, cellNumberByHeight);
                break;
            }
        }

        setTypeForNotMineCells();

        return openCellAndCheckEndOfGame(notMineCellNumberByWidth, notMineCellNumberByHeight);
    }

    @Override
    public boolean isCellOpen(int cellNumberByWidth, int cellNumberByHeight) {
        return field[cellNumberByHeight][cellNumberByWidth].isOpened();
    }

    @Override
    public boolean isCellMarked(int cellNumberByWidth, int cellNumberByHeight) {
        return field[cellNumberByHeight][cellNumberByWidth].isMarked();
    }
}
