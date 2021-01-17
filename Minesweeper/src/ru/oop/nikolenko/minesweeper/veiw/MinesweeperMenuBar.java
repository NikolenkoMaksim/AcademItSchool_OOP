package ru.oop.nikolenko.minesweeper.veiw;

import javax.swing.*;

public class MinesweeperMenuBar {
    public static JMenuBar createJMenuBar(FrameView frameView) {
        JMenuBar menuBar = new JMenuBar();
        JMenu mFile = new JMenu("File");
        JMenu mHelp = new JMenu("Help");

        JMenuItem miNewGame = new JMenuItem("New game");
        miNewGame.addActionListener(e -> frameView.startNewGame());
        mFile.add(miNewGame);

        mFile.add(new JSeparator());

        JMenuItem miChampions = new JMenuItem("Champions");
        miChampions.addActionListener(e -> frameView.openLeaderboardsFrame());
        mFile.add(miChampions);

        JMenuItem miOptions = new JMenuItem("Options");
        miOptions.addActionListener(e -> frameView.openOptionalsFrame());
        mFile.add(miOptions);

        mFile.add(new JSeparator());

        JMenuItem miExit = new JMenuItem("Exit");
        mFile.add(miExit);

        miExit.addActionListener(e -> System.exit(0));

        /*
        JMenuItem miRules = new JMenuItem("Rules");
        mHelp.add(miRules);
         */

        JMenuItem miAbout = new JMenuItem("About");
        mHelp.add(miAbout);

        miAbout.addActionListener(e -> frameView.openAboutFrame());

        menuBar.add(mFile);
        menuBar.add(mHelp);

        return menuBar;
    }
}
