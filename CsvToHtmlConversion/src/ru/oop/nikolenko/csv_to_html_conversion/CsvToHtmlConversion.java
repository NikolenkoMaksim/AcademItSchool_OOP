package ru.oop.nikolenko.csv_to_html_conversion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CsvToHtmlConversion {
    public static void convertCsvToHtml(String inputFileName, String outputFileName) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(inputFileName));
             PrintWriter writer = new PrintWriter(outputFileName)) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("\t<head>");
            writer.println("\t\t<title>" + "CSV таблица конвектированная в HTML" + "</title>");
            writer.println("\t\t<meta charset=\"UTF-8\">");
            writer.println("\t</head>");
            writer.println("\t<body>");
            writer.println("\t\t<table>");

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();

                while (s.isEmpty() && scanner.hasNextLine()) {
                    s = scanner.nextLine();
                }

                if (s.isEmpty() && !scanner.hasNextLine()) {
                    break;
                }

                writer.print("\t\t\t<tr>");

                int i = 0;
                boolean cellIsOpened = false;

                writer.print("<td>");

                while (i < s.length()) {
                    char c = s.charAt(i);

                    if (c == ',' && !cellIsOpened) {
                        writer.print("</td><td>");
                        i++;

                        if (i == s.length()) {
                            writer.print("</td>");
                        }

                        continue;
                    }

                    if (c == '\"') {
                        if (!cellIsOpened) {
                            if (i != s.length() - 1) {
                                i++;
                            } else {
                                writer.print("</br>");
                                s = scanner.nextLine();

                                while (s.isEmpty()) {
                                    writer.print("</br>");
                                    s = scanner.nextLine();
                                }

                                i = 0;
                            }

                            cellIsOpened = true;
                            continue;
                        }

                        if (i == s.length() - 1) {
                            i++;
                            cellIsOpened = false;
                        } else {
                            char nextCharacter = s.charAt(i + 1);

                            if (nextCharacter == '\"') {
                                writer.print("\"");
                                i += 2;
                            } else {
                                cellIsOpened = false;
                                i++;
                            }
                        }
                    } else {
                        if (c == '&') {
                            writer.print("&amp;");
                        } else if (c == '<') {
                            writer.print("&lt;");
                        } else if (c == '>') {
                            writer.print("&gt;");
                        } else {
                            writer.print(c);
                        }

                        i++;
                    }

                    if (i == s.length()) {
                        if (!cellIsOpened) {
                            writer.print("</td>");
                        } else {
                            writer.print("</br>");
                            s = scanner.nextLine();
                            i = 0;
                        }
                    }
                }

                writer.println("</tr>");
            }

            writer.println("\t\t</table>");
            writer.println("\t</body>");
            writer.println("</html>");
        }
    }
}
