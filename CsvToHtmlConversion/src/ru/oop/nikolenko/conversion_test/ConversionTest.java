package ru.oop.nikolenko.conversion_test;

import java.io.FileNotFoundException;

import static ru.oop.nikolenko.csv_o_html_conversion.CsvToHtmlConversion.convertCsvToHtml;

public class ConversionTest {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 2) {
            convertCsvToHtml(args[0], args[1]);
        } else {
            System.out.println("Ошибка! Аргументы программы не переданы или их количество неверно.");
            System.out.println("В программу должно быть передано два аргумента:");
            System.out.println("- адрес файла с исходной CSV-таблицей;");
            System.out.println("- адрес файла для итогового HTML-кода.");
        }
    }
}
