package ru.oop.nikolenko.temperature_converter.model;

public class TemperatureConverter implements Converter {
    private final String[] scalesNames;
    private final TwoUnitsConverter[] twoUnitsConverters;

    public TemperatureConverter(String[] scalesNames, TwoUnitsConverter[] twoUnitsConverters) {
        for (int i = 0; i < twoUnitsConverters.length; i++) {
            if (!twoUnitsConverters[i].getNameOfFirstUnit().equals(scalesNames[0])) {
                throw new IllegalArgumentException("The first scale of twoUnitsConverters[" + i + "] = \"" + twoUnitsConverters[i].getNameOfFirstUnit() +
                        "\" must be equal to scalesNames[0] = \"" + scalesNames[0] + "\"");
            }

            if (!twoUnitsConverters[i].getNameOfSecondUnit().equals(scalesNames[i + 1])) {
                throw new IllegalArgumentException("The second scale of twoUnitsConverters[" + i + "] = \"" + twoUnitsConverters[i].getNameOfSecondUnit() +
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
            return twoUnitsConverters[resultingUnitIndex - 1].convertDataFromFirstToSecondUnit(temperature);
        }

        if (resultingUnitIndex == 0) {
            return twoUnitsConverters[originalUnitIndex - 1].convertDataFromSecondToFirstUnit(temperature);
        }

        double convertedToMainTemperature = twoUnitsConverters[originalUnitIndex - 1].convertDataFromSecondToFirstUnit(temperature);

        return twoUnitsConverters[resultingUnitIndex - 1].convertDataFromFirstToSecondUnit(convertedToMainTemperature);
    }
}
