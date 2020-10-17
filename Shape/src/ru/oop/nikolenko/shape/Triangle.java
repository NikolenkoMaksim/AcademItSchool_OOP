package ru.oop.nikolenko.shape;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        final double epsilon = 1.0e-10;

        if (Math.abs((x3 - x1) * (y2 - y1) - (y3 - y1) * (x2 - x1)) <= epsilon) {
            throw new IllegalArgumentException("Точки лежат на одной линии");
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        double perimeterHalf = getPerimeter() / 2;
        double[] triangleSideLengths = getTriangleSideLengths();
        return Math.sqrt(perimeterHalf *
                (perimeterHalf - triangleSideLengths[0]) *
                (perimeterHalf - triangleSideLengths[1]) *
                (perimeterHalf - triangleSideLengths[2]));
    }

    @Override
    public double getPerimeter() {
        double perimeter = 0;
        double[] triangleSideLengths = getTriangleSideLengths();

        for (double triangleSideLength : triangleSideLengths) {
            perimeter += triangleSideLength;
        }

        return perimeter;
    }

    public double[] getTriangleSideLengths() {
        double triangleSide1Length =
                Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        double triangleSide2Length =
                Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
        double triangleSide3Length =
                Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
        return new double[]
                {triangleSide1Length, triangleSide2Length, triangleSide3Length};
    }

    @Override
    public String toString() {
        double[] triangleSideLengths = getTriangleSideLengths();
        return "это треугольник со сторонами " + triangleSideLengths[0] + ", " + triangleSideLengths[1] +
                " и " + triangleSideLengths[2] + ",\nплощадью " + getArea() + " и периметром " + getPerimeter() + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Triangle t = (Triangle) o;
        return x1 == t.x1 && x2 == t.x2 && x3 == t.x3 &&
                y1 == t.y1 && y2 == t.y2 && y3 == t.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 18;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }
}
