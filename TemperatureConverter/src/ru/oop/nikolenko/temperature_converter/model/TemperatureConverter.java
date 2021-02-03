package ru.oop.nikolenko.temperature_converter.model;

import java.util.List;

public class TemperatureConverter implements Converter {
    private final String[] scalesNames;
    private final List<TwoUnitsConverter> twoUnitsConverters;

    public TemperatureConverter(String[] scalesNames, List<TwoUnitsConverter> twoUnitsConverters) {
        for (int i = 0; i < twoUnitsConverters.size(); i++) {
            if (!twoUnitsConverters.get(i).getFirstUnitName().equals(scalesNames[0])) {
                throw new IllegalArgumentException("The name of first unit of twoUnitsConvectors.get(" + i + ") = \"" + twoUnitsConverters.get(i).getFirstUnitName() +
                        "\" must be equal to scalesNames[0] = \"" + scalesNames[0] + "\"");
            }

            if (!twoUnitsConverters.get(i).getSecondUnitName().equals(scalesNames[i + 1])) {
                throw new IllegalArgumentException("The name of second unit of twoUnitsConvectors.get(\"" + i + ") = \"" + twoUnitsConverters.get(i).getSecondUnitName() +
                        "\" must be equal to scalesNames[" + (i + 1) + "] = \"" + scalesNames[i + 1] + "\")");
            }
        }

        this.scalesNames = scalesNames;
        this.twoUnitsConverters = twoUnitsConverters;
    }

    @Override
    public String[] getScales() {
        return scalesNames;
    }

    @Override
    public double convert(double temperature, int originalUnitIndex, int resultingUnitIndex) {
        if (originalUnitIndex < 0) {
            throw new IllegalArgumentException("originalUnitIndex (" + originalUnitIndex + ") < 0");
        }

        if (resultingUnitIndex < 0) {
            throw new IllegalArgumentException("resultingUnitIndex (" + resultingUnitIndex + ") < 0");
        }

        if (originalUnitIndex >= scalesNames.length) {
            throw new IllegalArgumentException("originalUnitIndex (" + originalUnitIndex + ") >= scalesNames.length  (" + scalesNames.length + ")");
        }

        if (resultingUnitIndex >= scalesNames.length) {
            throw new IllegalArgumentException("resultingUnitIndex (" + resultingUnitIndex + ") >= scalesNames.length  (" + scalesNames.length + ")");
        }

        if (originalUnitIndex == resultingUnitIndex) {
            return temperature;
        }

        if (originalUnitIndex == 0) {
            return twoUnitsConverters.get(resultingUnitIndex - 1).convertDataFromFirstToSecondUnit(temperature);
        }

        if (resultingUnitIndex == 0) {
            return twoUnitsConverters.get(originalUnitIndex - 1).convertDataFromSecondToFirstUnit(temperature);
        }

        double convertedToMainTemperature = twoUnitsConverters.get(originalUnitIndex - 1).convertDataFromSecondToFirstUnit(temperature);

        return twoUnitsConverters.get(resultingUnitIndex - 1).convertDataFromFirstToSecondUnit(convertedToMainTemperature);
    }
}
