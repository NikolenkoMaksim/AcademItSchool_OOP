package ru.oop.nikolenko.temperature_converter.main;

import ru.oop.nikolenko.temperature_converter.controller.Controller;
import ru.oop.nikolenko.temperature_converter.model.*;
import ru.oop.nikolenko.temperature_converter.view.FrameView;

public class Main {
    public static void main(String[] args) {
        CelsiusConverter[] celsiusConverters = new CelsiusConverter[]{
                new CelsiusToCelsiusConverter(),
                new CelsiusToKelvinConverter(),
                new CelsiusToFahrenheitConverter()
        };

        TemperatureConverter temperatureConverter = new TemperatureConverter(celsiusConverters);
        Controller controller = new Controller(temperatureConverter);
        FrameView frameView = new FrameView(controller);

        frameView.start();
    }
}
