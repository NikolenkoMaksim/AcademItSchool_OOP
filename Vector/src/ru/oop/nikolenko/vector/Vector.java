package ru.oop.nikolenko.vector;

public class Vector {
    private double[] vector;

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
            throw new IllegalArgumentException("array is null");
        }

        this.vector = vector;
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector is null");
        }

        this.vector = new double[vector.getSize()];
        System.arraycopy(vector.vector, 0, this.vector, 0, vector.getSize());
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0");
        }

        if (array == null) {
            throw new IllegalArgumentException("array is null");
        }

        vector = new double[n];

        System.arraycopy(array, 0, this.vector, 0, array.length);

        for (int i = array.length; i < n; i++) {
            vector[i] = 0;
        }
    }

    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        if (vector == null) {
            throw new IllegalArgumentException("array is null");
        }

        this.vector = vector;
    }

    public double getComponent(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }

        if (n >= vector.length) {
            throw new IllegalArgumentException("n >= vector.length");
        }

        return vector[n];
    }

    public void setComponent(int n, double value) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }

        if (n >= vector.length) {
            throw new IllegalArgumentException("n >= vector.length");
        }

        vector[n] = value;
    }

    public int getSize() {
        return vector.length;
    }

    @Override
    public String toString() {
        StringBuilder vector = new StringBuilder();

        vector.append("{");

        for (int i = 0; i < this.vector.length; i++) {
            vector.append(this.vector[i]);

            if (i < this.vector.length - 1) {
                vector.append(", ");
            }
        }

        vector.append("}");

        return vector.toString();
    }

    public Vector sum(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector is null");
        }

        Vector sumVector = new Vector(Math.max(this.vector.length, vector.getSize()));

        int minLength = Math.min(this.vector.length, vector.getSize());

        for (int i = 0; i < minLength; i++) {
            sumVector.vector[i] = this.vector[i] + vector.vector[i];
        }

        if (this.vector.length != vector.getSize()) {
            if (this.vector.length < vector.getSize()) {
                System.arraycopy(vector.vector, minLength, sumVector.vector, minLength, sumVector.vector.length - minLength);
            } else {
                System.arraycopy(this.vector, minLength, sumVector.vector, minLength, sumVector.vector.length - minLength);
            }
        }

        return sumVector;
    }

    public Vector dif(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector is null");
        }

        Vector difVector = new Vector(Math.max(this.vector.length, vector.getSize()));

        int minLength = Math.min(this.vector.length, vector.getSize());

        for (int i = 0; i < minLength; i++) {
            difVector.vector[i] = this.vector[i] - vector.vector[i];
        }

        if (this.vector.length != vector.getSize()) {
            if (this.vector.length < vector.getSize()) {
                for (int i = minLength; i < difVector.vector.length; i++) {
                    difVector.vector[i] = -vector.vector[i];
                }
            } else {
                System.arraycopy(this.vector, minLength, difVector.vector, minLength, difVector.vector.length - minLength);
            }
        }

        return difVector;
    }

    public Vector multiplyByNumber(double n) {
        Vector multipliedVector = new Vector(vector.length);

        for (int i = 0; i < this.vector.length; i++) {
            multipliedVector.vector[i] = vector[i] * n;
        }

        return multipliedVector;
    }

    public Vector unfold() {
        Vector unfoldedVector = new Vector(vector.length);

        for (int i = 0; i < this.vector.length; i++) {
            unfoldedVector.vector[i] = this.vector[i] * -1;
        }

        return unfoldedVector;
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

        if (o == null || o.getClass() != getClass()) {
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
        final int prime = 139;
        int hash = 1;

        for (double v : vector) {
            hash = prime * hash + Double.hashCode(v);
        }

        return hash;
    }

    public static Vector sum(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("first vector is null");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("second vector is null");
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
        if (vector1 == null) {
            throw new IllegalArgumentException("first vector is null");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("second vector is null");
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
        if (vector1 == null) {
            throw new IllegalArgumentException("first vector is null");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("second vector is null");
        }

        int minLength = Math.min(vector1.getSize(), vector2.getSize());

        double scalarProduct = 0;

        for (int i = 0; i < minLength; i++) {
            scalarProduct += vector1.vector[i] * vector2.vector[i];
        }

        return scalarProduct;
    }
}