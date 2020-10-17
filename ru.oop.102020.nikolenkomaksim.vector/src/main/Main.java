package main;

import vector.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println("Проверка конструкторов");
        System.out.println();

        Vector vector1 = new Vector(4);
        System.out.println("Вектор1 = " + vector1);

        double[] array1 = {5, 7, 6};
        Vector vector2 = new Vector(5, array1);
        System.out.println("Вектор2 = " + vector2);

        Vector vector3 = new Vector(vector2);
        System.out.println("Вектор3 = " + vector3);

        Vector vector4 = new Vector(array1);
        System.out.println("Вектор4 = " + vector4);
        System.out.println();

        System.out.println("Проверка нестатических методов");
        System.out.println();

        boolean isEquals = vector4.equals(vector2);
        System.out.println("Выражение \"Ветор4 равен вектору2\" является " + isEquals);

        isEquals = vector2.equals(vector3);
        System.out.println("Выражение \"Ветор2 равен вектору3\" является " + isEquals);
        System.out.println();

        vector3.setComponent(4, 5);
        System.out.println("В векторе3 поменяли 4 компоненту на 5");
        System.out.println("Вектор3 = " + vector3);
        System.out.println();

        System.out.println("Четвертая компонетнта вектора3 = " + vector3.getComponent(4));
        System.out.println();

        double[] array2 = {8, 2, 3};
        Vector vector5 = new Vector(array2);
        System.out.println("Вектор5 = " + vector5);
        System.out.println();

        Vector vector6 = vector4.unfold();
        System.out.println("Вектор6 - развернутый вектор 4");
        System.out.println("Вектор6 = " + vector6);
        System.out.println();

        System.out.println("Размер вектора4 равен " + vector4.getSize());
        System.out.println();

        System.out.println("Вектор4 умножили на 2: " + vector4.multiplyByNumber(2));
        System.out.println();

        System.out.println("Длина вектора5 равна " + vector5.getLength());
        System.out.println();

        Vector vector7 = vector4.sum(vector5);
        System.out.println("Вектор7 = вектор4 + вектор5 = " + vector7);

        vector7 = vector5.sum(vector3);
        System.out.println("Вектор7 = вектор5 + вектор3 = " + vector7);

        vector7 = vector3.sum(vector5);
        System.out.println("Вектор7 = вектор3 + вектор5 = " + vector7);
        System.out.println();

        vector7 = vector4.dif(vector5);
        System.out.println("Вектор7 = вектор4 - вектор5 = " + vector7);

        vector7 = vector5.dif(vector3);
        System.out.println("Вектор7 = вектор5 - вектор3 = " + vector7);

        vector7 = vector3.dif(vector5);
        System.out.println("Вектор7 = вектор3 - вектор5 = " + vector7);
        System.out.println();

        System.out.println("Проверка статических методов");
        System.out.println();

        vector7 = Vector.sum(vector4, vector5);
        System.out.println("Вектор7 = вектор4 + вектор5 = " + vector7);

        vector7 = Vector.sum(vector5, vector3);
        System.out.println("Вектор7 = вектор5 + вектор3 = " + vector7);

        vector7 = Vector.sum(vector3, vector5);
        System.out.println("Вектор7 = вектор3 + вектор5 = " + vector7);
        System.out.println();

        vector7 = Vector.dif(vector4, vector5);
        System.out.println("Вектор7 = вектор4 - вектор5 = " + vector7);

        vector7 = Vector.dif(vector5, vector3);
        System.out.println("Вектор7 = вектор5 - вектор3 = " + vector7);

        vector7 = Vector.dif(vector3, vector5);
        System.out.println("Вектор7 = вектор3 - вектор5 = " + vector7);
        System.out.println();

        double scalarProduct = Vector.scalarMultiply(vector4, vector5);
        System.out.println("Скалярное произведение вектора4 на вектор5 равно " + scalarProduct);

        scalarProduct = Vector.scalarMultiply(vector4, vector3);
        System.out.println("Скалярное произведение вектора4 на вектор3 равно " + scalarProduct);

        scalarProduct = Vector.scalarMultiply(vector3, vector4);
        System.out.println("Скалярное произведение вектора3 на вектор4 равно " + scalarProduct);
    }
}
