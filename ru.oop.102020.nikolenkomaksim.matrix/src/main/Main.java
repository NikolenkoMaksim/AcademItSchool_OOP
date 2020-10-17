package main;

import matrix.Matrix;
import vector.Vector;

import java.util.Arrays;

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

        double[] array1 = {5, 7, 6};
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

        System.out.println("Первая строка матрицы D: " + matrixD.getLine(0));
        matrixD.getColumn(0);
        System.out.println("Первый столбец матрицы D: " + matrixD.getColumn(0));

        matrixB.setLine(0, vector1);
        System.out.println("В матрице B заменили первую строку на {5, 6, 7}. Матрица B:");
        System.out.println(matrixB);

        System.out.println();

        System.out.println("Длина матрицы D: " + matrixD.getLength());
        System.out.println("Ширина матрицы D: " + matrixD.getWight());
        System.out.println("Размеры матрицы D: " + Arrays.toString(matrixD.getSize()));

        System.out.println();

        Matrix matrixE = matrixD.transpose();
        System.out.println("Матрица Е - транспонированая матрица D. Матрица E: ");
        System.out.println(matrixE);






        Matrix matrixN = null;
    }
}
