package WorkingWithRange1;

import java.util.Scanner;

import ClassRange.Range;

public class WorkingWithRange1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начальное число диапазона:");
        double from = scanner.nextDouble();

        System.out.println("Введите конечное число диапазона");
        double to = scanner.nextDouble();

        scanner.nextLine();

        Range range = new Range(from, to);

        double epsilon = 1e-10;

        if (to - from < -epsilon) {
            System.out.println(
                    "Начальное число диапазона должно быть меньше или равно конечному.");
            System.out.println("Чтобы поменять числа местами введите \"Исправить\"");
            String consent = scanner.nextLine();

            if (!consent.equals("Исправить")) {
                return;
            }

            range.setFrom(to);
            range.setTo(from);
            from = range.getFrom();
            to = range.getTo();
            System.out.println("Начальное число диапазона: " + from);
            System.out.println("Конечное число диапазона: " + to);
        }

        System.out.println("Выберите команду:");
        System.out.println("1 - вывести длину диапазона");
        System.out.println("2 - узнать принадлежность числа диапазону");
        System.out.println("другой символ - закрыть программу");

        while (true) {
            String command = scanner.nextLine();

            if (command.equals("1")) {
                System.out.println("Длина диапазона равна: " + range.getLength());
            } else if (!command.equals("2")) {
                break;
            } else {
                System.out.println("Введите число:");
                double number = scanner.nextDouble();

                scanner.nextLine();

                if (range.isInside(number)) {
                    System.out.println("Число принадлежит диапазону.");
                } else {
                    System.out.println("Число не принадлежит диапазону.");
                }
            }

            System.out.println("Введите команду:");
        }
    }
}
