package ru.oop.nikolenko.minesweeper.model;

import java.util.Objects;
import java.util.Random;

public class MinesweeperModel {
    public String[][] getField(int fieldWidth, int fieldHeight, int minesNumber) {
        String[][] field = new String[fieldHeight][fieldWidth];

        Random random = new Random();
        int minesCount = 0;

        while (minesCount < minesNumber) {
            int cellNumberByWidth = random.nextInt(fieldWidth);
            int cellNumberByHeight = random.nextInt(fieldHeight);

            if (!Objects.equals(field[cellNumberByHeight][cellNumberByWidth], "mine")) {
                field[cellNumberByHeight][cellNumberByWidth] = "mine";
                minesCount++;
            }
        }

        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                if (!Objects.equals(field[i][j], "mine")) {
                    int minesAroundCount = 0;

                    for (int k = Math.max(0, i - 1); k <= Math.min(fieldHeight - 1, i + 1); k++) {
                        for (int m = Math.max(0, j - 1); m <= Math.min(fieldWidth - 1, j + 1); m++) {
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