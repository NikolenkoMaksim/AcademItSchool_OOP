package controller;

public interface ConverterController {
    String[] getScalesNames();

    double convertTemperature(double temperature, String originalScaleName, String resultingScaleName);
}
