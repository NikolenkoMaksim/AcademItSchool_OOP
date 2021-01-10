package ru.oop.nikolenko.minesweeper.veiw;

import ru.oop.nikolenko.minesweeper.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesweeperMenuBar {
    public static JMenuBar createJMenuBar(Controller controller) {
        JMenuBar mb = new JMenuBar();
        JMenu mFile = new JMenu("File");
        JMenu mHelp = new JMenu("Help");

        JMenuItem miNewGame = new JMenuItem("New game");
        mFile.add(miNewGame);

        miNewGame.addActionListener(e -> controller.startNewGame());

        mFile.add(new JSeparator());

        JMenuItem miChampions = new JMenuItem("Champions");
        mFile.add(miChampions);

        miChampions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        JMenuItem miOptions = new JMenuItem("Options");
        mFile.add(miOptions);

        miOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        mFile.add(new JSeparator());

        JMenuItem miExit = new JMenuItem("Exit");
        mFile.add(miExit);

        miExit.addActionListener(e -> System.exit(0));

        JMenuItem miRules = new JMenuItem("Rules");
        mHelp.add(miRules);

        miRules.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        JMenuItem miAbout = new JMenuItem("About...");
        mHelp.add(miAbout);

        miAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        mb.add(mFile);
        mb.add(mHelp);

        return mb;
    }
}
