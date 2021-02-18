package ru.oop.nikolenko.minesweeper.model;

public class EndGameParameters {
    private final boolean isEndTheGame;
    private final boolean isWin;

    public EndGameParameters(boolean isEndTheGame, boolean isWin) {
        this.isEndTheGame = isEndTheGame;
        this.isWin = isWin;
    }

    public boolean isEndTheGame() {
        return isEndTheGame;
    }

    public boolean isWin() {
        return isWin;
    }
}
