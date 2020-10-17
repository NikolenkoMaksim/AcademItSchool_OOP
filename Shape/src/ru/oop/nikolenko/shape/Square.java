package ru.oop.nikolenko.shape;

public class Square implements Shape {
    private double side;

    public Square(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Строна квадрата меньше или равна 0");
        }

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
        return "это квадварт со стороной " + side + ",\nплощадью " + getArea() + " и периметром " +
                getPerimeter() + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
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
