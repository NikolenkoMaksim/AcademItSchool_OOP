package ru.oop.nikolenko.array_list_home.methods.integer_methods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class MyIntegersListMethods {
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

    public static ArrayList<Integer> getIntegersListFromFile(String inputFileName) {
        ArrayList<Integer> list = new ArrayList<>();

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

            return list;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static ArrayList<Integer> getListWithOutDuplications(ArrayList<Integer> list) {
        checkList(list);

        ArrayList<Integer> resultList = new ArrayList<>(list.size());

        for (Integer data : list) {
            if (!resultList.contains(data)) {
                resultList.add(data);
            }
        }

        return resultList;
    }

    private static void checkList(ArrayList<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("list is null");
        }
    }
}
