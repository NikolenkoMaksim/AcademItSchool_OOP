package ru.oop.nikolenko.temperature_converter.controller;

public interface ConvectorController {
    String[] getScales();

    double convertTemperature(double temperature, String fromScale, String toScale);
}
