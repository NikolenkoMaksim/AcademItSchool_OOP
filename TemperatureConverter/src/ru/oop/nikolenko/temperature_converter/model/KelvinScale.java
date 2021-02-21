package ru.oop.nikolenko.temperature_converter.model;

public class KelvinScale implements Scale {
    public String getName() {
        return "Kelvin";
    }

    @Override
    public double convertTemperatureFromCelsius(double temperature) {
        return temperature + 273.15;
    }

    @Override
    public double convertTemperatureToCelsius(double temperature) {
        return temperature - 273.15;
    }
}
