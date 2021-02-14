package ru.oop.nikolenko.temperature_converter.model;

public interface CelsiusConverter {
    String getSecondScaleName();

    double convertTemperatureFromCelsius(double temperature);

    double convertTemperatureToCelsius(double temperature);
}
