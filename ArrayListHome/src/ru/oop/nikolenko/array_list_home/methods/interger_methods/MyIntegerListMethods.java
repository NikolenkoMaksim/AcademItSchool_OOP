package ru.oop.nikolenko.array_list_home.methods.interger_methods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class MyIntegerListMethods {
    public static void removeEvenNumbers(ArrayList<Integer> list) {
        checkList(list);

        int i = 0;

        while (i < list.size()) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            } else {
                i++;
            }
        }
    }

    public static void fillIntegerArrayListFromFile(String inputFileName, ArrayList<Integer> list) {
        checkList(list);

        if (inputFileName == null) {
            throw new IllegalArgumentException("inputFileName is null");
        }

        try (Scanner scanner = new Scanner(new FileInputStream(inputFileName))) {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();

                try {
                    list.add(valueOf(s));
                } catch (NumberFormatException e) {
                    System.out.println("String missed. NumberFormatException: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void removeDuplications(ArrayList<Integer> list) {
        checkList(list);

        for (int i = 0; i < list.size() - 1; i++) {
            int j = list.lastIndexOf(list.get(i));

            if (j != i) {
                list.remove(j);
            }
        }
    }

    private static void checkList(ArrayList<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("list is null");
        }
    }
}
