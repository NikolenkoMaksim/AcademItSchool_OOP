package ru.oop.nikolenko.minesweeper.veiw;

import javax.swing.*;

public class SmileButtonIcons implements MainButtonIcons {
    private final Icon normalFace = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/face-normal.png");
    private final Icon winnerFace = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/face-win.png");
    private final Icon loserFace = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/face-lose.png");
    private final Icon anxiousFace = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/face-click.png");

    @Override
    public Icon getNormalButtonIcon() {
        return normalFace;
    }

    @Override
    public Icon getWinnerButtonIcon() {
        return winnerFace;
    }

    @Override
    public Icon getLoserButtonIcon() {
        return loserFace;
    }

    @Override
    public Icon getCellClickButtonIcon() {
        return anxiousFace;
    }
}
