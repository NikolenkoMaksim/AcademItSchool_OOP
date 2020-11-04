package ru.oop.nikolenko.matrix;

import ru.oop.nikolenko.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("rowsCount value " + rowsCount + " <= 0");
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("columnsCount value " + columnsCount + " <= 0");
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

        if (array[0].length == 0) {
            throw new IllegalArgumentException("array[0].length is 0");
        }

        rows = new Vector[array.length];

        int maxArrayLength = array[0].length;

        for (int i = 1; i < array.length; i++) {
            if (array[i] == null) {
                throw new IllegalArgumentException("array[" + i + "] is null");
            }

            if (array[i].length == 0) {
                throw new IllegalArgumentException("array[" + i + "].length is 0");
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

        if (vectors[0].getSize() == 0) {
            throw new IllegalArgumentException("vector[0] must have at least one component");
        }

        rows = new Vector[vectors.length];
        int maxVectorSize = vectors[0].getSize();

        for (int i = 1; i < vectors.length; i++) {
            if (vectors[i] == null) {
                throw new IllegalArgumentException("vector[" + i + "] is null");
            }

            if (vectors[i].getSize() == 0) {
                throw new IllegalArgumentException("vector[" + i + "] must have at least one component");
            }

            maxVectorSize = Math.max(vectors[i - 1].getSize(), vectors[i].getSize());
        }

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(maxVectorSize, vectors[i].getComponentsCopy());
        }
    }

    public Vector getRow(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index value " + index + " < 0");
        }

        if (index >= rows.length) {
            throw new IndexOutOfBoundsException("index (" + index + ") >= matrix length (" + rows.length + ")");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector row) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index value " + index + " < 0");
        }

        if (index >= rows.length) {
            throw new IndexOutOfBoundsException("index (" + index + ") >= matrix length (" + rows.length + ")");
        }

        if (row.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("incorrect vector size: " + row.getSize() + " instead of " + getColumnsCount());
        }

        rows[index] = new Vector(row);
    }

    public Vector getColumn(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index value" + index + " < 0");
        }

        if (index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("index (" + index + ") >= matrix.wight (" + getColumnsCount() + ")");
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public void transpose() {
        Matrix transposedMatrix = new Matrix(getColumnsCount(), rows.length);

        for (int i = 0; i < getColumnsCount(); i++) {
            transposedMatrix.setRow(i, getColumn(i));
        }

        rows = transposedMatrix.rows;
    }

    public void multiplyByNumber(double number) {
        for (Vector row : rows) {
            row.multiplyByNumber(number);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new IllegalArgumentException("matrix must be square");
        }

        Matrix matrixCopy = new Matrix(this);

        boolean isDeterminantZero = false;
        final double epsilon = 1e-10;

        for (int i = 0; i < matrixCopy.rows.length - 1; i++) {
            if (Math.abs(matrixCopy.rows[i].getComponent(i)) <= epsilon) {
                isDeterminantZero = true;

                for (int j = i + 1; j < matrixCopy.getColumnsCount(); j++) {
                    if (Math.abs(matrixCopy.rows[j].getComponent(i)) > epsilon) {
                        matrixCopy.rows[i].add(matrixCopy.rows[j]);

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

                Vector subtrahend = new Vector(rows[i]);
                subtrahend.multiplyByNumber(coefficient);

                rows[j].subtract(subtrahend);
            }
        }

        double determinant = 1;

        for (int i = 0; i < matrixCopy.rows.length; i++) {
            determinant *= rows[i].getComponent(i);
        }

        return determinant;
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector is null");
        }

        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("vector length (" + vector.getSize() + ") must be equal number of matrix columns (" + getColumnsCount() + ")");
        }

        Vector multipliedVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            multipliedVector.setComponent(i, Vector.getScalarProduct(rows[i], vector));
        }

        return multipliedVector;
    }

    public void getSum(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix is null");
        }

        if (getRowsCount() != matrix.getRowsCount()) {
            throw new IllegalArgumentException("matrices must have the same length");
        }

        if (getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("matrices must have the same width");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void getDifference(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix is null");
        }

        if (getRowsCount() != matrix.getRowsCount()) {
            throw new IllegalArgumentException("matrices must have the same length");
        }

        if (getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("matrices must have the same width");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("first matrix is null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("second matrix is null");
        }

        if (matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("matrices must have the same length");
        }

        if (matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("matrices must have the same width");
        }

        Matrix sumMatrix = new Matrix(matrix1.rows.length, matrix1.getColumnsCount());

        for (int i = 0; i < matrix1.rows.length; i++) {
            sumMatrix.setRow(i, Vector.getSum(matrix1.rows[i], matrix2.rows[i]));
        }

        return sumMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("first matrix is null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("second matrix is null");
        }


        if (matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("matrices must have the same length");
        }

        if (matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("matrices must have the same width");
        }

        Matrix resultMatrix = new Matrix(matrix1.rows.length, matrix1.getColumnsCount());

        for (int i = 0; i < matrix1.rows.length; i++) {
            resultMatrix.setRow(i, Vector.getDifference(matrix1.rows[i], (matrix2.rows[i])));
        }

        return resultMatrix;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("first matrix is null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("second matrix is null");
        }

        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("matrix1 width (" + matrix1.getColumnsCount() + ") must be equal matrix1 length (" + matrix2.rows.length + ")");
        }

        Matrix multipliedMatrix = new Matrix(matrix1.rows.length, matrix2.getColumnsCount());

        for (int i = 0; i < matrix1.rows.length; i++) {
            Vector vector = new Vector(matrix2.getColumnsCount());

            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                double component = 0;

                for (int k = 0; k < matrix1.getColumnsCount(); k++) {
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
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (Vector row : rows) {
            stringBuilder.append(row).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
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
            if (!rows[i].equals(m.rows[i])) {
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
