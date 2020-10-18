package ru.oop.nikolenko.shape;

import java.util.Comparator;

public class SortByAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.getArea(), o2.getArea());
    }
}