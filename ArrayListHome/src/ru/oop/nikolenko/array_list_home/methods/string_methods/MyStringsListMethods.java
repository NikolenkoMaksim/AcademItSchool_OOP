package ru.oop.nikolenko.array_list_home.methods.string_methods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyStringsListMethods {
    public static ArrayList<String> fillStringsListFromFile(String inputFileName) {
        if (inputFileName == null) {
            throw new IllegalArgumentException("inputFileName is null");
        }

        ArrayList<String> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(inputFileName))) {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                list.add(s);
            }

            return list;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
