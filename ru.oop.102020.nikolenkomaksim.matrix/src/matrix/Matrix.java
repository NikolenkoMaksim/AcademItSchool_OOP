package matrix;

import vector.Vector;

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

        for(int i = 0; i < matrix.length; i++) {
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

    public Matrix transpose(){
        Matrix transposedMatrix = new Matrix(matrix[0].getSize(), matrix.length);

        for(int i = 0; i < matrix[0].getSize(); i++) {
            transposedMatrix.setLine(i, getColumn(i));
        }

        return  transposedMatrix;
    }

    @Override
    public String toString() {
        StringBuilder matrix = new StringBuilder();

        matrix.append("{");

        for (int i = 0; i < this.matrix.length; i++) {
            matrix.append(this.matrix[i]);

            if(i < this.matrix.length - 1)
                matrix.append(", ");
        }

        matrix.append("}");

        return matrix.toString();
    }
}
