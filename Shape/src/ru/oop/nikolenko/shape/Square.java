package ru.oop.nikolenko.shape;

public class Square implements Shape {
    private double side;

    public Square(double side) {
        checkSide(side);

        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        checkSide(side);

        this.side = side;
    }

    @Override
    public double getWidth() {
        return side;
    }

    @Override
    public double getHeight() {
        return side;
    }

    private static void checkSide(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("side value " + side + " <= 0");
        }
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public String toString() {
        return "{Square. Side = " + side + ". S = " + getArea() + ". P = " + getPerimeter() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Square s = (Square) o;
        return side == s.side;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(side);
        return hash;
    }
}
