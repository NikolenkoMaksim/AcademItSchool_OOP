package ru.oop.nikolenko.array_list_home.main;

import java.util.ArrayList;
import java.util.Arrays;

import static ru.oop.nikolenko.array_list_home.methods.string_methods.MyStringsListMethods.fillStringsListFromFile;
import static ru.oop.nikolenko.array_list_home.methods.interger_methods.MyIntegersListMethods.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> StringsList = fillStringsListFromFile("listInput.txt");
        System.out.println("Список 1:");
        System.out.println(StringsList);

        ArrayList<Integer> numbersList1 = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4, 5, 6, 5, 6, 6, 7, 8, 9, 9));
        System.out.println("Список 2:");
        System.out.println(numbersList1);

        removeEvenNumbers(numbersList1);
        System.out.println("Список 2 после удаления четных чисел:");
        System.out.println(numbersList1);

        ArrayList<Integer> numbersList2 = fillIntegersListFromFile("listInput.txt");
        System.out.println("Список 3:");
        System.out.println(numbersList2);

        if (numbersList2 != null) {
            ArrayList<Integer> numbersList3 = removeDuplications(numbersList2);
            System.out.println("Список 4 - это список 3 без повторяющихся чисел:");
            System.out.println(numbersList3);
        }
    }
}
