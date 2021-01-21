package ru.oop.nikolenko.minesweeper.veiw;

import javax.swing.*;

public class MenuBar implements MinesweeperMenuBar {
    public JMenuBar getJMenuBar(FrameView frameView) {
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
        miExit.addActionListener(e -> System.exit(0));
        mFile.add(miExit);

        JMenuItem miRules = new JMenuItem("Rules");
        miRules.addActionListener(e -> frameView.openRulesFrame());
        mHelp.add(miRules);

        JMenuItem miAbout = new JMenuItem("About");
        miAbout.addActionListener(e -> frameView.openAboutFrame());
        mHelp.add(miAbout);

        menuBar.add(mFile);
        menuBar.add(mHelp);

        return menuBar;
    }
}
