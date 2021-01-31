package ru.oop.nikolenko.minesweeper.view;

public interface View {
    void run();

    void startNewGame();

    void endGame(boolean isWinner);
}
