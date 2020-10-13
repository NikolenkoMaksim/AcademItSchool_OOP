package main;

import range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начальное число диапозона 1:");
        double from = scanner.nextDouble();

        System.out.println("Введите конечное число диапозона 1:");
        double to = scanner.nextDouble();

        Range range1 = new Range(from, to);
        range1.formatRangeEnds();

        System.out.println("Введите начальное число диапозона 2:");
        from = scanner.nextDouble();

        System.out.println("Введите конечное число диапозона 2:");
        to = scanner.nextDouble();

        Range range2 = new Range(from, to);
        range2.formatRangeEnds();

        System.out.println("Введите число:");
        double number = scanner.nextDouble();

        scanner.nextLine();

        if (range1.isInside(number)) {
            System.out.println("Число " + number + " принадлежит диапозону 1");
        } else {
            System.out.println("Число " + number + " не принадлежит диапозону 1");
        }

        if (range2.isInside(number)) {
            System.out.println("Число " + number + " принадлежит диапозону 2");
        } else {
            System.out.println("Число " + number + " не принадлежит диапозону 2");
        }

        System.out.println();

        System.out.println("Длина диапозона 1 равна " + range1.getLength());
        System.out.println("Длина диапозона 2 равна " + range2.getLength());
        System.out.println();

        Range intersection = range1.getIntersection(range2);

        if (intersection == null) {
            System.out.println("Диапозоны 1 и 2 не пересекаются");
        } else {
            System.out.println("Пересечение диапозонов 1 и 2: " + intersection);
        }

        System.out.println();

        Range[] union = range1.getUnion(range2);

        System.out.println("Объединение диапозонов 1 и 2: " + Arrays.toString(union));

        System.out.println();

        Range[] difference1 = range1.getDifference(range2);

        System.out.println("Разность диапозонов 1 и 2: " + Arrays.toString(difference1));

        System.out.println();

        Range[] difference2 = range2.getDifference(range1);

        System.out.println("Разность диапозонов 2 и 1: " + Arrays.toString(difference2));
    }
}
