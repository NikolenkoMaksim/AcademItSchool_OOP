package ru.oop.nikolenko.temperature_converter.controller;

import ru.oop.nikolenko.temperature_converter.model.Convecter;

public class Controller implements ConvectorController {
    private final Convecter temperatureConverter;

    public Controller(Convecter temperatureConverter) {
        this.temperatureConverter = temperatureConverter;
    }

    @Override
    public String[] getScales() {
        return temperatureConverter.getScales();
    }

    @Override
    public double convertTemperature(double temperature, String fromScale, String toScale) {
        return temperatureConverter.convert(temperature, getScaleIndex(fromScale), getScaleIndex(toScale));
    }

    private int getScaleIndex(String scale) {
        String[] scales = temperatureConverter.getScales();

        for (int i = 0; i < scales.length; i++) {
            if (scales[i].equals(scale)) {
                return i;
            }
        }

        throw new IllegalArgumentException("scale \"" + scale + "\" not found in scales");
    }
}
