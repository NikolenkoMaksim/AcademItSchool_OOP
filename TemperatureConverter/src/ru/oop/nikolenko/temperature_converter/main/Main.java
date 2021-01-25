package ru.oop.nikolenko.temperature_converter.main;

import ru.oop.nikolenko.temperature_converter.controller.Controller;
import ru.oop.nikolenko.temperature_converter.model.TwoUnitsConverter;
import ru.oop.nikolenko.temperature_converter.model.TemperatureConverter;
import ru.oop.nikolenko.temperature_converter.view.FrameView;

import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        UnaryOperator<Double> operatorFromCelsiusToKelvinConversion = temperature -> temperature + 273.15;
        UnaryOperator<Double> operatorFromKelvinToCelsiusConversion = temperature -> temperature - 273.15;
        UnaryOperator<Double> operatorFromCelsiusToFahrenheitConversion = temperature -> temperature * 1.8 + 32;
        UnaryOperator<Double> operatorFromFahrenheitToCelsiusConversion = temperature -> (temperature - 32) * 5 / 9;

        TwoUnitsConverter[] twoUnitsConverters = new TwoUnitsConverter[]{
                new TwoUnitsConverter("Celsius", "Kelvin", operatorFromCelsiusToKelvinConversion, operatorFromKelvinToCelsiusConversion),
                new TwoUnitsConverter("Celsius", "Fahrenheit", operatorFromCelsiusToFahrenheitConversion, operatorFromFahrenheitToCelsiusConversion)
        };

        TemperatureConverter temperatureConverter = new TemperatureConverter(
                new String[]{"Celsius", "Kelvin", "Fahrenheit"},
                twoUnitsConverters
        );
        Controller controller = new Controller(temperatureConverter);
        FrameView frameView = new FrameView(controller);

        frameView.start();
    }
}
