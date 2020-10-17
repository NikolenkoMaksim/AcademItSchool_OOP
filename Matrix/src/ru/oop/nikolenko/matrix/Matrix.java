package ru.oop.nikolenko.matrix;

import ru.oop.nikolenko.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrix;

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("n or m <= 0");
        }

        matrix = new Vector[n];

        for (int i = 0; i < n; i++) {
            matrix[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix is null");
        }

        this.matrix = matrix.matrix;
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new IllegalArgumentException("array is null");
        }

        if (array[0] == null) {
            throw new IllegalArgumentException("array[0] is null");
        }

        matrix = new Vector[array.length];

        int maxArrayWidth = array[0].length;

        for (int i = 1; i < array.length; i++) {
            if (array[i] == null) {
                throw new IllegalArgumentException("array[" + i + "] is null");
            }

            maxArrayWidth = Math.max(array[i - 1].length, array[i].length);
        }

        for (int i = 0; i < array.length; i++) {
            matrix[i] = new Vector(maxArrayWidth, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new IllegalArgumentException("vectors is null");
        }

        if (vectors[0] == null) {
            throw new IllegalArgumentException("vectors[0] is null");
        }

        matrix = new Vector[vectors.length];

        int maxArrayWidth = vectors[0].getSize();

        for (int i = 1; i < vectors.length; i++) {
            if (vectors[i] == null) {
                throw new IllegalArgumentException("vectors[" + i + "] is null");
            }

            maxArrayWidth = Math.max(vectors[i - 1].getSize(), vectors[i].getSize());
        }

        for (int i = 0; i < vectors.length; i++) {
            matrix[i] = new Vector(maxArrayWidth, vectors[i].getVector());
        }
    }

    public Vector[] getMatrix() {
        return matrix;
    }

    public void setMatrix(Vector[] matrix) {
        this.matrix = matrix;
    }

    public Vector getLine(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }

        if (n >= matrix.length) {
            throw new IllegalArgumentException("n >= matrix length");
        }

        return matrix[n];
    }

    public void setLine(int n, Vector line) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }

        if (n >= matrix.length) {
            throw new IllegalArgumentException("n >= matrix length");
        }

        if (line.getSize() != matrix[0].getSize()) {
            throw new IllegalArgumentException("incorrect vector size");
        }

        matrix[n] = line;
    }

    public Vector getColumn(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }

        if (n >= matrix[0].getSize()) {
            throw new IllegalArgumentException("n >= matrix.wight");
        }

        Vector column = new Vector(matrix.length);

        for (int i = 0; i < matrix.length; i++) {
            column.setComponent(i, matrix[i].getComponent(n));
        }

        return column;
    }

    public int getLength() {
        return matrix.length;
    }

    public int getWight() {
        return matrix[0].getSize();
    }

    public int[] getSize() {
        return new int[]{matrix.length, matrix[0].getSize()};
    }

    public Matrix transpose() {
        Matrix transposedMatrix = new Matrix(matrix[0].getSize(), matrix.length);

        for (int i = 0; i < matrix[0].getSize(); i++) {
            transposedMatrix.setLine(i, getColumn(i));
        }

        return transposedMatrix;
    }

    public Matrix multiplyByNumber(double n) {
        Matrix multipliedMatrix = new Matrix(matrix.length, matrix[0].getSize());

        for (int i = 0; i < matrix.length; i++) {
            multipliedMatrix.matrix[i] = matrix[i].multiplyByNumber(n);
        }

        return multipliedMatrix;
    }

    public double getDeterminant() {
        if (matrix.length != matrix[0].getSize()) {
            throw new IllegalArgumentException("matrix must be square");
        }

        Matrix matrixCopy = new Matrix(this);

        boolean isDeterminantZero = false;
        final double epsilon = 1e-10;

        for (int i = 0; i < matrixCopy.matrix.length - 1; i++) {
            if (Math.abs(matrixCopy.matrix[i].getComponent(i)) <= epsilon) {
                isDeterminantZero = true;

                for (int j = i + 1; j < matrixCopy.matrix[0].getSize(); j++) {
                    if (Math.abs(matrixCopy.matrix[j].getComponent(i)) > epsilon) {
                        matrixCopy.setLine(i, matrixCopy.matrix[i].sum(matrixCopy.matrix[j]));

                        isDeterminantZero = false;
                        break;
                    }
                }
            }

            if (isDeterminantZero) {
                return 0;
            }

            for (int j = i + 1; j < matrixCopy.matrix.length; j++) {
                double coefficient = matrix[j].getComponent(i) / matrix[i].getComponent(i);

                matrixCopy.setLine(j, matrixCopy.matrix[j].dif(matrixCopy.matrix[i].multiplyByNumber(coefficient)));
            }
        }

        double determinant = 1;

        for (int i = 0; i < matrixCopy.matrix.length; i++) {
            determinant *= matrix[i].getComponent(i);
        }

        return determinant;
    }

    public Matrix multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector is null");
        }

        if (matrix[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException("vector length must be equal matrix width");
        }

        Matrix multipliedMatrix = new Matrix(matrix.length, 1);

        for (int i = 0; i < matrix.length; i++) {
            double component = 0;

            for (int j = 0; j < matrix[0].getSize(); j++) {
                component += this.matrix[i].getComponent(j) * vector.getComponent(j);
            }

            multipliedMatrix.matrix[i].setComponent(0, component);
        }

        return multipliedMatrix;
    }

    public Matrix sum(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix is null");
        }

        if (!Arrays.equals(this.getSize(), matrix.getSize())) {
            throw new IllegalArgumentException("matrices must have the same size");
        }

        Matrix sumMatrix = new Matrix(this.matrix.length, matrix.matrix[0].getSize());

        for (int i = 0; i < this.matrix.length; i++) {
            sumMatrix.setLine(i, this.matrix[i].sum(matrix.matrix[i]));
        }

        return sumMatrix;
    }

    public Matrix dif(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix is null");
        }

        if (!Arrays.equals(this.getSize(), matrix.getSize())) {
            throw new IllegalArgumentException("matrices must have the same size");
        }

        Matrix sumMatrix = new Matrix(this.matrix.length, matrix.matrix[0].getSize());

        for (int i = 0; i < this.matrix.length; i++) {
            sumMatrix.setLine(i, this.matrix[i].dif(matrix.matrix[i]));
        }

        return sumMatrix;
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("first matrix is null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("second matrix is null");
        }

        if (!Arrays.equals(matrix1.getSize(), matrix2.getSize())) {
            throw new IllegalArgumentException("matrices must have the same size");
        }

        Matrix sumMatrix = new Matrix(matrix1.matrix.length, matrix1.matrix[0].getSize());

        for (int i = 0; i < matrix1.matrix.length; i++) {
            sumMatrix.setLine(i, matrix1.matrix[i].sum(matrix2.matrix[i]));
        }

        return sumMatrix;
    }

    public static Matrix dif(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("first matrix is null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("second matrix is null");
        }

        if (!Arrays.equals(matrix1.getSize(), matrix2.getSize())) {
            throw new IllegalArgumentException("matrices must have the same size");
        }

        Matrix sumMatrix = new Matrix(matrix1.matrix.length, matrix1.matrix[0].getSize());

        for (int i = 0; i < matrix1.matrix.length; i++) {
            sumMatrix.setLine(i, matrix1.matrix[i].dif(matrix2.matrix[i]));
        }

        return sumMatrix;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("first matrix is null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("second matrix is null");
        }

        if (matrix1.matrix[0].getSize() != matrix2.matrix.length) {
            throw new IllegalArgumentException("matrix1 width must be equal matrix1 length");
        }

        Matrix multipliedMatrix = new Matrix(matrix1.matrix.length, matrix2.matrix[0].getSize());

        for (int i = 0; i < matrix1.matrix.length; i++) {
            Vector vector = new Vector(matrix2.matrix[0].getSize());

            for (int j = 0; j < matrix2.matrix[0].getSize(); j++) {
                double component = 0;

                for (int k = 0; k < matrix1.matrix[0].getSize(); k++) {
                    component += matrix1.matrix[i].getComponent(k) * matrix2.matrix[k].getComponent(j);
                }

                vector.setComponent(j, component);
            }

            multipliedMatrix.setLine(i, vector);
        }

        return multipliedMatrix;
    }

    @Override
    public String toString() {
        StringBuilder matrix = new StringBuilder();

        matrix.append("{");

        for (int i = 0; i < this.matrix.length; i++) {
            matrix.append(this.matrix[i]);

            if (i < this.matrix.length - 1)
                matrix.append(", ");
        }

        matrix.append("}");

        return matrix.toString();
    }
}
