package ru.oop.nikolenko.minesweeper.veiw;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RulesView {
    public void openRulesFrame(String fileRulesPath) {
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

        StringBuilder stringBuilder = new StringBuilder();

        try (Scanner scanner = new Scanner(new FileInputStream(fileRulesPath))) {
            while (scanner.hasNextLine()) {
                stringBuilder.append("     ").append(scanner.nextLine()).append(System.lineSeparator()).append(System.lineSeparator());
            }
        } catch (FileNotFoundException ignored) {
            stringBuilder.append("There is no data");
        }

        JTextArea aboutTextArea = new JTextArea(stringBuilder.toString());
        aboutTextArea.setEditable(false);
        aboutTextArea.setWrapStyleWord(true);
        aboutTextArea.setLineWrap(true);
        aboutTextArea.setFont(new Font("", Font.PLAIN, 13));

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

        rulesFrameLayout.setConstraints(aboutTextArea, rulesLayoutConstraints);
        rulesFrame.add(aboutTextArea);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> rulesFrame.dispose());
        rulesLayoutConstraints.fill = GridBagConstraints.NONE;
        rulesLayoutConstraints.insets = new Insets(10, 10, 10, 10);
        rulesLayoutConstraints.weightx = 0;
        rulesLayoutConstraints.weighty = 0;
        rulesFrameLayout.setConstraints(cancelButton, rulesLayoutConstraints);
        rulesFrame.add(cancelButton);
    }
}