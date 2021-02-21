package ru.oop.nikolenko.temperature_converter.model;

public interface Scale {
    String getName();

    double convertTemperatureFromCelsius(double temperature);

    double convertTemperatureToCelsius(double temperature);
}
