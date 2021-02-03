package ru.oop.nikolenko.temperature_converter.model;

public class CelsiusToFahrenheitConverter implements TwoUnitsConverter {
    @Override
    public String getFirstUnitName() {
        return "Celsius";
    }

    @Override
    public String getSecondUnitName() {
        return "Fahrenheit";
    }

    @Override
    public double convertDataFromFirstToSecondUnit(double temperature) {
        return temperature * 1.8 + 32;
    }

    @Override
    public double convertDataFromSecondToFirstUnit(double temperature) {
        return (temperature - 32) * 5 / 9;
    }
}
