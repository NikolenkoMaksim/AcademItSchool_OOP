package ru.oop.nikolenko.minesweeper.main;

import ru.oop.nikolenko.minesweeper.controller.Controller;
import ru.oop.nikolenko.minesweeper.controller.MinesweeperController;
import ru.oop.nikolenko.minesweeper.model.MinesweeperModel;
import ru.oop.nikolenko.minesweeper.model.Model;
import ru.oop.nikolenko.minesweeper.veiw.*;

public class Main {
    public static void main(String[] args) {
        MinesweeperModel model = new Model();

        MinesweeperController controller = new Controller(model);

        FieldIcons fieldIcons = new FieldStandardIcons();
        MainButtonIcons mainButtonIcons = new SmileButtonIcons();

        final int[][] defaultOptionals = new int[][]{
                new int[]{9, 9, 10},
                new int[]{16, 16, 40},
                new int[]{30, 16, 99}
        };
        String optionsPath = "Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/optionals.txt";
        MinesweeperOptions mineSweeperOptionals = new Options(optionsPath, defaultOptionals);

        final String[] categoriesNames = new String[]{"Beginner", "Amateur", "Professional"};
        String championsFilePath = "Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/champions.txt";
        MinesweeperLeaders leaders = new Leaders(championsFilePath, 5, categoriesNames);

        View view = new FrameView(controller, fieldIcons, mainButtonIcons, mineSweeperOptionals, leaders);
        controller.setView(view);

        view.run();
    }
}