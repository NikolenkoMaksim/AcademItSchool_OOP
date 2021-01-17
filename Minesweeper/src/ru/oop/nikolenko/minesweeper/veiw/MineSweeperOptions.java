package ru.oop.nikolenko.minesweeper.veiw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MineSweeperOptions {
    public static int[] getMimeSweeperOptions(String optionalsFilePath) {
        int[] mimeSweeperOptions = new int[3];

        int defaultFieldWidth = 9;
        int defaultFieldHeight = 9;
        int defaultMinesNumber = 10;

        try (Scanner scanner = new Scanner(new FileInputStream(optionalsFilePath))) {
            int parametersCount = 0;

            while (scanner.hasNextInt() && parametersCount < 3) {
                mimeSweeperOptions[parametersCount] = scanner.nextInt();
                parametersCount++;
            }

            if (parametersCount < 2) {
                return new int[]{defaultFieldWidth, defaultFieldHeight, defaultMinesNumber};
            }

            if (mimeSweeperOptions[2] > mimeSweeperOptions[0] * mimeSweeperOptions[1]) {
                return new int[]{defaultFieldWidth, defaultFieldHeight, defaultMinesNumber};
            }

            return mimeSweeperOptions;
        } catch (FileNotFoundException ignored) {
            return new int[]{defaultFieldWidth, defaultFieldHeight, defaultMinesNumber};
        }
    }

    public static void saveOptionals(String optionalsFilePath, int[] mimeSweeperOptions) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(optionalsFilePath)) {
            for (int mimeSweeperOption : mimeSweeperOptions) {
                writer.println(mimeSweeperOption);
            }
        }
    }
}

