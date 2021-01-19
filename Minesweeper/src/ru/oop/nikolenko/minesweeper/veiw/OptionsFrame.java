package ru.oop.nikolenko.minesweeper.veiw;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class OptionsFrame {
    public void openOptionsFrame(int[] currentOptionals, FrameView frameView, int[][] defaultOptions, String[] categoryNames) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            JFrame optionsFrame = new JFrame("Optionals");

            final int frameDefaultWidth = 300;
            final int frameDefaultHeight = 450;
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            optionsFrame.setLocation((screenSize.width - frameDefaultWidth) / 2, (screenSize.height - frameDefaultHeight) / 2);
            optionsFrame.setSize(frameDefaultWidth, frameDefaultHeight);
            optionsFrame.setResizable(false);
            optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            optionsFrame.setVisible(true);
            optionsFrame.getRootPane().setBorder(new EmptyBorder(15, 15, 15, 15));

            GridBagLayout optionalsFrameLayout = new GridBagLayout();
            optionsFrame.setLayout(optionalsFrameLayout);

            GridBagConstraints optionalsLayoutConstraints = new GridBagConstraints();

            optionalsLayoutConstraints.anchor = GridBagConstraints.WEST;
            optionalsLayoutConstraints.fill = GridBagConstraints.NONE;
            optionalsLayoutConstraints.gridheight = 1;
            optionalsLayoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
            optionalsLayoutConstraints.gridx = GridBagConstraints.RELATIVE;
            optionalsLayoutConstraints.gridy = GridBagConstraints.RELATIVE;
            optionalsLayoutConstraints.insets = new Insets(0, 0, 10, 0);
            optionalsLayoutConstraints.ipadx = 0;
            optionalsLayoutConstraints.ipady = 0;
            optionalsLayoutConstraints.weightx = 0;
            optionalsLayoutConstraints.weighty = 0;

            JRadioButton beginnersButton = new JRadioButton(categoryNames[0] + ". (Field: " +
                    defaultOptions[0][0] + "x" + defaultOptions[0][1] + "; mines: " + defaultOptions[0][2] + ")");
            optionalsFrameLayout.setConstraints(beginnersButton, optionalsLayoutConstraints);
            optionsFrame.add(beginnersButton);

            JRadioButton amateurButton = new JRadioButton(categoryNames[1] + ". (Field: " +
                    defaultOptions[1][0] + "x" + defaultOptions[1][1] + "; mines: " + defaultOptions[1][2] + ")");
            optionalsFrameLayout.setConstraints(amateurButton, optionalsLayoutConstraints);
            optionsFrame.add(amateurButton);

            JRadioButton professionalButton = new JRadioButton(categoryNames[2] + ". (Field: " +
                    defaultOptions[2][0] + "x" + defaultOptions[2][1] + "; mines: " + defaultOptions[2][2] + ")");
            optionalsFrameLayout.setConstraints(professionalButton, optionalsLayoutConstraints);
            optionsFrame.add(professionalButton);

            JRadioButton specialButton = new JRadioButton("Special:");
            optionalsFrameLayout.setConstraints(specialButton, optionalsLayoutConstraints);
            optionsFrame.add(specialButton);

            ButtonGroup optionalsButtonGroup = new ButtonGroup();
            optionalsButtonGroup.add(beginnersButton);
            optionalsButtonGroup.add(amateurButton);
            optionalsButtonGroup.add(professionalButton);
            optionalsButtonGroup.add(specialButton);

            optionalsLayoutConstraints.insets = new Insets(0, 30, 10, 0);

            JLabel desiredWidthLabel = new JLabel("Width:");
            optionalsFrameLayout.setConstraints(desiredWidthLabel, optionalsLayoutConstraints);
            optionsFrame.add(desiredWidthLabel);

            JTextField desiredWidthTextField = new JTextField("", 3);
            optionalsFrameLayout.setConstraints(desiredWidthTextField, optionalsLayoutConstraints);
            optionsFrame.add(desiredWidthTextField);

            JLabel desiredHeightLabel = new JLabel("Height:");
            optionalsFrameLayout.setConstraints(desiredHeightLabel, optionalsLayoutConstraints);
            optionsFrame.add(desiredHeightLabel);

            JTextField desiredHeightTextField = new JTextField("", 3);
            optionalsFrameLayout.setConstraints(desiredHeightTextField, optionalsLayoutConstraints);
            optionsFrame.add(desiredHeightTextField);

            JLabel desiredMinesLabel = new JLabel("Mines:");
            optionalsFrameLayout.setConstraints(desiredMinesLabel, optionalsLayoutConstraints);
            optionsFrame.add(desiredMinesLabel);

            JTextField desiredMinesTextField = new JTextField("", 3);
            optionalsFrameLayout.setConstraints(desiredMinesTextField, optionalsLayoutConstraints);
            optionsFrame.add(desiredMinesTextField);

            ActionListener standardButtonSelected = e -> {
                desiredWidthTextField.setEditable(false);
                desiredHeightTextField.setEditable(false);
                desiredMinesTextField.setEditable(false);
            };

            beginnersButton.addActionListener(standardButtonSelected);
            amateurButton.addActionListener(standardButtonSelected);
            professionalButton.addActionListener(standardButtonSelected);

            specialButton.addActionListener(e -> {
                desiredWidthTextField.setEditable(true);
                desiredHeightTextField.setEditable(true);
                desiredMinesTextField.setEditable(true);
            });

            if (Arrays.equals(currentOptionals, defaultOptions[0])) {
                beginnersButton.setSelected(true);
            } else if (Arrays.equals(currentOptionals, defaultOptions[1])) {
                amateurButton.setSelected(true);
            } else if (Arrays.equals(currentOptionals, defaultOptions[2])) {
                professionalButton.setSelected(true);
            } else {
                specialButton.setSelected(true);
                desiredWidthTextField.setText(String.valueOf(currentOptionals[0]));
                desiredHeightTextField.setText(String.valueOf(currentOptionals[1]));
                desiredMinesTextField.setText(String.valueOf(currentOptionals[2]));
            }

            JButton applyButton = new JButton("Apply");
            optionalsLayoutConstraints.anchor = GridBagConstraints.CENTER;
            optionalsLayoutConstraints.insets = new Insets(10, 40, 0, 10);
            optionalsLayoutConstraints.gridwidth = 1;
            optionalsFrameLayout.setConstraints(applyButton, optionalsLayoutConstraints);
            optionsFrame.add(applyButton);

            JButton cancelButton = new JButton("Cancel");
            optionalsLayoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
            optionalsLayoutConstraints.insets = new Insets(10, 10, 0, 40);
            optionalsFrameLayout.setConstraints(cancelButton, optionalsLayoutConstraints);
            optionsFrame.add(cancelButton);

            applyButton.addActionListener(actionEvent -> {
                if (beginnersButton.isSelected()) {
                    frameView.saveOptions(defaultOptions[0], 0);
                    optionsFrame.dispose();
                } else if (amateurButton.isSelected()) {
                    frameView.saveOptions(defaultOptions[1], 1);
                    optionsFrame.dispose();
                } else if (professionalButton.isSelected()) {
                    frameView.saveOptions(defaultOptions[2], 2);
                    optionsFrame.dispose();
                } else {
                    try {
                        int[] specialOptionals = new int[]{
                                Integer.parseInt(desiredWidthTextField.getText()),
                                Integer.parseInt(desiredHeightTextField.getText()),
                                Integer.parseInt(desiredMinesTextField.getText())
                        };

                        if (specialOptionals[0] * specialOptionals[1] < specialOptionals[2]) {
                            throw new IllegalArgumentException();
                        }

                        optionsFrame.dispose();
                        frameView.saveOptions(specialOptionals, 3);
                    } catch (NumberFormatException exception1) {
                        String message = "Invalid data format." + System.lineSeparator() + "Fill in all fields with numbers";
                        JOptionPane.showMessageDialog(optionsFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException exception2) {
                        String message = "The number of bombs exceeds the number of cells";
                        JOptionPane.showMessageDialog(optionsFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            cancelButton.addActionListener(e -> optionsFrame.dispose());
        });
    }
}
