package ru.oop.nikolenko.hash_table_main;

import ru.oop.nikolenko.hash_table.HashTable;
import ru.oop.nikolenko.my_array_list.MyArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Double> table1 = new HashTable<>(7);
        HashTable<Double> table2 = new HashTable<>();

        Double[] array1 = {1.1, 2.2, 8.2, 7.6, 8.6, 4.4, 1.1, 0.11, 0.999, 1e2};
        MyArrayList<Double> list1 = new MyArrayList<>(array1);

        table1.addAll(list1);
        System.out.println("Таблица 1:");
        System.out.println(table1);

        table2.addAll(list1);
        System.out.println("Таблица 2:");
        System.out.println(table2);

        System.out.println("Длина таблицы 1: " + table1.getLength());
        System.out.println("Размер таблицы 1: " + table1.size());

        System.out.println("Длина таблицы 2: " + table2.getLength());
        System.out.println("Размер таблицы 2: " + table2.size());

        System.out.println("Проверка \"isEmpty\" таблицы 1: " + table1.isEmpty());
        table1.clear();
        System.out.println("Применили \"clear()\"  к таблице 1: ");
        System.out.println(table1);
        System.out.println("Проверка \"isEmpty\" таблицы 1: " + table1.isEmpty());

        System.out.println("Коллекция 1:");
        System.out.println(list1);
        table1.addAll(list1);
        System.out.println("К таблице 1 добавили коллекцию 1:");
        System.out.println(table1);

        System.out.println("Проверка \"contains(1)\" таблицы 1: " + table1.contains(1.0));
        System.out.println("Проверка \"contains(4.4)\" таблицы 1: " + table1.contains(4.4));

        System.out.println("Вывод результата table1.toArray(): ");
        Object[] array2 = table1.toArray();
        System.out.println(Arrays.toString(array2));

        /*
        System.out.println("Вывод результата table1.toArray(): ");
        Double[] array4 = table1.toArray();
        System.out.println(Arrays.toString(array4));

        System.out.println("Вывод результата table1.toArray(): ");
        Double[][] array3 = table1.toArray2();
        System.out.println(Arrays.toString(array3));
        */

        System.out.println("Вывод результата table1.toArray(array2): ");
        array2 = table1.toArray(array2);
        System.out.println(Arrays.toString(array2));

        System.out.println("В таблицу 1 добавили 7.7 :" + table1.add(7.7));
        System.out.println("Таблица 1: ");
        System.out.println(table1);

        System.out.println("Из таблицы 1 удалили 1.1 :" + table1.remove(1.1));
        System.out.println("Таблица 1: ");
        System.out.println(table1);

        Double[] array6 = {1.1, 8.6, 4.4, 1.1, 0.11, 0.999, 1e2};
        MyArrayList<Double> list2 = new MyArrayList<>(array6);
        System.out.println("Список 2: ");
        System.out.println(list2);
        Double[] array7 = {1.1, 8.6, 88.3, 0.11, 0.999, 1e2};
        MyArrayList<Double> list3 = new MyArrayList<>(array7);
        System.out.println("Список 3: ");
        System.out.println(list3);

        System.out.println("В таблицу 1 добавили 8.2: " + table1.add(8.2));
        System.out.println("В таблицу 1 добавили 1.1: " + table1.add(1.1));
        System.out.println("Таблица 1: ");
        System.out.println(table1);

        System.out.println("Таблица 1 содержит список 2: " + table1.containsAll(list2));
        System.out.println("Таблица 1 содержит список 3: " + table1.containsAll(list3));

        System.out.println("Выполнили \"table1.retainAll(list3)\". Результат: " + table1.retainAll(list3));
        System.out.println("Таблица 1: ");
        System.out.println(table1);
        System.out.println("Размер таблицы 1: " + table1.size());

        System.out.println("Выполнили \"table1.retainAll(list2)\". Результат: " + table1.removeAll(list2));
        System.out.println("Таблица 1: ");
        System.out.println(table1);
        System.out.println("Размер таблицы 1: " + table1.size());
    }
}
