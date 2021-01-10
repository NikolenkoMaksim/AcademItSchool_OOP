package ru.oop.nikolenko.minesweeper.main;

import ru.oop.nikolenko.minesweeper.controller.Controller;
import ru.oop.nikolenko.minesweeper.model.MinesweeperModel;
import ru.oop.nikolenko.minesweeper.veiw.FrameView;

public class Main {
    public static void main(String[] args) {
        MinesweeperModel model = new MinesweeperModel();
        Controller controller = new Controller(model);
        FrameView view = new FrameView(controller);
        controller.setView(view);

        view.start();
    }
}