package ru.oop.nikolenko.minesweeper.veiw;

import javax.swing.*;
import java.awt.*;

public class MenuBar implements MinesweeperMenuBar {
    public JMenuBar getJMenuBar(FrameView frameView) {
        JMenuBar menuBar = new JMenuBar();

        Font font = new Font("", Font.PLAIN, 13);

        JMenu mFile = new JMenu("File");
        mFile.setFont(font);
        JMenu mHelp = new JMenu("Help");
        mHelp.setFont(font);

        JMenuItem miNewGame = new JMenuItem("New game");
        miNewGame.setFont(font);
        miNewGame.addActionListener(e -> frameView.startNewGame());
        mFile.add(miNewGame);

        mFile.add(new JSeparator());

        JMenuItem miChampions = new JMenuItem("Champions");
        miChampions.setFont(font);
        miChampions.addActionListener(e -> frameView.openLeaderboardsFrame());
        mFile.add(miChampions);

        JMenuItem miOptions = new JMenuItem("Options");
        miOptions.setFont(font);
        miOptions.addActionListener(e -> frameView.openOptionalsFrame());
        mFile.add(miOptions);

        mFile.add(new JSeparator());

        JMenuItem miExit = new JMenuItem("Exit");
        miExit.setFont(font);
        miExit.addActionListener(e -> System.exit(0));
        mFile.add(miExit);

        JMenuItem miRules = new JMenuItem("Rules");
        miRules.setFont(font);
        miRules.addActionListener(e -> frameView.openRulesFrame());
        mHelp.add(miRules);

        JMenuItem miAbout = new JMenuItem("About");
        miAbout.setFont(font);
        miAbout.addActionListener(e -> frameView.openAboutFrame());
        mHelp.add(miAbout);

        menuBar.add(mFile);
        menuBar.add(mHelp);

        return menuBar;
    }
}
