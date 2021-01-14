package ru.oop.nikolenko.temperature_converter.model;

public class TemperatureConverter implements Convecter {
    private final String[] scales = new String[]{
            "Celsius",
            "Kelvin",
            "Fahrenheit"
    };
    private final int mainScaleIndex = 0;

    @Override
    public String[] getScales() {
        return scales;
    }

    @Override
    public double convert(double temperature, int scale1Index, int scale2Index) {
        if (scale1Index < 0) {
            throw new IllegalArgumentException("scale1Index (" + scale1Index + ") < 0");
        }

        if (scale2Index < 0) {
            throw new IllegalArgumentException("scale2Index (" + scale2Index + ") < 0");
        }

        if (scale1Index >= scales.length) {
            throw new IllegalArgumentException("scale1Index (" + scale1Index + ") >= scales.length  (" + scales.length + ")");
        }

        if (scale2Index >= scales.length) {
            throw new IllegalArgumentException("scale2Index (" + scale2Index + ") >= scales.length  (" + scales.length + ")");
        }

        if (scale1Index == scale2Index) {
            return temperature;
        }

        if (scale1Index == mainScaleIndex) {
            return convertFromMainScale(temperature, scale2Index);
        }

        if (scale2Index == mainScaleIndex) {
            return convertToMainScale(temperature, scale1Index);
        }

        double convertedToMainTemperature = convertToMainScale(temperature, scale1Index);

        return convertFromMainScale(convertedToMainTemperature, scale2Index);
    }

    private double convertFromMainScale(double temperature, int scaleIndex) {
        if (scaleIndex >= scales.length) {
            throw new IllegalArgumentException("scaleIndex (" + scaleIndex + ") >= scales.length  (" + scales.length + ")");
        }

        if (scaleIndex == mainScaleIndex) {
            return temperature;
        }

        if (scaleIndex == 1) {
            return convertCelsiusToKelvin(temperature);
        }

        if (scaleIndex == 2) {
            return convertCelsiusToFahrenheit(temperature);
        }

        throw new IllegalArgumentException("scaleIndex " + scaleIndex + " can't be convert in currentVersion");
    }

    private double convertToMainScale(double temperature, int scaleIndex) {
        if (scaleIndex >= scales.length) {
            throw new IllegalArgumentException("scaleIndex (" + scaleIndex + ") >= scales.length  (" + scales.length + ")");
        }

        if (scaleIndex == mainScaleIndex) {
            return temperature;
        }

        if (scaleIndex == 1) {
            return convertKelvinToCelsius(temperature);
        }

        if (scaleIndex == 2) {
            return convertFahrenheitToCelsius(temperature);
        }

        throw new IllegalArgumentException("scaleIndex " + scaleIndex + " can't be convert in currentVersion");
    }

    private double convertCelsiusToKelvin(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }

    public double convertCelsiusToFahrenheit(double celsiusTemperature) {
        return celsiusTemperature * 1.8 + 32;
    }

    public double convertKelvinToCelsius(double kelvinTemperature) {
        return kelvinTemperature - 273.15;
    }

    public double convertFahrenheitToCelsius(double fahrenheitTemperature) {
        return (fahrenheitTemperature - 32) * 5 / 9;
    }
}
