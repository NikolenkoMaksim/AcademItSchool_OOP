package ru.oop.nikolenko.minesweeper.model;

import java.io.FileNotFoundException;

public interface WorkWithMinesweeperOptions {
    Options getCurrentOptions();

    Options[] getDefaultOptions();

    void saveOptions(Options newOptions) throws FileNotFoundException;
}
