public class Rectangle implements Shape {
    private double side1;
    private double side2;

    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public Rectangle(double side1, double side2) {
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
        return "это прямоугольник со сторанами " + side1 +
                " и " + side2 + ",\nплощадью " + getArea() +
                " и периметром " + getPerimeter();
    }
}
