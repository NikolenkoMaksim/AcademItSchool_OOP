package ru.oop.nikolenko.temperature_converter.main;

import ru.oop.nikolenko.temperature_converter.controller.Controller;
import ru.oop.nikolenko.temperature_converter.model.*;
import ru.oop.nikolenko.temperature_converter.view.FrameView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<TwoUnitsConverter> twoUnitsConverters = new ArrayList<>(Arrays.asList(
                new CelsiusToKelvinConverter(),
                new CelsiusToFahrenheitConverter()
        ));

        TemperatureConverter temperatureConverter = new TemperatureConverter(
                new String[]{"Celsius", "Kelvin", "Fahrenheit"},
                twoUnitsConverters
        );
        Controller controller = new Controller(temperatureConverter);
        FrameView frameView = new FrameView(controller);

        frameView.start();
    }
}
