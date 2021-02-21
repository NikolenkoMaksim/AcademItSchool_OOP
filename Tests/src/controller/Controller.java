/*
package controller;

import ru.oop.nikolenko.temperature_converter.model.Converter;

public class Controller implements ConverterController {
    private final Converter temperatureConverter;
    private final String[] scalesNames;

    public Controller(Converter temperatureConverter) {
        this.temperatureConverter = temperatureConverter;
        scalesNames = temperatureConverter.getScalesNames();
    }

    @Override
    public String[] getScalesNames() {
        return scalesNames;
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

 */
