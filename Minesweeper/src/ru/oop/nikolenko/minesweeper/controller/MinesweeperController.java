package ru.oop.nikolenko.minesweeper.controller;

import ru.oop.nikolenko.minesweeper.view.View;

import java.io.FileNotFoundException;

public interface MinesweeperController {
    void setView(View view);

    void startNewGame(int fieldWidth, int fieldHeight, int minesAmount);

    void recreateField(int notBombCoordinateX, int notBombCoordinateY);

    String getTypeOfCell(int cellNumberByWidth, int cellNumberByHeight);

    boolean[][] openCells(int openedCellNumberByWidth, int openedCellNumberByHeight, boolean[][] isOpened, boolean[][] isMarked);

    int[] getMinesweeperOptions();

    void saveOptions(int[] mimeSweeperOptions) throws FileNotFoundException;

    int[][] getDefaultOptions();

    String[][] getLeadersNames();

    Integer[][] getLeadersTimes();

    String[] getCategoriesNames();

    int getNewWinnerPlace(int categoryNumber, int time);

    void saveLeader(int categoryNumber, int newLeaderTime, String newLeaderName, int place) throws FileNotFoundException;

    void clearLeaders() throws FileNotFoundException;

    StringBuilder getAboutFileStringBuilder() throws FileNotFoundException;

    StringBuilder getRulesFileStringBuilder() throws FileNotFoundException;
}
