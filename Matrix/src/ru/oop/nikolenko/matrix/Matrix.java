package ru.oop.nikolenko.matrix;

import ru.oop.nikolenko.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("rowsCount (" + rowsCount + ") <= 0");
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("columnsCount (" + columnsCount + ") <= 0");
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

        if (array.length == 0) {
            throw new IllegalArgumentException("array.length is 0");
        }

        rows = new Vector[array.length];

        int maxArrayLength = 0;
        boolean isAllArraysNull = true;

        for (double[] e : array) {
            if (e != null) {
                maxArrayLength = Math.max(maxArrayLength, e.length);
                isAllArraysNull = false;
            }
        }

        if (isAllArraysNull) {
            throw new IllegalArgumentException("all arrays of double[][] array are null");
        }

        if (maxArrayLength == 0) {
            throw new IllegalArgumentException("all arrays of double[][] array have 0 length");
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                rows[i] = new Vector(maxArrayLength, array[i]);
            } else {
                rows[i] = new Vector(maxArrayLength);
            }
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new IllegalArgumentException("Vector[] vectors is null");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("vectors.length is 0");
        }

        rows = new Vector[vectors.length];

        int maxVectorSize = 0;
        boolean isAllVectorsNull = true;

        for (Vector e : vectors) {
            if (e != null) {
                maxVectorSize = Math.max(maxVectorSize, e.getSize());
                isAllVectorsNull = false;
            }
        }

        if (isAllVectorsNull) {
            throw new IllegalArgumentException("all vectors are null");
        }

        for (int i = 0; i < vectors.length; i++) {
            if (vectors[i] != null) {
                rows[i] = new Vector(maxVectorSize, vectors[i].getComponentsCopy());
            } else {
                rows[i] = new Vector(maxVectorSize);
            }
        }
    }

    public Vector getRow(int index) {
        checkRowIndex(index);

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector row) {
        checkRowIndex(index);

        if (row == null) {
            throw new IllegalArgumentException("row is null");
        }

        if (row.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("incorrect vector size (" + row.getSize() + " instead of " + getColumnsCount() + ")");
        }

        rows[index] = new Vector(row);
    }

    private void checkRowIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index (" + index + ") < 0");
        }

        if (index >= rows.length) {
            throw new IndexOutOfBoundsException("index (" + index + ") >= rows count (" + rows.length + ")");
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public Vector getColumn(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index (" + index + ") < 0");
        }

        if (index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("index (" + index + ") >= columns count (" + getColumnsCount() + ")");
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public void transpose() {
        Vector[] transposedRows = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); i++) {
            transposedRows[i] = getColumn(i);
        }

        rows = transposedRows;
    }

    public void multiplyByNumber(double number) {
        for (Vector row : rows) {
            row.multiplyByNumber(number);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new UnsupportedOperationException("matrix must be square. Now it's size " + getRowsCount() + "x" + getColumnsCount());
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
                double coefficient = matrixCopy.rows[j].getComponent(i) / matrixCopy.rows[i].getComponent(i);

                Vector subtrahend = new Vector(matrixCopy.rows[i]);
                subtrahend.multiplyByNumber(coefficient);

                matrixCopy.rows[j].subtract(subtrahend);
            }
        }

        double determinant = 1;

        for (int i = 0; i < matrixCopy.rows.length; i++) {
            determinant *= matrixCopy.rows[i].getComponent(i);
        }

        return determinant;
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector is null");
        }

        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("vector size (" + vector.getSize() +
                    ") must be equal matrix columns count (" + getColumnsCount() + ")");
        }

        Vector resultVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            resultVector.setComponent(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultVector;
    }

    public void add(Matrix matrix) {
        checkMatrix(matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatrix(matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    private void checkMatrix(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix is null");
        }

        if (rows.length != matrix.rows.length) {
            throw new IllegalArgumentException("argument matrix has a different rows count (" + matrix.rows.length +
                    ") than the object matrix (" + rows.length + ")");
        }

        if (getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("argument matrix has a different columns count (" + matrix.getColumnsCount() +
                    ") than the object matrix (" + getColumnsCount() + ")");
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatrices(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatrices(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    private static void checkMatrices(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("matrix 1 is null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("matrix 2 is null");
        }

        if (matrix1.rows.length != matrix2.rows.length) {
            throw new IllegalArgumentException("matrix 1 has different rows count (" + matrix1.rows.length +
                    ") then matrix 2 (" + matrix2.rows.length + ")");
        }

        if (matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("matrix 1 has different columns count (" + matrix1.getColumnsCount() +
                    ") then matrix 2 (" + matrix2.getColumnsCount() + ")");
        }
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("matrix 1 is null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("matrix 2 is null");
        }

        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("matrix 1 columns count (" + matrix1.getColumnsCount() +
                    ") must be equal matrix 2 rows count (" + matrix2.rows.length + ")");
        }

        Matrix resultMatrix = new Matrix(matrix1.rows.length, matrix2.getColumnsCount());

        for (int i = 0; i < matrix1.rows.length; i++) {
            Vector vector = new Vector(matrix2.getColumnsCount());

            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                double component = 0;

                for (int k = 0; k < matrix1.getColumnsCount(); k++) {
                    component += matrix1.rows[i].getComponent(k) * matrix2.rows[k].getComponent(j);
                }

                vector.setComponent(j, component);
            }

            resultMatrix.rows[i] = vector;
        }

        return resultMatrix;
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

        return Arrays.equals(rows, m.rows);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(rows);
    }
}
