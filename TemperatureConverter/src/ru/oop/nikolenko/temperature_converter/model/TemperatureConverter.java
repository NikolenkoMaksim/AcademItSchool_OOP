package ru.oop.nikolenko.temperature_converter.model;

import java.util.List;

public class TemperatureConverter implements Converter {
    private final String[] scalesNames;
    private final List<TwoScalesConverter> twoScalesConverters;

    public TemperatureConverter(String[] scalesNames, List<TwoScalesConverter> twoScalesConverters) {
        for (int i = 0; i < twoScalesConverters.size(); i++) {
            if (!twoScalesConverters.get(i).getFirstScaleName().equals(scalesNames[0])) {
                throw new IllegalArgumentException("The name of first scale of twoScalesConvectors.get(" + i + ") = \"" + twoScalesConverters.get(i).getFirstScaleName() +
                        "\" must be equal to scalesNames[0] = \"" + scalesNames[0] + "\"");
            }

            if (!twoScalesConverters.get(i).getSecondScaleName().equals(scalesNames[i + 1])) {
                throw new IllegalArgumentException("The name of second scale of twoScalesConvectors.get(\"" + i + ") = \"" + twoScalesConverters.get(i).getSecondScaleName() +
                        "\" must be equal to scalesNames[" + (i + 1) + "] = \"" + scalesNames[i + 1] + "\")");
            }
        }

        this.scalesNames = scalesNames;
        this.twoScalesConverters = twoScalesConverters;
    }

    @Override
    public String[] getScalesNames() {
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

        if (originalScaleIndex >= scalesNames.length) {
            throw new IllegalArgumentException("originalScaleIndex (" + originalScaleIndex + ") >= scalesNames.length  (" + scalesNames.length + ")");
        }

        if (resultingScaleIndex >= scalesNames.length) {
            throw new IllegalArgumentException("resultingScaleIndex (" + resultingScaleIndex + ") >= scalesNames.length  (" + scalesNames.length + ")");
        }

        if (originalScaleIndex == resultingScaleIndex) {
            return temperature;
        }

        if (originalScaleIndex == 0) {
            return twoScalesConverters.get(resultingScaleIndex - 1).convertDataFromFirstToSecondScale(temperature);
        }

        if (resultingScaleIndex == 0) {
            return twoScalesConverters.get(originalScaleIndex - 1).convertDataFromSecondToFirstScale(temperature);
        }

        double convertedToMainTemperature = twoScalesConverters.get(originalScaleIndex - 1).convertDataFromSecondToFirstScale(temperature);

        return twoScalesConverters.get(resultingScaleIndex - 1).convertDataFromFirstToSecondScale(convertedToMainTemperature);
    }
}
