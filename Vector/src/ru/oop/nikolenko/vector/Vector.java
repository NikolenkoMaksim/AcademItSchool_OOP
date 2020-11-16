package ru.oop.nikolenko.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int componentsCount) {
        if (componentsCount <= 0) {
            throw new IllegalArgumentException("componentsCount value " + componentsCount + " <= 0");
        }

        components = new double[componentsCount];
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("array is null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("array length is 0");
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(Vector vector) {
        checkVector(vector);

        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(int componentsCount, double[] array) {
        if (componentsCount <= 0) {
            throw new IllegalArgumentException("componentsCount value " + componentsCount + " <= 0");
        }

        if (array == null) {
            throw new IllegalArgumentException("array is null");
        }

        components = new double[componentsCount];
        System.arraycopy(array, 0, components, 0, Math.min(componentsCount, array.length));
    }

    public double getComponent(int index) {
        checkIndex(index);

        return components[index];
    }

    public void setComponent(int index, double value) {
        checkIndex(index);

        components[index] = value;
    }

    public double[] getComponentsCopy() {
        return Arrays.copyOf(components, components.length);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (double c : components) {
            stringBuilder.append(c).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        checkVector(vector);

        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        checkVector(vector);

        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByNumber(double number) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= number;
        }
    }

    public void invert() {
        multiplyByNumber(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double c : components) {
            sum += c * c;
        }

        return Math.sqrt(sum);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector v = (Vector) o;

        if (components.length != v.components.length) {
            return false;
        }

        for (int i = 0; i < components.length; i++) {
            if (components[i] != v.components[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 139;
        int hash = 1;
        return prime * hash + Arrays.hashCode(components);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        checkVectors(vector1, vector2);

        Vector sum = new Vector(vector1);
        sum.add(vector2);

        return sum;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        checkVectors(vector1, vector2);

        Vector difference = new Vector(vector1);
        difference.subtract(vector2);

        return difference;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        checkVectors(vector1, vector2);

        int minLength = Math.min(vector1.components.length, vector2.components.length);

        double scalarProduct = 0;

        for (int i = 0; i < minLength; i++) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }

    private static void checkVector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector is null");
        }
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index value " + index + " < 0");
        }

        if (index >= components.length) {
            throw new IndexOutOfBoundsException("index (" + index + ") >= vector.size (" + components.length + ")");
        }
    }

    private static void checkVectors(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("first vector is null");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("second vector is null");
        }
    }
}