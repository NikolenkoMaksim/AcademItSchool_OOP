package ru.oop.nikolenko.temperature_converter.view;

import ru.oop.nikolenko.temperature_converter.controller.ConverterController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class FrameView implements View {
    private final ConverterController controller;

    public FrameView(ConverterController controller) {
        this.controller = controller;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            final int frameDefaultWidth = 250;
            final int frameDefaultHeight = 330;
            final int frameMinimumWidth = 250;
            final int frameMinimumHeight = 330;

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            JFrame frame = new JFrame("Temperature Converter");
            frame.setLocation((screenSize.width - frameDefaultWidth) / 2, (screenSize.height - frameDefaultHeight) / 2);
            frame.setMinimumSize(new Dimension(frameMinimumWidth, frameMinimumHeight));
            frame.setSize(new Dimension(frameDefaultWidth, frameDefaultHeight));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.getRootPane().setBorder(new EmptyBorder(15, 15, 15, 15));

            GridBagLayout frameLayout = new GridBagLayout();
            frame.setLayout(frameLayout);
            frame.setMinimumSize(new Dimension(frameMinimumWidth, frameMinimumHeight));

            JLabel valueLabel = new JLabel("Temperature value:");
            Font font = new Font("", Font.PLAIN, 15);
            valueLabel.setFont(font);

            GridBagConstraints constraints = new GridBagConstraints();

            constraints.anchor = GridBagConstraints.WEST;
            constraints.fill = GridBagConstraints.NONE;
            constraints.gridheight = 1;
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            constraints.gridx = GridBagConstraints.RELATIVE;
            constraints.gridy = GridBagConstraints.RELATIVE;
            constraints.insets = new Insets(0, 15, 0, 0);
            constraints.ipadx = 0;
            constraints.ipady = 0;
            constraints.weightx = 1.0;
            constraints.weighty = 0.0;

            frameLayout.setConstraints(valueLabel, constraints);
            frame.add(valueLabel);

            JTextField inputText = new JTextField(20);
            inputText.setFont(font);
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(10, 0, 0, 0);
            frameLayout.setConstraints(inputText, constraints);
            frame.add(inputText);

            JLabel labelConvertFrom = new JLabel("Convert from");
            labelConvertFrom.setFont(font);
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(10, 15, 0, 0);
            frameLayout.setConstraints(labelConvertFrom, constraints);
            frame.add(labelConvertFrom);

            String[] scales = controller.getScalesNames();
            JComboBox<String> originalScalesNames = new JComboBox<>(scales);
            originalScalesNames.setFont(font);
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(10, 0, 0, 0);
            constraints.weightx = 1;
            frameLayout.setConstraints(originalScalesNames, constraints);
            frame.add(originalScalesNames);

            JLabel labelConvertTo = new JLabel("To");
            labelConvertTo.setFont(font);
            constraints.fill = GridBagConstraints.NONE;
            constraints.insets = new Insets(10, 15, 0, 0);
            frameLayout.setConstraints(labelConvertTo, constraints);
            frame.add(labelConvertTo);

            JComboBox<String> resultingScalesNames = new JComboBox<>(scales);
            resultingScalesNames.setFont(font);
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(10, 0, 0, 0);
            frameLayout.setConstraints(resultingScalesNames, constraints);
            frame.add(resultingScalesNames);

            JButton buttonConvert = new JButton("Convert");
            buttonConvert.setFont(font);
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.fill = GridBagConstraints.NONE;
            frameLayout.setConstraints(buttonConvert, constraints);
            frame.add(buttonConvert);

            JTextArea result = new JTextArea(5, 40);
            result.setEditable(false);
            result.setWrapStyleWord(true);
            result.setLineWrap(true);
            result.setFont(font);
            constraints.fill = GridBagConstraints.BOTH;
            constraints.weighty = 1.0;
            frameLayout.setConstraints(result, constraints);
            frame.add(result);

            ActionListener comboBoxListener = e -> result.setText("");
            originalScalesNames.addActionListener(comboBoxListener);
            resultingScalesNames.addActionListener(comboBoxListener);

            buttonConvert.addActionListener(actionEvent -> {
                String originalScaleName = (String) originalScalesNames.getSelectedItem();
                String resultingScaleName = (String) resultingScalesNames.getSelectedItem();
                double convertTemperature;
                result.setText("");

                try {
                    double inputTemperature = Double.parseDouble(inputText.getText());
                    convertTemperature = controller.convertTemperature(inputTemperature, originalScaleName, resultingScaleName);
                    result.setText(String.valueOf(convertTemperature));
                } catch (NumberFormatException | NullPointerException e) {
                    String errorMessage = "It is necessary to fill in the temperature value with digits." +
                            System.lineSeparator() + "Use point as decimal separator.";
                    JOptionPane.showMessageDialog(frame, errorMessage, "Warning", JOptionPane.WARNING_MESSAGE);
                }
            });

            buttonConvert.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    if (frame.getWidth() < frameDefaultWidth) {
                        frame.setSize(frameDefaultWidth, frame.getHeight());
                    }

                    if (frame.getHeight() < frameDefaultHeight) {
                        frame.setSize(frame.getWidth(), frameDefaultHeight);
                    }
                }
            });
        });
    }
}

