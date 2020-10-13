package Vector;

import java.util.Arrays;

public class Vector {
    private double[] vector;

    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    public void setVectorComponent(double value, int componentNumber) {
        if (componentNumber < 0) {
            throw new IllegalArgumentException("n < 0");
        }

        if (componentNumber >= vector.length) {
            throw new IllegalArgumentException("n >= vector.length");
        }

        vector[componentNumber] = value;
    }

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0");
        }

        double[] vector = new double[n];

        for (int i = 0; i < n; i++) {
            vector[i] = 0;
        }

        this.vector = vector;
    }

    public Vector(double[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Array is null");
        }

        this.vector = vector;
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Vector is null");
        }

        this.vector = new double[vector.getSize()];
        System.arraycopy(vector.vector, 0, this.vector, 0, vector.getSize());
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0");
        }

        if (array == null) {
            throw new IllegalArgumentException("Array is null");
        }

        vector = new double[n];

        System.arraycopy(array, 0, this.vector, 0, array.length);

        for (int i = array.length; i < n; i++) {
            vector[i] = 0;
        }
    }

    public int getSize() {
        return vector.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(vector);
    }

    public void sum(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Vector is null");
        }

        if (this.vector.length != vector.getSize()) {
            throw new IllegalArgumentException("Vectors have different dimensions");
        }

        for (int i = 0; i < this.vector.length; i++) {
            this.vector[i] += vector.vector[i];
        }
    }

    public void dif(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Vector is null");
        }

        if (this.vector.length != vector.getSize()) {
            throw new IllegalArgumentException("Vectors have different dimensions");
        }

        for (int i = 0; i < this.vector.length; i++) {
            this.vector[i] -= vector.vector[i];
        }
    }

    public void multiplyByNumber(int n) {
        for (int i = 0; i < this.vector.length; i++) {
            this.vector[i] *= n;
        }
    }

    public void unfold() {
        for (int i = 0; i < this.vector.length; i++) {
            this.vector[i] *= -1;
        }
    }

    public double getLength() {
        double sum = 0;

        for (double v : this.vector) {
            sum += v * v;
        }

        return Math.sqrt(sum);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Vector v = (Vector) o;

        if (vector.length != v.getSize()) {
            return false;
        }

        for (int i = 0; i < vector.length; i++) {
            if (vector[i] != v.vector[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 14;
        int hash = 1;

        for (double v : vector) {
            hash = prime * hash + Double.hashCode(v);
        }

        return hash;
    }

    public static Vector sum(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("Some vector(s) is null");
        }

        double[] array = new double[Math.max(vector1.getSize(), vector2.getSize())];

        int minLength = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < minLength; i++) {
            array[i] = vector1.vector[i] + vector2.vector[i];
        }

        if (vector1.getSize() != vector2.getSize()) {
            if (vector1.getSize() < vector2.getSize()) {
                System.arraycopy(vector2.vector, minLength, array, minLength, array.length - minLength);
            } else {
                System.arraycopy(vector1.vector, minLength, array, minLength, array.length - minLength);
            }
        }

        return new Vector(array);
    }

    public static Vector dif(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("Some vector(s) is null");
        }

        double[] array = new double[Math.max(vector1.getSize(), vector2.getSize())];

        int minLength = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < minLength; i++) {
            array[i] = vector1.vector[i] - vector2.vector[i];
        }

        if (vector1.getSize() < vector2.getSize()) {
            for (int i = minLength; i < array.length; i++) {
                array[i] = -vector2.vector[i];
            }
        } else {
            if (array.length - minLength > 0) {
                System.arraycopy(vector1.vector, minLength, array, minLength, array.length - minLength);
            }
        }

        return new Vector(array);
    }

    public static double scalarMultiply(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("Some vector(s) is null");
        }

        int minLength = Math.min(vector1.getSize(), vector2.getSize());

        double scalarProduct = 0;

        for (int i = 0; i < minLength; i++) {
            scalarProduct += vector1.vector[i] * vector2.vector[i];
        }

        return scalarProduct;
    }
}