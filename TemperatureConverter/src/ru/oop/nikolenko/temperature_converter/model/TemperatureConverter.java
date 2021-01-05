package ru.oop.nikolenko.temperature_converter.model;

public class TemperatureConverter {
    public double convertCelsiusToKelvin(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }

    public double convertCelsiusToFahrenheit(double celsiusTemperature) {
        return celsiusTemperature * 1.8 + 32;
    }

    public double convertKelvinToCelsius(double kelvinTemperature) {
        return kelvinTemperature - 273.15;
    }

    public double convertKelvinToFahrenheit(double kelvinTemperature) {
        return (kelvinTemperature - 273.15) * 1.8 + 32;
    }

    public double convertFahrenheitToCelsius(double fahrenheitTemperature) {
        return (fahrenheitTemperature - 32) * 5 / 9;
    }

    public double convertFahrenheitToKelvin(double fahrenheitTemperature) {
        return (fahrenheitTemperature - 32) * 5 / 9 + 273.15;
    }
}
