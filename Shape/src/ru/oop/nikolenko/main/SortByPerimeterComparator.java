package ru.oop.nikolenko.main;

import ru.oop.nikolenko.shape.Shape;

import java.util.Comparator;

public class SortByPerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.getPerimeter(), o2.getPerimeter());
    }
}
