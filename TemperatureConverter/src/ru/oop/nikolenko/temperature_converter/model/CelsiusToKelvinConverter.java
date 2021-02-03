package ru.oop.nikolenko.temperature_converter.model;

public class CelsiusToKelvinConverter implements TwoUnitsConverter {
    public CelsiusToKelvinConverter() {
    }

    @Override
    public String getFirstUnitName() {
        return "Celsius";
    }

    @Override
    public String getSecondUnitName() {
        return "Kelvin";
    }

    @Override
    public double convertDataFromFirstToSecondUnit(double temperature) {
        return temperature + 273.15;
    }

    @Override
    public double convertDataFromSecondToFirstUnit(double temperature) {
        return temperature - 273.15;
    }
}
