package ru.oop.nikolenko.temperature_converter.model;

public interface Converter {
    String[] getScalesNames();

    double convert(double temperature, int originalScaleIndex, int resultingScaleIndex);
}
