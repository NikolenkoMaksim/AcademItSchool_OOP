package ru.oop.nikolenko.minesweeper.controller;

import ru.oop.nikolenko.minesweeper.model.MinesweeperModel;
import ru.oop.nikolenko.minesweeper.veiw.View;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Controller implements MinesweeperController {
    private final MinesweeperModel model;
    private View view;
    private String[][] field;
    private int cellsInWidthAmount;
    private int cellsInHeightAmount;

    public Controller(MinesweeperModel model) {
        this.model = model;
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void startNewGame(int cellsInWidthAmount, int cellsInHeightAmount, int minesAmount) {
        this.cellsInWidthAmount = cellsInWidthAmount;
        this.cellsInHeightAmount = cellsInHeightAmount;

        field = model.getField(cellsInWidthAmount, cellsInHeightAmount, minesAmount);
    }

    @Override
    public String getTypeOfCell(int cellNumberByWidth, int cellNumberByHeight) {
        return field[cellNumberByHeight][cellNumberByWidth];
    }

    @Override
    public boolean[][] openCells(int openedCellNumberByWidth, int openedCellNumberByHeight, boolean[][] isOpened, boolean[][] isMarked) {
        boolean isEndOfGame = false;

        if (!field[openedCellNumberByHeight][openedCellNumberByWidth].equals("0")) {
            int markedCellsCount = 0;

            for (int i = Math.max(0, openedCellNumberByHeight - 1); i <= Math.min(cellsInHeightAmount - 1, openedCellNumberByHeight + 1); i++) {
                for (int j = Math.max(0, openedCellNumberByWidth - 1); j <= Math.min(cellsInWidthAmount - 1, openedCellNumberByWidth + 1); j++) {
                    if (isMarked[i][j]) {
                        markedCellsCount++;

                        if (!field[i][j].equals("mine")) {
                            isEndOfGame = true;
                        }
                    }
                }
            }

            if (markedCellsCount != Integer.parseInt(field[openedCellNumberByHeight][openedCellNumberByWidth])) {
                return isOpened;
            }
        }

        if (isEndOfGame) {
            view.endGame(false);
        } else {
            boolean[][] needBeOpened = Arrays.copyOf(isOpened, isOpened.length);

            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{openedCellNumberByWidth, openedCellNumberByHeight});

            while (!queue.isEmpty()) {
                int[] xy = queue.remove();
                int x = xy[0];
                int y = xy[1];

                for (int i = Math.max(0, y - 1); i <= Math.min(cellsInHeightAmount - 1, y + 1); i++) {
                    for (int j = Math.max(0, x - 1); j <= Math.min(cellsInWidthAmount - 1, x + 1); j++) {
                        if (!isMarked[i][j] && !needBeOpened[i][j]) {
                            needBeOpened[i][j] = true;

                            if (field[i][j].equals("0")) {
                                queue.add(new int[]{j, i});
                            }
                        }
                    }
                }
            }

            return needBeOpened;
        }

        return null;
    }
}
