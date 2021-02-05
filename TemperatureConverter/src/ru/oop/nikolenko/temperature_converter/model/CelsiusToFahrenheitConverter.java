package ru.oop.nikolenko.temperature_converter.model;

public class CelsiusToFahrenheitConverter implements TwoScalesConverter {
    @Override
    public String getFirstScaleName() {
        return "Celsius";
    }

    @Override
    public String getSecondScaleName() {
        return "Fahrenheit";
    }

    @Override
    public double convertDataFromFirstToSecondScale(double temperature) {
        return temperature * 1.8 + 32;
    }

    @Override
    public double convertDataFromSecondToFirstScale(double temperature) {
        return (temperature - 32) * 5 / 9;
    }
}
