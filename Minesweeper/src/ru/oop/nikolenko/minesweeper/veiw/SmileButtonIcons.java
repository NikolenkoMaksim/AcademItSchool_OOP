package ru.oop.nikolenko.minesweeper.veiw;

import javax.swing.*;

public class SmileButtonIcons {
    private final Icon normalFace = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/face-normal.png");
    private final Icon winnerFace = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/face-win.png");
    private final Icon loserFace = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/face-lose.png");
    private final Icon anxiousFace = new ImageIcon("Minesweeper/src/ru/oop/nikolenko/minesweeper/resources/face-click.png");

    public Icon getNormalFace() {
        return normalFace;
    }

    public Icon getWinnerFace() {
        return winnerFace;
    }

    public Icon getLoserFace() {
        return loserFace;
    }

    public Icon getAnxiousFace() {
        return anxiousFace;
    }
}
