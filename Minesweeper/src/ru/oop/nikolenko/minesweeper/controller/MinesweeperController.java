package ru.oop.nikolenko.minesweeper.controller;

import ru.oop.nikolenko.minesweeper.veiw.View;

public interface MinesweeperController {
    void setView(View view);

    void startNewGame(int fieldWidth, int fieldHeight, int minesAmount);

    String getTypeOfCell(int cellNumberByWidth, int cellNumberByHeight);

    boolean[][] openCells(int openedCellNumberByWidth, int openedCellNumberByHeight, boolean[][] isOpened, boolean[][] isMarked);
}
