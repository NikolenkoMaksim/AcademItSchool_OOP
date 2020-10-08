package Main;

import Shape.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    static class SortByArea implements Comparator<Shape> {
        @Override
        public int compare(Shape o1, Shape o2) {
            return (int) (o2.getArea() - o1.getArea());
        }
    }

    static class SortByPerimeter implements Comparator<Shape> {
        @Override
        public int compare(Shape o1, Shape o2) {
            return (int) (o2.getPerimeter() - o1.getPerimeter());
        }
    }

    public static Shape getMaxAreaFigure(Shape[] figures) {
        Arrays.sort(figures, new SortByArea());
        return figures[0];
    }

    public static Shape getNByPerimeterFigure(Shape[] figures, int countByPerimeterSize) {
        Arrays.sort(figures, new SortByPerimeter());
        return figures[countByPerimeterSize - 1];
    }

    public static void main(String[] args) {
        Shape[] figures = {
                new Square(3.5),
                new Triangle(0, 0, 5, 0, 0, 7),
                new Rectangle(4, 3),
                new Circle(1),
                new Square(2),
                new Circle(2)};

        Shape maxAreaFigure = getMaxAreaFigure(figures);

        System.out.println("Фигура с максимальной площадью " + maxAreaFigure);

        Shape secondPerimeterFigure = getNByPerimeterFigure(figures, 2);

        System.out.println("Фигура со вторым по велечине периметром " + secondPerimeterFigure);

    }
}
