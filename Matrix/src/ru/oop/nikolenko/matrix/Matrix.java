package ru.oop.nikolenko.matrix;

import ru.oop.nikolenko.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("rowsCount <= 0");
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("columnsCount <= 0");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix is null");
        }

        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new IllegalArgumentException("array is null");
        }

        if (array[0] == null) {
            throw new IllegalArgumentException("array[0] is null");
        }

        rows = new Vector[array.length];

        int maxArrayLength = array[0].length;

        for (int i = 1; i < array.length; i++) {
            if (array[i] == null) {
                throw new IllegalArgumentException("array[" + i + "] is null");
            }

            maxArrayLength = Math.max(array[i - 1].length, array[i].length);
        }

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxArrayLength, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new IllegalArgumentException("Vector[] vectors is null");
        }

        if (vectors[0] == null) {
            throw new IllegalArgumentException("vector[0] is null");
        }

        rows = new Vector[vectors.length];
        int maxVectorSize = vectors[0].getSize();

        for (int i = 1; i < vectors.length; i++) {
            if (vectors[i] == null) {
                throw new IllegalArgumentException("vector[" + i + "] is null");
            }

            maxVectorSize = Math.max(vectors[i - 1].getSize(), vectors[i].getSize());
        }

        for (int i = 0; i < vectors.length; i++) {
            this.rows[i] = new Vector(maxVectorSize, vectors[i].getComponentsCopy());
        }
    }

    public Vector getRow(int rowIndex) {
        if (rowIndex < 0) {
            throw new ArrayIndexOutOfBoundsException("rowIndex < 0");
        }

        if (rowIndex >= rows.length) {
            throw new ArrayIndexOutOfBoundsException("rowIndex >= matrix length");
        }

        return new Vector(rows[rowIndex]);
    }

    public void setRow(int rowIndex, Vector row) {
        if (rowIndex < 0) {
            throw new ArrayIndexOutOfBoundsException("rowIndex < 0");
        }

        if (rowIndex >= rows.length) {
            throw new ArrayIndexOutOfBoundsException("rowIndex >= matrix length");
        }

        if (row.getSize() != rows[0].getSize()) {
            throw new IllegalArgumentException("incorrect vector size");
        }

        rows[rowIndex] = new Vector(row);
    }

    public Vector getColumn(int columnIndex) {
        if (columnIndex < 0) {
            throw new ArrayIndexOutOfBoundsException("columnIndex < 0");
        }

        if (columnIndex >= rows[0].getSize()) {
            throw new ArrayIndexOutOfBoundsException("columnIndex >= matrix.wight");
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            column.setComponent(i, rows[i].getComponent(columnIndex));
        }

        return column;
    }

    public int getLength() {
        return rows.length;
    }

    public int getWight() {
        return rows[0].getSize();
    }

    public int[] getSizes() {
        return new int[]{rows.length, rows[0].getSize()};
    }

    public Matrix transpose() {
        Matrix transposedMatrix = new Matrix(rows[0].getSize(), rows.length);

        for (int i = 0; i < rows[0].getSize(); i++) {
            transposedMatrix.setRow(i, getColumn(i));
        }

        return transposedMatrix;
    }

    public Matrix multiplyByNumber(double number) {
        Matrix multipliedMatrix = new Matrix(rows.length, rows[0].getSize());

        for (int i = 0; i < rows.length; i++) {
            multipliedMatrix.rows[i] = rows[i].multiplyByNumber(number);
        }

        return multipliedMatrix;
    }

    public double getDeterminant() {
        if (rows.length != rows[0].getSize()) {
            throw new IllegalArgumentException("matrix must be square");
        }

        Matrix matrixCopy = new Matrix(this);

        boolean isDeterminantZero = false;
        final double epsilon = 1e-10;

        for (int i = 0; i < matrixCopy.rows.length - 1; i++) {
            if (Math.abs(matrixCopy.rows[i].getComponent(i)) <= epsilon) {
                isDeterminantZero = true;

                for (int j = i + 1; j < matrixCopy.rows[0].getSize(); j++) {
                    if (Math.abs(matrixCopy.rows[j].getComponent(i)) > epsilon) {
                        matrixCopy.setRow(i, matrixCopy.rows[i].add(matrixCopy.rows[j]));

                        isDeterminantZero = false;
                        break;
                    }
                }
            }

            if (isDeterminantZero) {
                return 0;
            }

            for (int j = i + 1; j < matrixCopy.rows.length; j++) {
                double coefficient = rows[j].getComponent(i) / rows[i].getComponent(i);

                matrixCopy.setRow(j, matrixCopy.rows[j].subtract(matrixCopy.rows[i].multiplyByNumber(coefficient)));
            }
        }

        double determinant = 1;

        for (int i = 0; i < matrixCopy.rows.length; i++) {
            determinant *= rows[i].getComponent(i);
        }

        return determinant;
    }

    public Matrix multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector is null");
        }

        if (rows[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException("vector length must be equal matrix width");
        }

        Matrix multipliedMatrix = new Matrix(rows.length, 1);

        for (int i = 0; i < rows.length; i++) {
            double component = 0;

            for (int j = 0; j < rows[0].getSize(); j++) {
                component += this.rows[i].getComponent(j) * vector.getComponent(j);
            }

            multipliedMatrix.rows[i].setComponent(0, component);
        }

        return multipliedMatrix;
    }

    public Matrix getSum(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix is null");
        }

        if (!Arrays.equals(this.getSizes(), matrix.getSizes())) {
            throw new IllegalArgumentException("matrices must have the same size");
        }

        Matrix sumMatrix = new Matrix(this.rows.length, matrix.rows[0].getSize());

        for (int i = 0; i < this.rows.length; i++) {
            sumMatrix.setRow(i, this.rows[i].add(matrix.rows[i]));
        }

        return sumMatrix;
    }

    public Matrix getDifference(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix is null");
        }

        if (!Arrays.equals(this.getSizes(), matrix.getSizes())) {
            throw new IllegalArgumentException("matrices must have the same size");
        }

        Matrix resultMatrix = new Matrix(this.rows.length, matrix.rows[0].getSize());

        for (int i = 0; i < this.rows.length; i++) {
            resultMatrix.setRow(i, this.rows[i].subtract(matrix.rows[i]));
        }

        return resultMatrix;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("first matrix is null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("second matrix is null");
        }

        return matrix1.getSum(matrix2);
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("first matrix is null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("second matrix is null");
        }

        return matrix1.getDifference(matrix2);
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("first matrix is null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("second matrix is null");
        }

        if (matrix1.rows[0].getSize() != matrix2.rows.length) {
            throw new IllegalArgumentException("matrix1 width must be equal matrix1 length");
        }

        Matrix multipliedMatrix = new Matrix(matrix1.rows.length, matrix2.rows[0].getSize());

        for (int i = 0; i < matrix1.rows.length; i++) {
            Vector vector = new Vector(matrix2.rows[0].getSize());

            for (int j = 0; j < matrix2.rows[0].getSize(); j++) {
                double component = 0;

                for (int k = 0; k < matrix1.rows[0].getSize(); k++) {
                    component += matrix1.rows[i].getComponent(k) * matrix2.rows[k].getComponent(j);
                }

                vector.setComponent(j, component);
            }

            multipliedMatrix.setRow(i, vector);
        }

        return multipliedMatrix;
    }

    @Override
    public String toString() {
        StringBuilder matrix = new StringBuilder();

        matrix.append("{");

        for (int i = 0; i < this.rows.length; i++) {
            matrix.append(this.rows[i]);

            if (i < this.rows.length - 1)
                matrix.append(", ");
        }

        matrix.append("}");

        return matrix.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Matrix m = (Matrix) o;

        if (rows.length != m.rows.length) {
            return false;
        }

        for (int i = 0; i < rows.length; i++) {
            if (rows[i] != m.rows[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 61;
        int hash = 1;

        for (Vector row : rows) {
            hash = prime * hash + row.hashCode();
        }

        return hash;
    }
}
