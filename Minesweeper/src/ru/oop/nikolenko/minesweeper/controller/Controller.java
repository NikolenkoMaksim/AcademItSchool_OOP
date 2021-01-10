package ru.oop.nikolenko.minesweeper.controller;

import ru.oop.nikolenko.minesweeper.model.MinesweeperModel;
import ru.oop.nikolenko.minesweeper.veiw.FrameView;

public class Controller {
    private final MinesweeperModel model;
    private FrameView view;
    private String[][] field;

    public Controller(MinesweeperModel model) {
        this.model = model;
    }

    public void setView(FrameView view) {
        this.view = view;
    }

    public void startNewGame() {
        int fieldWidth = 9;
        int fieldHeight = 9;
        int minesNumber = 10;

        view.removeField();
        view.createField(fieldWidth, fieldHeight);
        field = model.getField(fieldWidth, fieldHeight, minesNumber);
    }

    public void setMines(int fieldWidth, int fieldHeight, int minesCount) {

    }

    public String getTypeOfCell(int x, int y) {
        return field[y][x];
    }
            /* 0 - открывать соседей
               1 - 8 ставить картинку
               9 - mine
             */

}
