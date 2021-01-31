package ru.oop.nikolenko.minesweeper.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Options implements MinesweeperOptions {
    private final String optionsFilePath;
    private final int[][] defaultOptions;

    public Options(String optionsFilePath, int[][] defaultOptions) {
        for (int i = 0; i < defaultOptions.length; i++) {
            if (defaultOptions[i].length != 3) {
                throw new IllegalArgumentException("defaultOptions[" + i + "].length = " + defaultOptions[i].length +
                        "must be 3: {cellsInWidthAmount, cellsInHeightAmount, minesCount}");
            }

            if (defaultOptions[i][0] * defaultOptions[i][1] < defaultOptions[i][2]) {
                throw new IllegalArgumentException("The number of bombs in defaultOptions[" + i + "] exceeds the number of cells");
            }
        }

        this.optionsFilePath = optionsFilePath;
        this.defaultOptions = defaultOptions;
    }

    @Override
    public int[] getMinesweeperOptions() {
        int[] minesweeperOptions = new int[3];

        try (Scanner scanner = new Scanner(new FileInputStream(optionsFilePath))) {
            int parametersCount = 0;

            while (scanner.hasNextInt() && parametersCount < 3) {
                minesweeperOptions[parametersCount] = scanner.nextInt();
                parametersCount++;
            }

            if (parametersCount < 2 || minesweeperOptions[2] > minesweeperOptions[0] * minesweeperOptions[1]) {
                return getDefaultFrameOptionals();
            }

            return getMinesweeperViewOptions(minesweeperOptions);
        } catch (FileNotFoundException ignored) {
            return getDefaultFrameOptionals();
        }
    }

    @Override
    public void saveOptions(int[] mimeSweeperOptions) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(optionsFilePath)) {
            for (int mimeSweeperOption : mimeSweeperOptions) {
                writer.println(mimeSweeperOption);
            }
        }
    }

    @Override
    public int[][] getDefaultOptions() {
        return defaultOptions;
    }

    private int[] getDefaultFrameOptionals() {
        int[] defaultViewOptions = Arrays.copyOf(defaultOptions[0], 4);
        defaultViewOptions[3] = 0;

        return defaultViewOptions;
    }

    private int[] getMinesweeperViewOptions(int[] minesweeperOptionals) {
        for (int i = 0; i < defaultOptions.length; i++) {
            if (Arrays.equals(defaultOptions[i], minesweeperOptionals)) {
                int[] minesweeperViewOptions = Arrays.copyOf(minesweeperOptionals, 4);
                minesweeperViewOptions[3] = i;

                return minesweeperViewOptions;
            }
        }

        int[] minesweeperViewOptions = Arrays.copyOf(minesweeperOptionals, 4);
        minesweeperViewOptions[3] = 3;

        return minesweeperViewOptions;
    }
}

