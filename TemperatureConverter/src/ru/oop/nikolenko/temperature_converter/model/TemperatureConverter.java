package ru.oop.nikolenko.temperature_converter.model;

public class TemperatureConverter implements Converter {
    private final Scale[] celsiusConverters;
    private final String[] scalesNames;

    public TemperatureConverter(Scale[] celsiusConverters) {
        this.celsiusConverters = celsiusConverters;

        scalesNames = new String[celsiusConverters.length];

        for (int i = 0; i < celsiusConverters.length; i++) {
            scalesNames[i] = celsiusConverters[i].getName();
        }
    }

    @Override
    public String[] getScalesNames() {
        return scalesNames;
    }

    @Override
    public double convert(double temperature, int originalScaleIndex, int resultingScaleIndex) {
        if (originalScaleIndex < 0) {
            throw new IndexOutOfBoundsException("originalScaleIndex (" + originalScaleIndex + ") < 0");
        }

        if (resultingScaleIndex < 0) {
            throw new IndexOutOfBoundsException("resultingScaleIndex (" + resultingScaleIndex + ") < 0");
        }

        if (originalScaleIndex >= celsiusConverters.length) {
            throw new IndexOutOfBoundsException("originalScaleIndex (" + originalScaleIndex + ") >= celsiusConverters.length  (" + celsiusConverters.length + ")");
        }

        if (resultingScaleIndex >= celsiusConverters.length) {
            throw new IndexOutOfBoundsException("resultingScaleIndex (" + resultingScaleIndex + ") >= celsiusConverters.length  (" + celsiusConverters.length + ")");
        }

        if (originalScaleIndex == resultingScaleIndex) {
            return temperature;
        }

        double temperatureInCelsius = celsiusConverters[originalScaleIndex].convertTemperatureToCelsius(temperature);

        return celsiusConverters[resultingScaleIndex].convertTemperatureFromCelsius(temperatureInCelsius);
    }
}
