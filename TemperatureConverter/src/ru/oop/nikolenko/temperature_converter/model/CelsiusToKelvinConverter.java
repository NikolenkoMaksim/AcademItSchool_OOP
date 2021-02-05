package ru.oop.nikolenko.temperature_converter.model;

public class CelsiusToKelvinConverter implements TwoScalesConverter {
    public CelsiusToKelvinConverter() {
    }

    @Override
    public String getFirstScaleName() {
        return "Celsius";
    }

    @Override
    public String getSecondScaleName() {
        return "Kelvin";
    }

    @Override
    public double convertDataFromFirstToSecondScale(double temperature) {
        return temperature + 273.15;
    }

    @Override
    public double convertDataFromSecondToFirstScale(double temperature) {
        return temperature - 273.15;
    }
}
