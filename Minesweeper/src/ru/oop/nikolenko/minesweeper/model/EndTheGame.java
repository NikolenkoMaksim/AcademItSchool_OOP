package ru.oop.nikolenko.minesweeper.model;

public class EndTheGame {
    private final boolean isEndTheGame;
    private final boolean isWin;

    public EndTheGame(boolean isEndTheGame, boolean isWin) {
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
