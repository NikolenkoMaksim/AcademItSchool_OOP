package ru.oop.nikolenko.minesweeper.model;

import java.io.FileNotFoundException;

public interface MinesweeperModel {
    void createField();

    EndGameParameters recreateFieldWithoutMineInCell(int cellNumberByWidth, int cellNumberByHeight);

    boolean isMine(int cellNumberByWidth, int cellNumberByHeight);

    int getMinesAroundCount(int cellNumberByWidth, int cellNumberByHeight);

    EndGameParameters handleFieldEvent(FieldEvent fieldEvent, int cellNumberByWidth, int cellNumberByHeight);

    boolean isCellOpen(int cellNumberByWidth, int cellNumberByHeight);

    boolean isCellMarked(int cellNumberByWidth, int cellNumberByHeight);

    int getRemainingMinesCount();

    Options getCurrentOptions();

    void saveOptions(Options newOptions) throws FileNotFoundException;

    Options[] getDefaultOptions();

    Leader[][] getLeaders();

    String[] getCategoriesNames();

    boolean isLeader(int categoryNumber, int time);

    void saveLeader(int categoryNumber, Leader newLeader) throws FileNotFoundException;

    void clearLeaders() throws FileNotFoundException;

    StringBuilder getAboutFileStringBuilder() throws FileNotFoundException;

    StringBuilder getRulesFileStringBuilder() throws FileNotFoundException;
}
