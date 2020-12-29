package ru.oop.nikolenko.array_list_home.methods.integer_methods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyIntegersListMethods {
    public static void removeEvenNumbers(ArrayList<Integer> list) {
        checkListForNull(list);

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
        if (inputFileName == null) {
            throw new IllegalArgumentException("inputFileName is null");
        }

        try (Scanner scanner = new Scanner(new FileInputStream(inputFileName))) {
            ArrayList<Integer> list = new ArrayList<>();

            while (scanner.hasNextLine()) {
                if (scanner.hasNextInt()) {
                    list.add(scanner.nextInt());
                } else {
                    scanner.nextLine();
                }
            }

            return list;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static ArrayList<Integer> getListWithoutDuplications(ArrayList<Integer> list) {
        checkListForNull(list);

        ArrayList<Integer> resultList = new ArrayList<>(list.size());

        for (Integer number : list) {
            if (!resultList.contains(number)) {
                resultList.add(number);
            }
        }

        return resultList;
    }

    private static void checkListForNull(ArrayList<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("list is null");
        }
    }
}
