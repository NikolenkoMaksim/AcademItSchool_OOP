package ru.oop.nikolenko.vector_main;

import ru.oop.nikolenko.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(4);
        System.out.println("Вектор 1 = " + vector1);

        double[] array1 = {5, 7, 6};
        Vector vector2 = new Vector(5, array1);
        System.out.println("Вектор 2 = " + vector2);

        Vector vector3 = new Vector(vector2);
        System.out.println("Вектор 3 = " + vector3);

        Vector vector4 = new Vector(array1);
        System.out.println("Вектор 4 = " + vector4);

        double[] array2 = {8, 2, 3};
        Vector vector5 = new Vector(array2);
        System.out.println("Вектор 5 = " + vector5);
        System.out.println();

        boolean isEquals = vector4.equals(vector2);
        System.out.println("Выражение \"Ветор 4 равен вектору 2\" является " + isEquals);

        isEquals = vector2.equals(vector3);
        System.out.println("Выражение \"Ветор 2 равен вектору 3\" является " + isEquals);
        System.out.println();

        vector3.setComponent(4, 5);
        System.out.println("В векторе 3 поменяли четвертую компоненту на 5");
        System.out.println("Вектор 3 = " + vector3);
        System.out.println();

        System.out.println("Четвертая компонента вектора 3 = " + vector3.getComponent(4));
        System.out.println();

        Vector vector6 = Vector.getSum(vector4, vector5);
        System.out.println("Вектор 6 = вектор 4 + вектор 5 = " + vector6);

        vector6 = Vector.getSum(vector5, vector3);
        System.out.println("Вектор 6 = вектор 5 + вектор 3 = " + vector6);

        vector6 = Vector.getSum(vector3, vector5);
        System.out.println("Вектор 6 = вектор 3 + вектор 5 = " + vector6);
        System.out.println();

        vector6 = Vector.getDifference(vector4, vector5);
        System.out.println("Вектор 6 = вектор 4 - вектор 5 = " + vector6);

        vector6 = Vector.getDifference(vector5, vector3);
        System.out.println("Вектор 6 = вектор 5 - вектор 3 = " + vector6);

        vector6 = Vector.getDifference(vector3, vector5);
        System.out.println("Вектор 6 = вектор 3 - вектор 5 = " + vector6);
        System.out.println();

        double scalarProduct = Vector.getScalarProduct(vector4, vector5);
        System.out.println("Скалярное произведение вектора 4 на вектор 5 равно " + scalarProduct);

        scalarProduct = Vector.getScalarProduct(vector4, vector3);
        System.out.println("Скалярное произведение вектора 4 на вектор 3 равно " + scalarProduct);

        scalarProduct = Vector.getScalarProduct(vector3, vector4);
        System.out.println("Скалярное произведение вектора 3 на вектор 4 равно " + scalarProduct);
        System.out.println();

        vector4.invert();
        System.out.println("Вектор 4 развернули");
        System.out.println("Вектор 4 = " + vector4);
        System.out.println();

        System.out.println("Размер вектора 4 равен " + vector4.getSize());
        System.out.println();

        vector4.multiplyByNumber(2);
        System.out.println("Вектор 4 умножили на 2");
        System.out.println("Вектор 4 = " + vector4);
        System.out.println();

        System.out.println("Длина вектора 5 равна " + vector5.getLength());
        System.out.println();

        vector4.add(vector5);
        System.out.println("К вектору 4 прибавили вектор 5");
        System.out.println("Вектор 4 = " + vector4);
        System.out.println();

        vector4.subtract(vector5);
        System.out.println("От вектора 4 отняли вектор 5");
        System.out.println("Вектор 4 = " + vector4);
        System.out.println();

        vector5.add(vector3);
        System.out.println("К вектору 5 прибавили вектор 3");
        System.out.println("Вектор 5 = " + vector5);
        System.out.println();

        vector5.subtract(vector3);
        System.out.println("От вектора 5 отняли вектор 3");
        System.out.println("Вектор 5 = " + vector5);
        System.out.println();

        vector3.add(vector4);
        System.out.println("К вектору 3 прибавили вектор 4");
        System.out.println("Вектор 3 = " + vector3);
        System.out.println();

        vector3.subtract(vector4);
        System.out.println("От вектора 3 отняли вектор 4");
        System.out.println("Вектор 3 = " + vector3);
        System.out.println();

        vector1.subtract(vector3);
        System.out.println("От вектора 1 отняли вектор 3");
        System.out.println("Вектор 1 = " + vector1);
        System.out.println();
    }
}
