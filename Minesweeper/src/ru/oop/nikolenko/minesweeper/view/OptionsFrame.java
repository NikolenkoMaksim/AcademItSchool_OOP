package ru.oop.nikolenko.minesweeper.view;

import ru.oop.nikolenko.minesweeper.model.Options;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class OptionsFrame {
    public void openOptionsFrame(Options currentOptions, Options[] defaultOptions, FrameView frameView) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            JFrame optionsFrame = new JFrame("Options");

            final int frameDefaultWidth = 350;
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
            Font font = new Font("", Font.PLAIN, 14);

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

            JRadioButton[] standardRadioButtons = new JRadioButton[defaultOptions.length];

            for (int i = 0; i < standardRadioButtons.length; i++) {
                JRadioButton radioButton = new JRadioButton(defaultOptions[i].getName() + ". Field: " +
                        defaultOptions[i].getCellsInWidthAmount() + "x" + defaultOptions[i].getCellsInHeightAmount() +
                        "; mines: " + defaultOptions[i].getMinesAmount());
                radioButton.setFont(font);
                optionalsFrameLayout.setConstraints(radioButton, optionalsLayoutConstraints);
                optionsFrame.add(radioButton);

                standardRadioButtons[i] = radioButton;
            }

            JRadioButton specialButton = new JRadioButton("Special:");
            specialButton.setFont(font);
            optionalsFrameLayout.setConstraints(specialButton, optionalsLayoutConstraints);
            optionsFrame.add(specialButton);

            ButtonGroup optionalsButtonGroup = new ButtonGroup();

            for (JRadioButton radioButton : standardRadioButtons) {
                optionalsButtonGroup.add(radioButton);
            }

            optionalsButtonGroup.add(specialButton);

            optionalsLayoutConstraints.insets = new Insets(0, 30, 10, 0);

            JLabel desiredWidthLabel = new JLabel("Width:");
            desiredWidthLabel.setFont(font);
            optionalsFrameLayout.setConstraints(desiredWidthLabel, optionalsLayoutConstraints);
            optionsFrame.add(desiredWidthLabel);

            JTextField desiredWidthTextField = new JTextField("", 3);
            desiredWidthTextField.setFont(font);
            optionalsFrameLayout.setConstraints(desiredWidthTextField, optionalsLayoutConstraints);
            optionsFrame.add(desiredWidthTextField);

            JLabel desiredHeightLabel = new JLabel("Height:");
            desiredHeightLabel.setFont(font);
            optionalsFrameLayout.setConstraints(desiredHeightLabel, optionalsLayoutConstraints);
            optionsFrame.add(desiredHeightLabel);

            JTextField desiredHeightTextField = new JTextField("", 3);
            desiredHeightTextField.setFont(font);
            optionalsFrameLayout.setConstraints(desiredHeightTextField, optionalsLayoutConstraints);
            optionsFrame.add(desiredHeightTextField);

            JLabel desiredMinesLabel = new JLabel("Mines:");
            desiredMinesLabel.setFont(font);
            optionalsFrameLayout.setConstraints(desiredMinesLabel, optionalsLayoutConstraints);
            optionsFrame.add(desiredMinesLabel);

            JTextField desiredMinesTextField = new JTextField("", 3);
            desiredMinesTextField.setFont(font);
            optionalsFrameLayout.setConstraints(desiredMinesTextField, optionalsLayoutConstraints);
            optionsFrame.add(desiredMinesTextField);

            ActionListener standardButtonSelected = e -> {
                desiredWidthTextField.setEditable(false);
                desiredHeightTextField.setEditable(false);
                desiredMinesTextField.setEditable(false);
            };

            for (JRadioButton radioButton : standardRadioButtons) {
                radioButton.addActionListener(standardButtonSelected);
            }

            specialButton.addActionListener(e -> {
                desiredWidthTextField.setEditable(true);
                desiredHeightTextField.setEditable(true);
                desiredMinesTextField.setEditable(true);
            });

            if (currentOptions.getNumberOfDefaultOptions() > -1) {
                standardRadioButtons[currentOptions.getNumberOfDefaultOptions()].setSelected(true);
                desiredWidthTextField.setEditable(false);
                desiredHeightTextField.setEditable(false);
                desiredMinesTextField.setEditable(false);
            } else {
                specialButton.setSelected(true);
                desiredWidthTextField.setEditable(true);
                desiredHeightTextField.setEditable(true);
                desiredMinesTextField.setEditable(true);
                desiredWidthTextField.setText(String.valueOf(currentOptions.getCellsInWidthAmount()));
                desiredHeightTextField.setText(String.valueOf(currentOptions.getCellsInHeightAmount()));
                desiredMinesTextField.setText(String.valueOf(currentOptions.getMinesAmount()));
            }

            JButton applyButton = new JButton("Apply");
            applyButton.setFont(font);
            optionalsLayoutConstraints.anchor = GridBagConstraints.CENTER;
            optionalsLayoutConstraints.insets = new Insets(10, 45, 0, 10);
            optionalsLayoutConstraints.gridwidth = 1;
            optionalsFrameLayout.setConstraints(applyButton, optionalsLayoutConstraints);
            optionsFrame.add(applyButton);

            JButton cancelButton = new JButton("Cancel");
            cancelButton.setFont(font);
            optionalsLayoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
            optionalsLayoutConstraints.insets = new Insets(10, 10, 0, 45);
            optionalsFrameLayout.setConstraints(cancelButton, optionalsLayoutConstraints);
            optionsFrame.add(cancelButton);

            applyButton.addActionListener(actionEvent -> {
                for (int i = 0; i < standardRadioButtons.length; i++) {
                    if (standardRadioButtons[i].isSelected()) {
                        frameView.saveOptions(defaultOptions[i]);
                        optionsFrame.dispose();

                        return;
                    }
                }

                try {
                    Options newOptions = new Options(
                            Integer.parseInt(desiredWidthTextField.getText()),
                            Integer.parseInt(desiredHeightTextField.getText()),
                            Integer.parseInt(desiredMinesTextField.getText())
                    );

                    frameView.saveOptions(newOptions);
                    optionsFrame.dispose();
                } catch (NumberFormatException exception1) {
                    String message = "Invalid data format." + System.lineSeparator() + "Fill in all fields with numbers";
                    JOptionPane.showMessageDialog(optionsFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException exception2) {
                    String message = "The number of bombs exceeds the number of cells";
                    JOptionPane.showMessageDialog(optionsFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            cancelButton.addActionListener(e -> optionsFrame.dispose());

            optionsFrame.requestFocusInWindow();
            cancelButton.requestFocus();
        });
    }
}
