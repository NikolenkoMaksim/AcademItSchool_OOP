package Main;

import Vector.Vector;

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
        System.out.println();

        System.out.println("Проверка нестатических методов");
        System.out.println();

        double[] array2 = {8, 2, 3};
        Vector vector5 = new Vector(array2);
        System.out.println("Вектор5 = " + vector5);
        System.out.println();

        vector4.sum(vector5);
        System.out.println("К вектору4 прибавили вектор5");
        System.out.println("Вектор4 = " + vector4);
        System.out.println();

        // vector4.sum(vector3);

        vector4.dif(vector5);
        System.out.println("Из вектора4 отняли вектор5");
        System.out.println("Вектор4 = " + vector4);
        System.out.println();

        System.out.println("Размер вектора4 равен " + vector4.getSize());
        System.out.println();

        vector4.multiplyByNumber(2);
        System.out.println("Вектор4 умножили на 2");
        System.out.println("Вектор4 = " + vector4);
        System.out.println();

        vector4.unfold();
        System.out.println("Вектор4 развернули");
        System.out.println("Вектор4 = " + vector4);
        System.out.println();

        System.out.println("Длина вектора5 равна " + vector5.getLength());
        System.out.println();

        boolean isEquals = vector4.equals(vector2);
        System.out.println("Выражение \"Ветор4 равен вектору2\" является " + isEquals);

        isEquals = vector2.equals(vector3);
        System.out.println("Выражение \"Ветор1 равен вектору2\" является " + isEquals);
        System.out.println();

        vector3.setVectorComponent(5, 4);
        System.out.println("В векторе3 поменяли 4 компоненту на 5");
        System.out.println("Вектор3 = " + vector3);
        System.out.println();
        System.out.println();

        System.out.println("Проверка статических методов");
        System.out.println();

        Vector vector6 = Vector.sum(vector4, vector5);
        System.out.println("Вектор6 = вектор4 + вектор5 = " + vector6);

        vector6 = Vector.sum(vector4, vector3);
        System.out.println("Вектор6 = вектор4 + вектор3 = " + vector6);

        vector6 = Vector.sum(vector3, vector4);
        System.out.println("Вектор6 = вектор3 + вектор4 = " + vector6);
        System.out.println();

        vector6 = Vector.dif(vector4, vector5);
        System.out.println("Вектор6 = вектор4 - вектор5 = " + vector6);

        vector6 = Vector.dif(vector4, vector3);
        System.out.println("Вектор6 = вектор4 - вектор3 = " + vector6);

        vector6 = Vector.dif(vector3, vector4);
        System.out.println("Вектор6 = вектор3 - вектор4 = " + vector6);
        System.out.println();

        double scalarProduct = Vector.scalarMultiply(vector4, vector5);
        System.out.println("Скалярное произведение вектора4 на вектор5 равно " + scalarProduct);

        scalarProduct = Vector.scalarMultiply(vector4, vector3);
        System.out.println("Скалярное произведение вектора4 на вектор3 равно " + scalarProduct);

        scalarProduct = Vector.scalarMultiply(vector3, vector4);
        System.out.println("Скалярное произведение вектора3 на вектор4 равно " + scalarProduct);
    }
}
