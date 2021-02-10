package ru.oop.nikolenko.minesweeper.model;

import java.io.FileNotFoundException;

public interface MinesweeperModel {
    void createField();

    void recreateFieldWithoutMineInCell(int cellNumberByWidth, int cellNumberByHeight);

    String getTypeOfCell(int cellNumberByWidth, int cellNumberByHeight);

    EndTheGame handleMouseClick(int mouseButton, int cellNumberByWidth, int cellNumberByHeight);

    boolean isCellOpen(int cellNumberByWidth, int cellNumberByHeight);

    boolean isCellMarked(int cellNumberByWidth, int cellNumberByHeight);

    int getRemainingMinesCount();

    Options getCurrentOptions();

    void saveOptions(Options newOptions) throws FileNotFoundException;

    Options[] getDefaultOptions();

    String[][] getLeadersNames();

    Integer[][] getLeadersTimes();

    String[] getCategoriesNames();

    int getNewWinnerPlace(int categoryNumber, int time);

    void saveLeader(int categoryNumber, int newLeaderTime, String newLeaderName, int place) throws FileNotFoundException;

    void clearLeaders() throws FileNotFoundException;

    StringBuilder getAboutFileStringBuilder() throws FileNotFoundException;

    StringBuilder getRulesFileStringBuilder() throws FileNotFoundException;
}
