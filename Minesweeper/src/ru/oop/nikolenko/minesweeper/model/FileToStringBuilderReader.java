package ru.oop.nikolenko.minesweeper.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileToStringBuilderReader implements FileToStringBuilderConverter {
    @Override
    public StringBuilder convertDataToStringBuilder(String filePath) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();

            if (scanner.hasNextLine()) {
                stringBuilder.append("    ").append(scanner.nextLine());
            }

            while (scanner.hasNextLine()) {
                stringBuilder.append(System.lineSeparator()).append("    ").append(scanner.nextLine());
            }

            return stringBuilder;
        }
    }
}
