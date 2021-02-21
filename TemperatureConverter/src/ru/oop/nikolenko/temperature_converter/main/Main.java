package ru.oop.nikolenko.temperature_converter.main;

import ru.oop.nikolenko.temperature_converter.model.*;
import ru.oop.nikolenko.temperature_converter.view.FrameView;

public class Main {
    public static void main(String[] args) {
        Scale[] celsiusConverters = new Scale[]{
                new CelsiusScale(),
                new KelvinScale(),
                new FahrenheitScale()
        };

        TemperatureConverter temperatureConverter = new TemperatureConverter(celsiusConverters);
        FrameView frameView = new FrameView(temperatureConverter);

        frameView.start();
    }
}
