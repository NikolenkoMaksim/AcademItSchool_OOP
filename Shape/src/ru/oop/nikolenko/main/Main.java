package ru.oop.nikolenko.main;

import ru.oop.nikolenko.shape.*;

import java.util.Arrays;

public class Main {
    public static Shape getMaxAreaFigure(Shape[] shapes) {
        Arrays.sort(shapes, new SortByAreaComparator());
        return shapes[shapes.length - 1];
    }

    public static Shape getFigureWithCountByPerimeterSize(Shape[] shapes, int countByPerimeterSize) {
        Arrays.sort(shapes, new SortByPerimeterComparator());
        return shapes[shapes.length - countByPerimeterSize - 1];
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(5),
                new Triangle(0, 0, 5, 0, 0, 7),
                new Rectangle(4, 3),
                new Circle(1),
                new Square(2),
                new Circle(2)};

        Shape maxAreaFigure = getMaxAreaFigure(shapes);
        System.out.println("Фигура с максимальной площадь: " + maxAreaFigure);

        Shape secondPerimeterFigure = getFigureWithCountByPerimeterSize(shapes, 2);
        System.out.println("Фигура со вторым по велечине периметром: " + secondPerimeterFigure);
    }
}
