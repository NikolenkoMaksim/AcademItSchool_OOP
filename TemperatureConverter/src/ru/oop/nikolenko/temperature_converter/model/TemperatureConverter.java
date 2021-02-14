package ru.oop.nikolenko.temperature_converter.model;

public class TemperatureConverter implements Converter {
    private final CelsiusConverter[] celsiusConverters;

    public TemperatureConverter(CelsiusConverter[] celsiusConverters) {
        this.celsiusConverters = celsiusConverters;
    }

    @Override
    public String[] getScalesNames() {
        String[] scalesNames = new String[celsiusConverters.length];

        for (int i = 0; i < celsiusConverters.length; i++) {
            scalesNames[i] = celsiusConverters[i].getSecondScaleName();
        }

        return scalesNames;
    }

    @Override
    public double convert(double temperature, int originalScaleIndex, int resultingScaleIndex) {
        if (originalScaleIndex < 0) {
            throw new IllegalArgumentException("originalScaleIndex (" + originalScaleIndex + ") < 0");
        }

        if (resultingScaleIndex < 0) {
            throw new IllegalArgumentException("resultingScaleIndex (" + resultingScaleIndex + ") < 0");
        }

        if (originalScaleIndex >= celsiusConverters.length) {
            throw new IllegalArgumentException("originalScaleIndex (" + originalScaleIndex + ") >= celsiusConverters.length  (" + celsiusConverters.length + ")");
        }

        if (resultingScaleIndex >= celsiusConverters.length) {
            throw new IllegalArgumentException("resultingScaleIndex (" + resultingScaleIndex + ") >= celsiusConverters.length  (" + celsiusConverters.length + ")");
        }

        if (originalScaleIndex == resultingScaleIndex) {
            return temperature;
        }

        double temperatureInCelsius = celsiusConverters[originalScaleIndex].convertTemperatureToCelsius(temperature);

        return celsiusConverters[resultingScaleIndex].convertTemperatureFromCelsius(temperatureInCelsius);
    }
}
