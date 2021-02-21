package ru.oop.nikolenko.temperature_converter.model;

public class CelsiusScale implements Scale {
    public String getName() {
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
