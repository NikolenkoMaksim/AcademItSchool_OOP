package ru.oop.nikolenko.minesweeper.model;

import java.util.Objects;
import java.util.Random;

public class Model implements MinesweeperModel {
    @Override
    public String[][] getField(int cellsInWidthAmount, int cellsInHeightAmount, int minesAmount) {
        String[][] field = new String[cellsInHeightAmount][cellsInWidthAmount];

        Random random = new Random();
        int minesCount = 0;

        while (minesCount < minesAmount) {
            int cellNumberByWidth = random.nextInt(cellsInWidthAmount);
            int cellNumberByHeight = random.nextInt(cellsInHeightAmount);

            if (!Objects.equals(field[cellNumberByHeight][cellNumberByWidth], "mine")) {
                field[cellNumberByHeight][cellNumberByWidth] = "mine";
                minesCount++;
            }
        }

        for (int i = 0; i < cellsInHeightAmount; i++) {
            for (int j = 0; j < cellsInWidthAmount; j++) {
                if (!Objects.equals(field[i][j], "mine")) {
                    int minesAroundCount = 0;

                    for (int k = Math.max(0, i - 1); k <= Math.min(cellsInHeightAmount - 1, i + 1); k++) {
                        for (int m = Math.max(0, j - 1); m <= Math.min(cellsInWidthAmount - 1, j + 1); m++) {
                            if (k != i || m != j) {
                                if (Objects.equals(field[k][m], "mine")) {
                                    minesAroundCount++;
                                }
                            }
                        }
                    }

                    field[i][j] = String.valueOf(minesAroundCount);
                }
            }
        }

        return field;
    }
}