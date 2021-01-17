package ru.oop.nikolenko.minesweeper.controller;

import ru.oop.nikolenko.minesweeper.model.MinesweeperModel;
import ru.oop.nikolenko.minesweeper.veiw.FrameView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Controller {
    private final MinesweeperModel model;
    private FrameView view;
    private String[][] field;
    private int fieldWidth;
    private int fieldHeight;

    public Controller(MinesweeperModel model) {
        this.model = model;
    }

    public void setView(FrameView view) {
        this.view = view;
    }

    public void startNewGame(int fieldWidth, int fieldHeight, int minesNumber) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;

        field = model.getField(fieldWidth, fieldHeight, minesNumber);
    }

    public String getTypeOfCell(int x, int y) {
        return field[y][x];
    }

    public boolean[][] openCells(int openedCellX, int openedCellY, boolean[][] isOpened, boolean[][] isMarked) {
        boolean isEndOfGame = false;

        if (!field[openedCellY][openedCellX].equals("0")) {
            int markedCellsCount = 0;

            for (int i = Math.max(0, openedCellY - 1); i <= Math.min(fieldHeight - 1, openedCellY + 1); i++) {
                for (int j = Math.max(0, openedCellX - 1); j <= Math.min(fieldWidth - 1, openedCellX + 1); j++) {
                    if (isMarked[i][j]) {
                        markedCellsCount++;

                        if (!field[i][j].equals("mine")) {
                            isEndOfGame = true;
                        }
                    }
                }
            }

            if (markedCellsCount != Integer.parseInt(field[openedCellY][openedCellX])) {
                return isOpened;
            }
        }

        if (isEndOfGame) {
            view.endGame(false);
        } else {

            boolean[][] needBeOpened = Arrays.copyOf(isOpened, isOpened.length);

            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{openedCellX, openedCellY});

            while (!queue.isEmpty()) {
                int[] xy = queue.remove();
                int x = xy[0];
                int y = xy[1];

                for (int i = Math.max(0, y - 1); i <= Math.min(fieldHeight - 1, y + 1); i++) {
                    for (int j = Math.max(0, x - 1); j <= Math.min(fieldWidth - 1, x + 1); j++) {
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
