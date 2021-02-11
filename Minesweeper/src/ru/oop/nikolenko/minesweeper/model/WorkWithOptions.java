package ru.oop.nikolenko.minesweeper.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WorkWithOptions implements WorkWithMinesweeperOptions {
    private final String optionsFilePath;
    private final Options[] defaultOptions;
    private Options currentOptions;

    public WorkWithOptions(String optionsFilePath, Options[] defaultOptions) {
        if (defaultOptions.length < 1) {
            throw new IllegalArgumentException("defaultOptions.length = " + defaultOptions.length + " can't be < 1");
        }

        for (int i = 0; i < defaultOptions.length; i++) {
            if (defaultOptions[i] == null) {
                throw new IllegalArgumentException("defaultOptions[" + i + "] can't be null");
            }
        }

        this.optionsFilePath = optionsFilePath;
        this.defaultOptions = defaultOptions;

        readOptions();
    }

    @Override
    public Options[] getDefaultOptions() {
        return defaultOptions;
    }

    @Override
    public Options getCurrentOptions() {
        return currentOptions;
    }

    private void readOptions() {
        try (Scanner scanner = new Scanner(new FileInputStream(optionsFilePath))) {
            int parametersCount = 0;
            int[] readParameters = new int[4];

            while (scanner.hasNextInt() && parametersCount < 4) {
                readParameters[parametersCount] = scanner.nextInt();
                parametersCount++;
            }

            if (parametersCount < 3 || readParameters[2] > readParameters[0] * readParameters[1] ||
                    readParameters[3] > defaultOptions.length) {
                currentOptions = defaultOptions[0];
                return;
            }

            if (readParameters[3] < 0) {
                currentOptions = new Options(readParameters[0], readParameters[1], readParameters[2]);
            } else {
                currentOptions = defaultOptions[readParameters[3]];
            }
        } catch (FileNotFoundException ignored) {
            currentOptions = defaultOptions[0];
        }
    }

    @Override
    public void saveOptions(Options newOptions) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(optionsFilePath)) {
            writer.println(newOptions.getCellsInWidthAmount());
            writer.println(newOptions.getCellsInHeightAmount());
            writer.println(newOptions.getMinesAmount());
            writer.println(newOptions.getNumberOfDefaultOptions());
            writer.println(newOptions.getName());
        }

        currentOptions = newOptions;
    }
}

