package ru.oop.nikolenko.temperature_converter.model;

public interface Convecter {
    String[] getScales();

    double convert(double temperature, int scale1Index, int scale2Index);
}
