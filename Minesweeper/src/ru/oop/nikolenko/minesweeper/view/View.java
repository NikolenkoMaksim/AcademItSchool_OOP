package ru.oop.nikolenko.minesweeper.view;

public interface View {
    void run();

    void startNewGame();

    void endGame(boolean isWinner);

    void createField(boolean isGameEnd);

    void startTimer();

    void setRemainingMinesLabel(int remainingMinesCount);
}
