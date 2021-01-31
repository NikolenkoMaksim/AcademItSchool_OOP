package ru.oop.nikolenko.minesweeper.model;

import java.io.FileNotFoundException;

public interface MinesweeperOptions {
    int[] getMinesweeperOptions();

    void saveOptions(int[] mimeSweeperOptions) throws FileNotFoundException;

    int[][] getDefaultOptions();
}
