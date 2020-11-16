package ru.oop.nikolenko.array_list_home.methods.string_methods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyStringListMethods {
    public static void fillStringArrayListFromFile(String inputFileName, ArrayList<String> list) {
        if (list == null) {
            throw new IllegalArgumentException("list is null");
        }

        if (inputFileName == null) {
            throw new IllegalArgumentException("inputFileName is null");
        }

        try (Scanner scanner = new Scanner(new FileInputStream(inputFileName))) {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                list.add(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
