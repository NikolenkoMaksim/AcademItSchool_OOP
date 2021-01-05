package ru.oop.nikolenko.temperature_converter.view;

import ru.oop.nikolenko.temperature_converter.controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrameView implements View {
    private final Controller controller;

    public FrameView(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            final int frameDefaultWidth = 430;
            final int frameDefaultHeight = 550;
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            JFrame frame = new JFrame("Temperature Converter");
            frame.setLocation((screenSize.width - frameDefaultWidth) / 2, (screenSize.height - frameDefaultHeight) / 2);
            frame.setSize(frameDefaultWidth, frameDefaultHeight);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.getRootPane().setBorder(new EmptyBorder(20, 25, 20, 20));

            GridBagLayout gbl = new GridBagLayout();
            frame.setLayout(gbl);

            JLabel valueLabel = new JLabel("Temperature value:");
            Font font = new Font("", Font.PLAIN, 15);
            valueLabel.setFont(font);

            GridBagConstraints c = new GridBagConstraints();

            c.anchor = GridBagConstraints.WEST;
            c.fill = GridBagConstraints.NONE;
            c.gridheight = 1;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridx = GridBagConstraints.RELATIVE;
            c.gridy = GridBagConstraints.RELATIVE;
            c.insets = new Insets(20, 15, 0, 0);
            c.ipadx = 0;
            c.ipady = 0;
            c.weightx = 1.0;
            c.weighty = 0.0;

            gbl.setConstraints(valueLabel, c);
            frame.add(valueLabel);

            JTextField inputText = new JTextField(20);
            inputText.setFont(font);
            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.insets = new Insets(20, 0, 0, 0);
            gbl.setConstraints(inputText, c);
            frame.add(inputText);

            JLabel convertLabel = new JLabel("Convert");
            convertLabel.setFont(font);
            c.anchor = GridBagConstraints.WEST;
            c.fill = GridBagConstraints.NONE;
            c.gridwidth = 1;
            c.insets = new Insets(20, 25, 0, 0);
            gbl.setConstraints(convertLabel, c);
            frame.add(convertLabel);

            String[] typeOfConversionElements = new String[]{
                    ("Celsius to Kelvin"),
                    ("Celsius to Fahrenheit"),
                    ("Kelvin to Celsius"),
                    ("Kelvin to Fahrenheit"),
                    ("Fahrenheit to Celsius"),
                    ("Fahrenheit to Kelvin")
            };
            JComboBox<String> typeOfConversion = new JComboBox<>(typeOfConversionElements);
            typeOfConversion.setFont(font);
            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.insets = new Insets(20, 0, 0, 0);
            gbl.setConstraints(typeOfConversion, c);
            frame.add(typeOfConversion);

            JButton convertButton = new JButton("Convert");
            convertButton.setFont(font);
            c.fill = GridBagConstraints.NONE;
            gbl.setConstraints(convertButton, c);
            frame.add(convertButton);

            JTextArea outputText = new JTextArea(5, 40);
            outputText.setEditable(false);
            outputText.setWrapStyleWord(true);
            outputText.setLineWrap(true);
            outputText.setFont(font);
            c.fill = GridBagConstraints.BOTH;
            c.weighty = 1.0;
            gbl.setConstraints(outputText, c);
            frame.add(outputText);

            convertButton.addActionListener(e -> {
                String inputString = (String) typeOfConversion.getSelectedItem();
                String outputString = "";

                try {
                    double temperatureValue = Double.parseDouble(inputText.getText());
                    outputString = controller.convertTemperature(inputString, temperatureValue);
                } catch (NumberFormatException | NullPointerException nE) {
                    String errorMessage = "It is necessary to fill in the temperature value with digits.\n" +
                            "Use period as decimal separator.";
                    JOptionPane.showMessageDialog(frame, errorMessage, "Inane warning", JOptionPane.WARNING_MESSAGE);
                } catch (IllegalArgumentException iE) {
                    JOptionPane.showMessageDialog(frame, iE.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }

                outputText.setText(outputString);
            });
        });
    }
}

