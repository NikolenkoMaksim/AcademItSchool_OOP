package ru.oop.nikolenko.minesweeper.controller;

import ru.oop.nikolenko.minesweeper.model.Options;
import ru.oop.nikolenko.minesweeper.view.View;

import java.io.FileNotFoundException;

public interface MinesweeperController {
    void setView(View view);

    void startNewGame();

    String getTypeOfCell(int cellNumberByWidth, int cellNumberByHeight);

    void handleMouseClick(int mouseButton, int cellNumberByWidth, int cellNumberByHeight);

    boolean isCellOpen(int cellNumberByWidth, int cellNumberByHeight);

    boolean isCellMarked(int cellNumberByWidth, int cellNumberByHeight);

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
