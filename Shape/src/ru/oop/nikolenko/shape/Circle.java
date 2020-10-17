package ru.oop.nikolenko.shape;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Радиус круга меньше или равен 0");
        }

        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return 2 * radius;
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    @Override
    public String toString() {
        return "это круг с радиусом " + radius + ",\nплощадью " + getArea() + " и периметром " + getPerimeter() + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Circle c = (Circle) o;
        return radius == c.radius;
    }

    @Override
    public int hashCode() {
        final int prime = 23;
        int hash = 1;
        hash = prime * hash + Double.hashCode(radius);
        return hash;
    }
}
