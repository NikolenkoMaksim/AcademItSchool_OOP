package ru.oop.nikolenko.minesweeper.model;

import java.awt.event.MouseEvent;
import java.util.*;

public class Field implements MinesweeperField {
    private Cell[][] field;
    private int cellsInWidthAmount;
    private int cellsInHeightAmount;
    private int minesAmount;
    private int remainingMinesCount;
    private int openedCellsCount;

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

        field = new Cell[cellsInHeightAmount][cellsInWidthAmount];

        Random random = new Random();
        int minesCount = 0;

        while (minesCount < minesAmount) {
            int cellNumberByWidth = random.nextInt(cellsInWidthAmount);
            int cellNumberByHeight = random.nextInt(cellsInHeightAmount);

            if (field[cellNumberByHeight][cellNumberByWidth] == null) {
                field[cellNumberByHeight][cellNumberByWidth] = new Cell("mine", cellNumberByWidth, cellNumberByHeight);
                minesCount++;
            }
        }

        setTypeForNotMineCells();
    }

    private void setTypeForNotMineCells() {
        for (int i = 0; i < cellsInHeightAmount; i++) {
            for (int j = 0; j < cellsInWidthAmount; j++) {
                if (field[i][j] == null || !field[i][j].getValue().equals("mine")) {
                    int minesAroundCount = 0;

                    for (int k = Math.max(0, i - 1); k <= Math.min(cellsInHeightAmount - 1, i + 1); k++) {
                        for (int m = Math.max(0, j - 1); m <= Math.min(cellsInWidthAmount - 1, j + 1); m++) {
                            if (k != i || m != j) {
                                if (field[k][m] != null && field[k][m].getValue().equals("mine")) {
                                    minesAroundCount++;
                                }
                            }
                        }
                    }

                    if (field[i][j] == null) {
                        field[i][j] = new Cell(String.valueOf(minesAroundCount), j, i);
                    } else {
                        field[i][j].setValue(String.valueOf(minesAroundCount));
                    }
                }
            }
        }
    }

    @Override
    public EndTheGame handleMouseClick(int mouseButton, int cellNumberByWidth, int cellNumberByHeight) {
        checkCellCoordinates(cellNumberByWidth, cellNumberByHeight);

        if (mouseButton == MouseEvent.BUTTON1 && !field[cellNumberByHeight][cellNumberByWidth].isMarked()) {
            openedCellsCount++;
            return openCellAndCheckEndOfGame(cellNumberByWidth, cellNumberByHeight);
        }

        if (mouseButton == MouseEvent.BUTTON2 && field[cellNumberByHeight][cellNumberByWidth].isOpened()) {
            return openCellsAndCheckEndOfGame(cellNumberByWidth, cellNumberByHeight);
        }

        if (mouseButton == MouseEvent.BUTTON3) {
            if (!field[cellNumberByHeight][cellNumberByWidth].isMarked()) {
                field[cellNumberByHeight][cellNumberByWidth].setMarked(true);
                remainingMinesCount--;
            } else {
                field[cellNumberByHeight][cellNumberByWidth].setMarked(false);
                remainingMinesCount++;
            }
        }

        return new EndTheGame(false, false);
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

    private EndTheGame openCellAndCheckEndOfGame(int openedCellNumberByWidth, int openedCellNumberByHeight) {
        field[openedCellNumberByHeight][openedCellNumberByWidth].setOpened(true);

        if (field[openedCellNumberByHeight][openedCellNumberByWidth].getValue().equals("mine")) {
            return new EndTheGame(true, false);
        }

        if (field[openedCellNumberByHeight][openedCellNumberByWidth].getValue().equals("0")) {
            return openCellsAndCheckEndOfGame(openedCellNumberByWidth, openedCellNumberByHeight);
        }

        return checkWin();
    }

    private EndTheGame openCellsAndCheckEndOfGame(int openedCellNumberByWidth, int openedCellNumberByHeight) {
        field[openedCellNumberByHeight][openedCellNumberByWidth].setOpened(true);

        if (field[openedCellNumberByHeight][openedCellNumberByWidth].getValue().equals("mine")) {
            return new EndTheGame(true, false);
        }

        boolean isEndOfGame = false;

        if (!field[openedCellNumberByHeight][openedCellNumberByWidth].getValue().equals("0")) {
            int markedCellsCount = 0;

            for (int i = Math.max(0, openedCellNumberByHeight - 1); i <= Math.min(cellsInHeightAmount - 1, openedCellNumberByHeight + 1); i++) {
                for (int j = Math.max(0, openedCellNumberByWidth - 1); j <= Math.min(cellsInWidthAmount - 1, openedCellNumberByWidth + 1); j++) {
                    if (field[i][j].isMarked()) {
                        markedCellsCount++;

                        if (!field[i][j].getValue().equals("mine")) {
                            isEndOfGame = true;
                        }
                    }
                }
            }

            if (markedCellsCount != Integer.parseInt(field[openedCellNumberByHeight][openedCellNumberByWidth].getValue())) {
                return new EndTheGame(false, false);
            }
        }

        if (isEndOfGame) {
            return new EndTheGame(true, false);
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

                        if (field[i][j].getValue().equals("0")) {
                            queue.add(field[i][j]);
                        }
                    }
                }
            }
        }

        return checkWin();
    }

    private EndTheGame checkWin() {
        if (cellsInWidthAmount * cellsInHeightAmount - openedCellsCount == minesAmount) {
            return new EndTheGame(true, true);
        }

        return new EndTheGame(false, false);
    }

    @Override
    public String getTypeOfCell(int cellNumberByWidth, int cellNumberByHeight) {
        checkCellCoordinates(cellNumberByWidth, cellNumberByHeight);

        return field[cellNumberByHeight][cellNumberByWidth].getValue();
    }

    @Override
    public void recreateFieldWithoutMineInCell(int notMineCellNumberByWidth, int notMineCellNumberByHeight) {
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

        field[notMineCellNumberByHeight][notMineCellNumberByWidth].setValue("0");
        field[notMineCellNumberByHeight][notMineCellNumberByWidth].setOpened(true);
        openedCellsCount = 1;

        while (true) {
            int cellNumberByWidth = random.nextInt(cellsInWidthAmount);
            int cellNumberByHeight = random.nextInt(cellsInHeightAmount);

            if ((cellNumberByWidth != notMineCellNumberByWidth || cellNumberByHeight != notMineCellNumberByHeight) && !field[cellNumberByHeight][cellNumberByWidth].getValue().equals("mine")) {
                field[cellNumberByHeight][cellNumberByWidth] = new Cell("mine", cellNumberByWidth, cellNumberByHeight);
                break;
            }
        }

        setTypeForNotMineCells();
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
