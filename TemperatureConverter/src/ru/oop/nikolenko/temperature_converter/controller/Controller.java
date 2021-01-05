package ru.oop.nikolenko.temperature_converter.controller;

import ru.oop.nikolenko.temperature_converter.model.TemperatureConverter;

public class Controller {
    private final TemperatureConverter temperatureConverter;

    public Controller(TemperatureConverter temperatureConverter) {
        this.temperatureConverter = temperatureConverter;
    }

    public String convertTemperature(String typeOfConversion, double temperature) {
        return switch (typeOfConversion) {
            case "Celsius to Kelvin" -> temperature + " degrees Celsius = " + String.format("%.3f", temperatureConverter.convertCelsiusToKelvin(temperature)) + " degrees Kelvin";
            case "Celsius to Fahrenheit" -> temperature + " degrees Celsius = " + String.format("%.3f", temperatureConverter.convertCelsiusToFahrenheit(temperature)) + " degrees Fahrenheit";
            case "Kelvin to Celsius" -> temperature + " degrees Kelvin = " + String.format("%.3f", temperatureConverter.convertKelvinToCelsius(temperature)) + " degrees Celsius";
            case "Kelvin to Fahrenheit" -> temperature + " degrees Kelvin = " + String.format("%.3f", temperatureConverter.convertKelvinToFahrenheit(temperature)) + " degrees Fahrenheit";
            case "Fahrenheit to Celsius" -> temperature + " degrees Fahrenheit = " + String.format("%.3f", temperatureConverter.convertFahrenheitToCelsius(temperature)) + " degrees Celsius";
            case "Fahrenheit to Kelvin" -> temperature + " degrees Fahrenheit = " + String.format("%.3f", temperatureConverter.convertFahrenheitToKelvin(temperature)) + " degrees Kelvin";
            default -> throw new IllegalArgumentException("Illegal typeOfConversion");
        };
    }
}
