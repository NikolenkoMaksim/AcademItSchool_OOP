package ru.oop.nikolenko.temperature_converter.controller;

public interface ConverterController {
    String[] getScalesNames();

    double convertTemperature(double temperature, String originalScaleName, String resultingScaleName);
}
