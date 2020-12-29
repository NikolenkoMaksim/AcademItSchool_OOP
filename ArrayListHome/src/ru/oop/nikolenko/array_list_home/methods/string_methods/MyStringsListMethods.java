package ru.oop.nikolenko.array_list_home.methods.string_methods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyStringsListMethods {
    public static ArrayList<String> getStringsListFromFile(String inputFileName) {
        if (inputFileName == null) {
            throw new IllegalArgumentException("inputFileName is null");
        }

        try (Scanner scanner = new Scanner(new FileInputStream(inputFileName))) {
            ArrayList<String> list = new ArrayList<>();

            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }

            return list;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
