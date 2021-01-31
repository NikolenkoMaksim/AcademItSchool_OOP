package ru.oop.nikolenko.minesweeper.model;

import java.io.FileNotFoundException;

public interface MinesweeperModel {
    String[][] getField(int cellsInWidthAmount, int cellsInHeightAmount, int minesAmount);

    String[][] getField(String[][] minesField);

    String[][] getMinesField(int cellsInWidthAmount, int cellsInHeightAmount, int minesAmount);

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
