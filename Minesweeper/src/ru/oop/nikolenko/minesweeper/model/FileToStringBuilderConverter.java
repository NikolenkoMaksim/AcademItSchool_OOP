package ru.oop.nikolenko.minesweeper.model;

import java.io.FileNotFoundException;

public interface FileToStringBuilderConverter {
    StringBuilder convertDataToStringBuilder(String filePath) throws FileNotFoundException;
}
