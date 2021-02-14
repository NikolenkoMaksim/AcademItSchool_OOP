package ru.oop.nikolenko.temperature_converter.model;

public class CelsiusToCelsiusConverter implements CelsiusConverter {
    public CelsiusToCelsiusConverter() {
    }

    @Override
    public String getSecondScaleName() {
        return "Celsius";
    }

    @Override
    public double convertTemperatureFromCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertTemperatureToCelsius(double temperature) {
        return temperature;
    }
}
