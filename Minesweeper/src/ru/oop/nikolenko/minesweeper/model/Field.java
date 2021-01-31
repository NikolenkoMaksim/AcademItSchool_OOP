package ru.oop.nikolenko.minesweeper.model;

import java.util.Objects;
import java.util.Random;

public class Field implements MinesweeperField {
    @Override
    public String[][] getMinesField(int cellsInWidthAmount, int cellsInHeightAmount, int minesAmount) {
        String[][] minesField = new String[cellsInHeightAmount][cellsInWidthAmount];

        Random random = new Random();
        int minesCount = 0;

        while (minesCount < minesAmount) {
            int cellNumberByWidth = random.nextInt(cellsInWidthAmount);
            int cellNumberByHeight = random.nextInt(cellsInHeightAmount);

            if (!Objects.equals(minesField[cellNumberByHeight][cellNumberByWidth], "mine")) {
                minesField[cellNumberByHeight][cellNumberByWidth] = "mine";
                minesCount++;
            }
        }

        return minesField;
    }

    private String[][] arrangeNumbers(String[][] minesField) {
        for (int i = 0; i < minesField.length; i++) {
            for (int j = 0; j < minesField[0].length; j++) {
                if (!Objects.equals(minesField[i][j], "mine")) {
                    int minesAroundCount = 0;

                    for (int k = Math.max(0, i - 1); k <= Math.min(minesField.length - 1, i + 1); k++) {
                        for (int m = Math.max(0, j - 1); m <= Math.min(minesField[0].length - 1, j + 1); m++) {
                            if (k != i || m != j) {
                                if (Objects.equals(minesField[k][m], "mine")) {
                                    minesAroundCount++;
                                }
                            }
                        }
                    }

                    minesField[i][j] = String.valueOf(minesAroundCount);
                }
            }
        }

        return minesField;
    }

    @Override
    public String[][] getField(int cellsInWidthAmount, int cellsInHeightAmount, int minesAmount) {
        String[][] minesField = getMinesField(cellsInWidthAmount, cellsInHeightAmount, minesAmount);

        return arrangeNumbers(minesField);
    }

    @Override
    public String[][] getField(String[][] minesField) {
        return arrangeNumbers(minesField);
    }
}
