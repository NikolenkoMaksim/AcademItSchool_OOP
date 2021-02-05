package ru.oop.nikolenko.temperature_converter.model;

public interface TwoScalesConverter {
    String getFirstScaleName();

    String getSecondScaleName();

    double convertDataFromFirstToSecondScale(double temperature);

    double convertDataFromSecondToFirstScale(double temperature);
}
