package ru.oop.nikolenko.minesweeper.model;

public interface MinesweeperField {
    String[][] getMinesField(int cellsInWidthAmount, int cellsInHeightAmount, int minesAmount);

    String[][] getField(int cellsInWidthAmount, int cellsInHeightAmount, int minesAmount);

    String[][] getField(String[][] minesField);
}
