package ru.oop.nikolenko.minesweeper.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RulesFrame {
    public void openRulesFrame(StringBuilder fileRulesStringBuilder) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        JFrame rulesFrame = new JFrame("Rules");

        final int frameDefaultWidth = 550;
        final int frameDefaultHeight = 530;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        rulesFrame.setLocation((screenSize.width - frameDefaultWidth) / 2, (screenSize.height - frameDefaultHeight) / 2);
        rulesFrame.setSize(frameDefaultWidth, frameDefaultHeight);
        rulesFrame.setResizable(false);
        rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rulesFrame.setVisible(true);
        rulesFrame.getRootPane().setBorder(new EmptyBorder(15, 15, 15, 15));

        Font font = new Font("", Font.PLAIN, 14);

        JTextArea rulesTextArea = new JTextArea(fileRulesStringBuilder.toString());
        rulesTextArea.setFont(font);
        rulesTextArea.setEditable(false);
        rulesTextArea.setWrapStyleWord(true);
        rulesTextArea.setLineWrap(true);
        rulesTextArea.setFont(new Font("", Font.PLAIN, 14));

        GridBagLayout rulesFrameLayout = new GridBagLayout();
        rulesFrame.setLayout(rulesFrameLayout);

        GridBagConstraints rulesLayoutConstraints = new GridBagConstraints();

        rulesLayoutConstraints.anchor = GridBagConstraints.CENTER;
        rulesLayoutConstraints.fill = GridBagConstraints.BOTH;
        rulesLayoutConstraints.gridheight = 1;
        rulesLayoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
        rulesLayoutConstraints.gridx = GridBagConstraints.RELATIVE;
        rulesLayoutConstraints.gridy = GridBagConstraints.RELATIVE;
        rulesLayoutConstraints.insets = new Insets(0, 0, 0, 0);
        rulesLayoutConstraints.ipadx = 0;
        rulesLayoutConstraints.ipady = 0;
        rulesLayoutConstraints.weightx = 1;
        rulesLayoutConstraints.weighty = 1;

        rulesFrameLayout.setConstraints(rulesTextArea, rulesLayoutConstraints);
        rulesFrame.add(rulesTextArea);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(font);
        cancelButton.addActionListener(e -> rulesFrame.dispose());
        rulesLayoutConstraints.fill = GridBagConstraints.NONE;
        rulesLayoutConstraints.insets = new Insets(10, 10, 10, 10);
        rulesLayoutConstraints.weightx = 0;
        rulesLayoutConstraints.weighty = 0;
        rulesFrameLayout.setConstraints(cancelButton, rulesLayoutConstraints);
        rulesFrame.add(cancelButton);

        rulesFrame.requestFocusInWindow();
        cancelButton.requestFocus();
    }
}
