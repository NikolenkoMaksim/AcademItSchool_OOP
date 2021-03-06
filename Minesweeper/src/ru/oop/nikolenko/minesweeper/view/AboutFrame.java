package ru.oop.nikolenko.minesweeper.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AboutFrame {
    public void openAboutFrame(StringBuilder fileAboutStringBuilder) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        JFrame aboutFrame = new JFrame("About");

        final int frameDefaultWidth = 300;
        final int frameDefaultHeight = 145;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        aboutFrame.setLocation((screenSize.width - frameDefaultWidth) / 2, (screenSize.height - frameDefaultHeight) / 2);
        aboutFrame.setSize(frameDefaultWidth, frameDefaultHeight);
        aboutFrame.setResizable(false);
        aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aboutFrame.setVisible(true);
        aboutFrame.getRootPane().setBorder(new EmptyBorder(15, 15, 15, 15));

        JTextArea aboutTextArea = new JTextArea(fileAboutStringBuilder.toString());
        Font font = new Font("", Font.PLAIN, 14);
        aboutTextArea.setFont(font);
        aboutTextArea.setEditable(false);
        aboutTextArea.setWrapStyleWord(true);
        aboutTextArea.setLineWrap(true);
        aboutTextArea.setFont(new Font("", Font.PLAIN, 14));

        GridBagLayout aboutFrameLayout = new GridBagLayout();
        aboutFrame.setLayout(aboutFrameLayout);

        GridBagConstraints aboutLayoutConstraints = new GridBagConstraints();

        aboutLayoutConstraints.anchor = GridBagConstraints.CENTER;
        aboutLayoutConstraints.fill = GridBagConstraints.BOTH;
        aboutLayoutConstraints.gridheight = 1;
        aboutLayoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
        aboutLayoutConstraints.gridx = GridBagConstraints.RELATIVE;
        aboutLayoutConstraints.gridy = GridBagConstraints.RELATIVE;
        aboutLayoutConstraints.insets = new Insets(0, 0, 0, 0);
        aboutLayoutConstraints.ipadx = 0;
        aboutLayoutConstraints.ipady = 0;
        aboutLayoutConstraints.weightx = 1;
        aboutLayoutConstraints.weighty = 1;

        aboutFrameLayout.setConstraints(aboutTextArea, aboutLayoutConstraints);
        aboutFrame.add(aboutTextArea);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(font);
        cancelButton.addActionListener(e -> aboutFrame.dispose());
        aboutLayoutConstraints.fill = GridBagConstraints.NONE;
        aboutLayoutConstraints.insets = new Insets(10, 10, 0, 10);
        aboutLayoutConstraints.weightx = 0;
        aboutLayoutConstraints.weighty = 0;
        aboutFrameLayout.setConstraints(cancelButton, aboutLayoutConstraints);
        aboutFrame.add(cancelButton);

        aboutFrame.requestFocusInWindow();
        cancelButton.requestFocus();
    }
}
