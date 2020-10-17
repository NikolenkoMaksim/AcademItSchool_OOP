package ru.oop.nikolenko.shape;

public class Rectangle implements Shape {
    private double side1;
    private double side2;

    public Rectangle(double side1, double side2) {
        if (side1 <= 0 || side2 <= 0) {
            throw new IllegalArgumentException("Строна прямоугольника меньше или равна 0");
        }

        this.side1 = side1;
        this.side2 = side2;
    }

    @Override
    public double getWidth() {
        return side1;
    }

    @Override
    public double getHeight() {
        return side2;
    }

    @Override
    public double getArea() {
        return side1 * side2;
    }

    @Override
    public double getPerimeter() {
        return 2 * (side1 + side2);
    }

    @Override
    public String toString() {
        return "это прямоугольник со сторанами " + side1 + " и " + side2 + ",\nплощадью " + getArea() +
                " и периметром " + getPerimeter() + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Rectangle r = (Rectangle) o;
        return side1 == r.side1 && side2 == r.side2;
    }

    @Override
    public int hashCode() {
        final int prime = 960;
        int hash = 1;
        hash = prime * hash + Double.hashCode(side1);
        hash = prime * hash + Double.hashCode(side2);
        return hash;
    }
}
