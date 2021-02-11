package ru.oop.nikolenko.minesweeper.model;

import java.io.FileNotFoundException;

public interface MinesweeperLeaders {
    Leader[][] getLeaders();

    String[] getCategoriesNames();

    boolean isLeader(int categoryNumber, int time);

    void saveLeader(int categoryNumber, Leader newLeader) throws FileNotFoundException;

    void clearLeaders() throws FileNotFoundException;
}
