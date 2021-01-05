package ru.oop.nikolenko.temperature_converter.controller;

import ru.oop.nikolenko.temperature_converter.model.TemperatureConverter;

public class Controller {
    private final TemperatureConverter temperatureConverter;

    public Controller(TemperatureConverter temperatureConverter) {
        this.temperatureConverter = temperatureConverter;
    }

    public String convertTemperature(String typeOfConversion, double temperature) {
        return switch (typeOfConversion) {
            case "Celsius to Kelvin" -> temperature + " degrees of Celsius = " + String.format("%.3f", temperatureConverter.convertCelsiusToKelvin(temperature)) + " degrees of Kelvin";
            case "Celsius to Fahrenheit" -> temperature + " degrees of Celsius = " + String.format("%.3f", temperatureConverter.convertCelsiusToFahrenheit(temperature)) + " degrees of Fahrenheit";
            case "Kelvin to Celsius" -> temperature + " degrees of Kelvin = " + String.format("%.3f", temperatureConverter.convertKelvinToCelsius(temperature)) + " degrees of Celsius";
            case "Kelvin to Fahrenheit" -> temperature + " degrees of Kelvin = " + String.format("%.3f", temperatureConverter.convertKelvinToFahrenheit(temperature)) + " degrees of Fahrenheit";
            case "Fahrenheit to Celsius" -> temperature + " degrees of Fahrenheit = " + String.format("%.3f", temperatureConverter.convertFahrenheitToCelsius(temperature)) + " degrees of Celsius";
            case "Fahrenheit to Kelvin" -> temperature + " degrees of Fahrenheit = " + String.format("%.3f", temperatureConverter.convertFahrenheitToKelvin(temperature)) + " degrees of Kelvin";
            default -> throw new IllegalArgumentException("Illegal typeOfConversion");
        };
    }
}
