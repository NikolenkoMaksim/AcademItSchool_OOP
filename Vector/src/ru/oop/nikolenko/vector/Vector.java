package ru.oop.nikolenko.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int componentsCount) {
        if (componentsCount <= 0) {
            throw new IllegalArgumentException("n <= 0");
        }

        this.components = new double[componentsCount];
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("array is null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("array length is 0");
        }

        components = new double[array.length];
        System.arraycopy(array, 0, components, 0, components.length);
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector is null");
        }

        this.components = new double[vector.components.length];
        System.arraycopy(vector.components, 0, this.components, 0, vector.getSize());
    }

    public Vector(int componentsCount, double[] array) {
        if (componentsCount <= 0) {
            throw new IllegalArgumentException("componentsCount <= 0");
        }

        if (array == null) {
            throw new IllegalArgumentException("array is null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("array length is 0");
        }

        if (componentsCount < array.length) {
            throw new IllegalArgumentException("componentsCount < array.length");
        }

        components = new double[componentsCount];
        System.arraycopy(array, 0, this.components, 0, array.length);
    }

    public double getComponent(int componentIndex) {
        if (componentIndex < 0) {
            throw new ArrayIndexOutOfBoundsException("componentIndex < 0");
        }

        if (componentIndex >= components.length) {
            throw new ArrayIndexOutOfBoundsException("componentIndex >= vector.length");
        }

        return components[componentIndex];
    }

    public void setComponent(int componentIndex, double value) {
        if (componentIndex < 0) {
            throw new ArrayIndexOutOfBoundsException("componentIndex < 0");
        }

        if (componentIndex >= components.length) {
            throw new ArrayIndexOutOfBoundsException("componentIndex >= vector.length");
        }

        components[componentIndex] = value;
    }

    public double[] getComponentsCopy() {
        double[] copy = new double[components.length];
        System.arraycopy(components, 0, copy, 0, components.length);

        return copy;
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder vector = new StringBuilder();

        vector.append("{");

        for (int i = 0; i < this.components.length; i++) {
            vector.append(this.components[i]);

            if (i < this.components.length - 1) {
                vector.append(", ");
            }
        }

        vector.append("}");

        return vector.toString();
    }

    public Vector add(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector is null");
        }

        Vector sumVector = new Vector(Math.max(this.components.length, vector.getSize()));

        int minLength = Math.min(this.components.length, vector.getSize());

        for (int i = 0; i < minLength; i++) {
            sumVector.components[i] = this.components[i] + vector.components[i];
        }

        if (this.components.length != vector.getSize()) {
            if (this.components.length < vector.getSize()) {
                System.arraycopy(vector.components, minLength, sumVector.components, minLength, sumVector.components.length - minLength);
            } else {
                System.arraycopy(this.components, minLength, sumVector.components, minLength, sumVector.components.length - minLength);
            }
        }

        return sumVector;
    }

    public Vector subtract(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector is null");
        }

        Vector resultVector = new Vector(Math.max(this.components.length, vector.getSize()));

        int minLength = Math.min(this.components.length, vector.getSize());

        for (int i = 0; i < minLength; i++) {
            resultVector.components[i] = this.components[i] - vector.components[i];
        }

        if (this.components.length != vector.getSize()) {
            if (this.components.length < vector.getSize()) {
                for (int i = minLength; i < resultVector.components.length; i++) {
                    resultVector.components[i] = -vector.components[i];
                }
            } else {
                System.arraycopy(this.components, minLength,
                        resultVector.components, minLength, resultVector.components.length - minLength);
            }
        }

        return resultVector;
    }

    public Vector multiplyByNumber(double number) {
        Vector multipliedVector = new Vector(components.length);

        for (int i = 0; i < this.components.length; i++) {
            multipliedVector.components[i] = components[i] * number;
        }

        return multipliedVector;
    }

    public Vector invert() {
        return multiplyByNumber(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double v : this.components) {
            sum += v * v;
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

        if (components.length != v.getSize()) {
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
        if (vector1 == null) {
            throw new IllegalArgumentException("first vector is null");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("second vector is null");
        }

        return vector1.add(vector2);
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("first vector is null");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("second vector is null");
        }

        return vector1.subtract(vector2);
    }

    public static double getScalarMultiply(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("first vector is null");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("second vector is null");
        }

        int minLength = Math.min(vector1.getSize(), vector2.getSize());

        double scalarProduct = 0;

        for (int i = 0; i < minLength; i++) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }
}