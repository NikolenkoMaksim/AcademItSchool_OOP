package ru.oop.nikolenko.conversion_test;

import java.io.FileNotFoundException;

import static ru.oop.nikolenko.csv_to_html_converter.CsvToHtmlConverter.convertCsvToHtml;

public class ConversionTest {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error. Program arguments weren't passed.");
            printErrorMessage();
            return;
        }

        if (args.length == 1) {
            System.out.println("Error. Program argument 1  wasn't passed.");
            printErrorMessage();
            return;
        }

        try {
            convertCsvToHtml(args[0], args[1]);
            System.out.println("Conversion complete");
        } catch (FileNotFoundException e) {
            System.out.println("Source CSV table file not found: " + e.getMessage());
        }
    }

    private static void printErrorMessage() {
        System.out.println("Two arguments must be passed to the program:");
        System.out.println("- source CSV table file path;");
        System.out.println("- resulting HTML code file path.");
    }
}
