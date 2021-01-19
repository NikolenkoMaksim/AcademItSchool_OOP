package ru.oop.nikolenko.minesweeper.model;

public interface MinesweeperModel {
    String[][] getField(int cellsInWidthAmount, int cellsInHeightAmount, int minesAmount);
}
