package ru.oop.nikolenko.matrix_main;

import ru.oop.nikolenko.matrix.Matrix;
import ru.oop.nikolenko.vector.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println("Проверка конструкторов");
        System.out.println();

        Matrix matrixA = new Matrix(2, 3);
        System.out.println("Матрица А:");
        System.out.println(matrixA);

        Matrix matrixB = new Matrix(matrixA);
        System.out.println("Матрица B:");
        System.out.println(matrixB);

        double[] array1 = {-7, 6, 8};
        Vector vector1 = new Vector(array1);
        double[] array2 = {1, 3};
        Vector vector2 = new Vector(array2);
        Matrix matrixC = new Matrix(new Vector[]{vector2, vector1});
        System.out.println("Матрица C:");
        System.out.println(matrixC);

        double[][] array3 = {{1, 2}, {5, 7, 9}};
        Matrix matrixD = new Matrix(array3);
        System.out.println("Матрица D:");
        System.out.println(matrixD);

        System.out.println();

        System.out.println("Проверка статических методов");
        System.out.println();

        System.out.println("Первая строка матрицы D: " + matrixD.getRow(0));
        matrixD.getColumn(0);
        System.out.println("Первый столбец матрицы D: " + matrixD.getColumn(0));

        matrixB.setRow(0, vector1);
        System.out.println("В матрице B заменили первую строку на " + vector1 + ". Матрица B:");
        System.out.println(matrixB);

        System.out.println();

        System.out.println("Число строк матрицы D: " + matrixD.getRowsCount());
        System.out.println("Число столбцов матрицы D: " + matrixD.getColumnsCount());

        System.out.println();

        Matrix matrixE = new Matrix(matrixD);
        matrixE.transpose();
        System.out.println("Матрица Е - транспонированая матрица D. Матрица E: ");
        System.out.println(matrixE);

        System.out.println();

        matrixE.multiplyByNumber(2);
        System.out.println("Матрицу E умножили на 2:");
        System.out.println(matrixE);

        System.out.println();

        double[][] array4 = {{1, 2}, {5, 7, 9}, {7, 8, 9}};
        Matrix matrixF = new Matrix(array4);
        System.out.println("Матрица F:");
        System.out.println(matrixF);
        System.out.println("Детерминант матрицы F равен " + matrixF.getDeterminant());

        System.out.println();

        double[][] array5 = {{2, 1}, {-3, 0}, {4, -1}};
        Matrix matrixG = new Matrix(array5);
        System.out.println("Матрица G:");
        System.out.println(matrixG);
        System.out.println("Вектор 2:");
        System.out.println(vector2);
        System.out.println("Произведение матрицы G на вектор 2:");
        System.out.println(matrixG.multiplyByVector(vector2));

        System.out.println();

        matrixC.getSum(matrixD);
        System.out.println("К матрице С прибавили матрицу D. Матрица С:");
        System.out.println(matrixC);

        System.out.println();

        matrixC.getDifference(matrixD);
        System.out.println("От матрицы С отняли матрицу D. Матрица С:");
        System.out.println(matrixC);

        System.out.println();

        System.out.println("Проверка статических методов");
        System.out.println();

        System.out.println("Сумма матрицы С и матрицы D:");
        System.out.println(Matrix.getSum(matrixC, matrixD));

        System.out.println();

        System.out.println("Разность матрицы С и матрицы D:");
        System.out.println(Matrix.getDifference(matrixC, matrixD));

        System.out.println();

        System.out.println("Произведение матрицы G на матрицу D:");
        System.out.println(Matrix.multiply(matrixG, matrixD));
    }
}
