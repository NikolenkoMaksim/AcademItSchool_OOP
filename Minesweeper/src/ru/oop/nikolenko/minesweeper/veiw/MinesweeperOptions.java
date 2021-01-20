package ru.oop.nikolenko.minesweeper.veiw;

import java.io.FileNotFoundException;

public interface MinesweeperOptions {
    int[] getMimeSweeperOptions();

    void saveOptions(int[] mimeSweeperOptions) throws FileNotFoundException;

    int[][] getDefaultOptions();
}
