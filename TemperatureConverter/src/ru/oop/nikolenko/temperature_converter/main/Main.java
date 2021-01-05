package ru.oop.nikolenko.temperature_converter.main;

import ru.oop.nikolenko.temperature_converter.controller.Controller;
import ru.oop.nikolenko.temperature_converter.model.TemperatureConverter;
import ru.oop.nikolenko.temperature_converter.veiw.FrameView;

public class Main {
    public static void main(String[] args) {
        TemperatureConverter temperatureConverter = new TemperatureConverter();
        Controller controller = new Controller(temperatureConverter);
        FrameView frameView = new FrameView(controller);

        frameView.start();
    }
}
