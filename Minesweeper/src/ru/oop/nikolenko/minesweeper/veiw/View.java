package ru.oop.nikolenko.minesweeper.veiw;

public interface View {
    void run();

    void startNewGame();

    void endGame(boolean isWinner);
}
