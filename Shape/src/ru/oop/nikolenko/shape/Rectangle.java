package ru.oop.nikolenko.shape;

public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        if (width <= 0) {
            throw new IllegalArgumentException("width <= 0");
        }

        if (height <= 0) {
            throw new IllegalArgumentException("height <= 0");
        }

        this.width = width;
        this.height = height;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("width <= 0");
        }

        this.width = width;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("height <= 0");
        }

        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "{Rectangle. Width = " + width + ", height = " + height + ", S = " + getArea() + ", P = " + getPerimeter() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Rectangle r = (Rectangle) o;
        return width == r.width && height == r.height;
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int hash = 1;
        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);
        return hash;
    }
}
