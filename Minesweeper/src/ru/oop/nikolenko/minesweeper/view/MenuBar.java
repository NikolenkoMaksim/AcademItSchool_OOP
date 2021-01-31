package ru.oop.nikolenko.minesweeper.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;

public class MenuBar implements MinesweeperMenuBar {
    public JMenuBar getJMenuBar(FrameView frameView) {
        JMenuBar menuBar = new JMenuBar();

        Font font = new Font("", Font.PLAIN, 13);

        JMenu mFile = new JMenu("File");
        mFile.setFont(font);
        JMenu mHelp = new JMenu("Help");
        mHelp.setFont(font);

        JMenuItem miNewGame = new JMenuItem("New game");
        miNewGame.setMnemonic('N');
        miNewGame.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
        miNewGame.setFont(font);
        miNewGame.addActionListener(e -> frameView.startNewGame());
        mFile.add(miNewGame);

        mFile.add(new JSeparator());

        JMenuItem miChampions = new JMenuItem("Leaderboard");
        miChampions.setMnemonic('L');
        miChampions.setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.CTRL_DOWN_MASK));
        miChampions.setFont(font);
        miChampions.addActionListener(e -> frameView.openLeaderboardsFrame());
        mFile.add(miChampions);

        JMenuItem miOptions = new JMenuItem("Options");
        miOptions.setMnemonic('O');
        miOptions.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
        miOptions.setFont(font);
        miOptions.addActionListener(e -> frameView.openOptionalsFrame());
        mFile.add(miOptions);

        mFile.add(new JSeparator());

        JMenuItem miExit = new JMenuItem("Exit");
        miExit.setMnemonic('E');
        miExit.setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.CTRL_DOWN_MASK));
        miExit.setFont(font);
        miExit.addActionListener(e -> System.exit(0));
        mFile.add(miExit);

        JMenuItem miRules = new JMenuItem("Rules");
        miRules.setMnemonic('R');
        miRules.setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.CTRL_DOWN_MASK));
        miRules.setFont(font);
        miRules.addActionListener(e -> frameView.openRulesFrame());
        mHelp.add(miRules);

        JMenuItem miAbout = new JMenuItem("About");
        miAbout.setMnemonic('A');
        miAbout.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.CTRL_DOWN_MASK));
        miAbout.setFont(font);
        miAbout.addActionListener(e -> frameView.openAboutFrame());
        mHelp.add(miAbout);

        menuBar.add(mFile);
        menuBar.add(mHelp);

        return menuBar;
    }
}
