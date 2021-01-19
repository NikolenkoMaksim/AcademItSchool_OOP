package ru.oop.nikolenko.minesweeper.veiw;

import java.io.FileNotFoundException;

public interface MinesweeperLeaders {
    String[][] getLeadersNames();

    Integer[][] getLeadersTimes();

    String[] getCategoriesNames();

    int getNewWinnerPlace(int categoryNumber, int time);

    void saveLeader(int categoryNumber, int newLeaderTime, String newLeaderName, int place) throws FileNotFoundException;
}
