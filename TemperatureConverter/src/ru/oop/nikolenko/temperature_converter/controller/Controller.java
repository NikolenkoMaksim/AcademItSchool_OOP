package ru.oop.nikolenko.temperature_converter.controller;

import ru.oop.nikolenko.temperature_converter.model.Converter;

public class Controller implements ConverterController {
    private final Converter temperatureConverter;

    public Controller(Converter temperatureConverter) {
        this.temperatureConverter = temperatureConverter;
    }

    @Override
    public String[] getScalesNames() {
        return temperatureConverter.getScalesNames();
    }

    @Override
    public double convertTemperature(double temperature, String originalScaleName, String resultingScaleName) {
        return temperatureConverter.convert(temperature, getScaleIndex(originalScaleName), getScaleIndex(resultingScaleName));
    }

    private int getScaleIndex(String scaleName) {
        String[] scalesNames = temperatureConverter.getScalesNames();

        for (int i = 0; i < scalesNames.length; i++) {
            if (scalesNames[i].equals(scaleName)) {
                return i;
            }
        }

        throw new IllegalArgumentException("scaleName \"" + scaleName + "\" not found in scalesNames");
    }
}
