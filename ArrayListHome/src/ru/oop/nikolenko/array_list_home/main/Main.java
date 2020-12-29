package ru.oop.nikolenko.array_list_home.main;

import ru.oop.nikolenko.array_list_home.methods.integer_methods.MyIntegersListMethods;
import ru.oop.nikolenko.array_list_home.methods.string_methods.MyStringsListMethods;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> fileStrings = MyStringsListMethods.getStringsListFromFile("listInput.txt");
        System.out.println("Список 1:");
        System.out.println(fileStrings);

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4, 5, 6, 5, 6, 6, 7, 8, 9, 9));
        System.out.println("Список 2:");
        System.out.println(numbers);

        MyIntegersListMethods.removeEvenNumbers(numbers);
        System.out.println("Список 2 после удаления четных чисел:");
        System.out.println(numbers);

        ArrayList<Integer> fileIntegers = MyIntegersListMethods.getIntegersListFromFile("listInput.txt");
        System.out.println("Список 3:");
        System.out.println(fileIntegers);

        if (fileIntegers != null) {
            ArrayList<Integer> fileIntegersWithOutDuplications = MyIntegersListMethods.getListWithoutDuplications(fileIntegers);
            System.out.println("Список 4 - это список 3 без повторяющихся чисел:");
            System.out.println(fileIntegersWithOutDuplications);
        }
    }
}
