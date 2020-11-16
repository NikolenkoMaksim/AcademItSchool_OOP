package ru.oop.nikolenko.array_list_home.array_list_home_Main;

import java.util.ArrayList;

import static ru.oop.nikolenko.array_list_home.methods.string_methods.MyStringListMethods.fillStringArrayListFromFile;
import static ru.oop.nikolenko.array_list_home.methods.interger_methods.MyIntegerListMethods.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();

        fillStringArrayListFromFile("listInput.txt", list1);
        System.out.println("Список 1:");
        System.out.println(list1);

        ArrayList<Integer> list2 = new ArrayList<>();
        boolean isAdded = list2.add(1);
        isAdded = list2.add(2);
        isAdded = list2.add(3);
        isAdded = list2.add(4);
        isAdded = list2.add(5);
        isAdded = list2.add(0);
        System.out.println("Список 2:");
        System.out.println(list2);

        removeEvenNumbers(list2);
        System.out.println("Список 2 после удаления четных чисел:");
        System.out.println(list2);

        ArrayList<Integer> list3 = new ArrayList<>();
        fillIntegerArrayListFromFile("listInput.txt", list3);
        System.out.println("Список 3:");
        System.out.println(list3);

        removeDuplications(list3);
        System.out.println("Список 3 после удаления повторяющихся чисел:");
        System.out.println(list3);
    }
}
