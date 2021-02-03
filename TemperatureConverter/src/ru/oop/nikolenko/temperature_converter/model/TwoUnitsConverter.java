package ru.oop.nikolenko.temperature_converter.model;

public interface TwoUnitsConverter {
    String getFirstUnitName();

    String getSecondUnitName();

    double convertDataFromFirstToSecondUnit(double temperature);

    double convertDataFromSecondToFirstUnit(double temperature);
}
