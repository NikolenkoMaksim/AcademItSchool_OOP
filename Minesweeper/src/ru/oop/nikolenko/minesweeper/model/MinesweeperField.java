package ru.oop.nikolenko.minesweeper.model;

public interface MinesweeperField {
    int getRemainingMinesCount();

    void createField(Options currentOptions);

    EndGameParameters recreateFieldWithoutMineInCell(int notMineCellNumberByWidth, int notMineCellNumberByHeight);

    EndGameParameters handleFieldEvent(FieldEvent fieldEvent, int cellNumberByWidth, int cellNumberByHeight);

    boolean isMine(int cellNumberByWidth, int cellNumberByHeight);

    int getMinesAroundCount(int cellNumberByWidth, int cellNumberByHeight);

    boolean isCellOpen(int cellNumberByWidth, int cellNumberByHeight);

    boolean isCellMarked(int cellNumberByWidth, int cellNumberByHeight);
}
