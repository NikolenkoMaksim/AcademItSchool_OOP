package ru.oop.nikolenko.minesweeper.main;

import ru.oop.nikolenko.minesweeper.controller.Controller;
import ru.oop.nikolenko.minesweeper.controller.MinesweeperController;
import ru.oop.nikolenko.minesweeper.model.*;
import ru.oop.nikolenko.minesweeper.view.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MinesweeperField minesweeperField = new Field();

        final int[][] defaultOptionals = new int[][]{
                new int[]{9, 9, 10},
                new int[]{16, 16, 40},
                new int[]{30, 16, 99}
        };
        String optionsPath = "Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/optionals.txt";
        MinesweeperOptions minesweeperOptionals = new Options(optionsPath, defaultOptionals);

        final String[] categoriesNames = new String[]{"Beginner", "Amateur", "Professional"};
        String championsFilePath = "Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/champions.txt";
        MinesweeperLeaders minesweeperLeaders = new Leaders(championsFilePath, 5, categoriesNames);

        FileToStringBuilderConverter fileToStringBuilderConverter = new FileToStringBuilderReader();
        final String aboutFilePath = "Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/about.txt";
        final String rulesFilePath = "Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/rules.txt";

        MinesweeperModel model = new Model(minesweeperField, minesweeperOptionals, minesweeperLeaders,
                fileToStringBuilderConverter, aboutFilePath, rulesFilePath);

        MinesweeperController controller = new Controller(model);

        FieldIcons fieldIcons = new FieldStandardIcons();
        MainButtonIcons mainButtonIcons = new SmileButtonIcons();

        ImageIcon mainIcon = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/mainIcon.png");

        View view = new FrameView(controller, fieldIcons, mainButtonIcons, mainIcon);
        controller.setView(view);

        view.run();
    }
}