package ru.oop.nikolenko.minesweeper.veiw;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class OptionalsView {
    public static void createOptionalsView(int[] currentOptionals, FrameView frameView) {
        //final String[] categoriesNames = new String[]{"Beginner", "Amateur", "Professional"};
        final int[] defaultBeginnersOptionals = new int[]{9, 9, 10};
        final int[] defaultAmateurOptionals = new int[]{16, 16, 40};
        final int[] defaultProfessionalOptionals = new int[]{30, 16, 99};

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

            JRadioButton beginnersButton = new JRadioButton("Beginner (Field: 9x9; mines: 10)");
            optionalsFrameLayout.setConstraints(beginnersButton, optionalsLayoutConstraints);
            optionsFrame.add(beginnersButton);

            JRadioButton amateurButton = new JRadioButton("Amateur (Field: 16x16; mines: 40)");
            optionalsFrameLayout.setConstraints(amateurButton, optionalsLayoutConstraints);
            optionsFrame.add(amateurButton);

            JRadioButton professionalButton = new JRadioButton("Professional (Field: 30x16; mines: 99)");
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

            if (Arrays.equals(currentOptionals, defaultBeginnersOptionals)) {
                beginnersButton.setSelected(true);
            } else if (Arrays.equals(currentOptionals, defaultAmateurOptionals)) {
                amateurButton.setSelected(true);
            } else if (Arrays.equals(currentOptionals, defaultProfessionalOptionals)) {
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
                    frameView.saveOptions(defaultBeginnersOptionals);
                    optionsFrame.dispose();
                } else if (amateurButton.isSelected()) {
                    frameView.saveOptions(defaultAmateurOptionals);
                    optionsFrame.dispose();
                } else if (professionalButton.isSelected()) {
                    frameView.saveOptions(defaultProfessionalOptionals);
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
                        frameView.saveOptions(specialOptionals);
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
