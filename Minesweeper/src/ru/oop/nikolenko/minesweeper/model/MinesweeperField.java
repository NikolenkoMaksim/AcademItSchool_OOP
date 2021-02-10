package ru.oop.nikolenko.minesweeper.model;

public interface MinesweeperField {
    int getRemainingMinesCount();

    void createField(Options currentOptions);

    void recreateFieldWithoutMineInCell(int notMineCellNumberByWidth, int notMineCellNumberByHeight);

    EndTheGame handleMouseClick(int mouseButton, int cellNumberByWidth, int cellNumberByHeight);

    String getTypeOfCell(int cellNumberByWidth, int cellNumberByHeight);

    boolean isCellOpen(int cellNumberByWidth, int cellNumberByHeight);

    boolean isCellMarked(int cellNumberByWidth, int cellNumberByHeight);
}
