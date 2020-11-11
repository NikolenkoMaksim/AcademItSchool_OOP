package ru.oop.nikolenko.shape;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        checkPointsCollinearity(x1, y1, x2, y2, x3, y3);

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private static void checkPointsCollinearity(double x1, double y1, double x2, double y2, double x3, double y3) {
        final double epsilon = 1.0e-10;

        if (Math.abs((x3 - x1) * (y2 - y1) - (y3 - y1) * (x2 - x1)) <= epsilon) {
            throw new IllegalArgumentException("points (" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + "), (" + x3 + ", " + y3 + ") are collinear");
        }
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        checkPointsCollinearity(x1, y1, x2, y2, x3, y3);

        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        checkPointsCollinearity(x1, y1, x2, y2, x3, y3);

        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        checkPointsCollinearity(x1, y1, x2, y2, x3, y3);

        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        checkPointsCollinearity(x1, y1, x2, y2, x3, y3);

        this.y2 = y2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        checkPointsCollinearity(x1, y1, x2, y2, x3, y3);

        this.x3 = x3;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        checkPointsCollinearity(x1, y1, x2, y2, x3, y3);

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
        double[] triangleSideLengths = getTriangleSidesLengths();
        double perimeterHalf = (triangleSideLengths[0] + triangleSideLengths[1] + triangleSideLengths[2]) / 2;

        return Math.sqrt(perimeterHalf *
                (perimeterHalf - triangleSideLengths[0]) *
                (perimeterHalf - triangleSideLengths[1]) *
                (perimeterHalf - triangleSideLengths[2]));
    }

    @Override
    public double getPerimeter() {
        double perimeter = 0;
        double[] triangleSidesLengths = getTriangleSidesLengths();

        for (double triangleSideLength : triangleSidesLengths) {
            perimeter += triangleSideLength;
        }

        return perimeter;
    }

    private double[] getTriangleSidesLengths() {
        return new double[]{getSideLength(x1, y1, x2, y2), getSideLength(x1, y1, x3, y3), getSideLength(x2, y2, x3, y3)};
    }

    private static double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public String toString() {
        return "{Triangle. Apexes coordinates: {" + x1 + ", " + y1 + "}, {" + x2 + ", " + y2 + "}, {" + x3 + ", " + y3 +
                "}. S = " + getArea() + ". P = " + getPerimeter() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Triangle t = (Triangle) o;
        return x1 == t.x1 && x2 == t.x2 && x3 == t.x3 &&
                y1 == t.y1 && y2 == t.y2 && y3 == t.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 47;
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
